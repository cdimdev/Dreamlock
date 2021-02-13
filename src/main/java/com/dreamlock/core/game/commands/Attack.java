
package com.dreamlock.core.game.commands;


import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ActionState;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Attack extends AbstractCommand{
    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        int enemiesFound = 0;
        Enemy foundEnemy = null;
        Word word = words.get(Sequence.SECOND);
        List<Enemy> enemies = gameContext.getCurrentRoom().getEnemies();

        if (enemies.size() > 0 && gameContext.getTurnBattle().enemiesAlive()) {
            gameContext.getTurnBattle().letTheBattleBegin(); // activate turn system
        }

        for (Enemy enemy : enemies) {
            if (enemy.getName().toLowerCase().contains(word.getDescription())) {
                enemiesFound++;
                foundEnemy = enemy;
            }
        }

        if (enemiesFound == 1) {
            return attackFoundEnemy(gameContext, foundEnemy);
        }

        outputMessages.add(new OutputMessage(1305, PrintStyle.ONLY_TITLE));
        return outputMessages;
    }

    private List<OutputMessage> attackFoundEnemy(IGameContext gameContext, Enemy enemy) {
        List<OutputMessage> outputMessages = new ArrayList<>();
        if (enemy.getHealth() > 0) {
            outputMessages.addAll(attackEnemyHealth(gameContext, enemy));
        } else if (enemy.getHealth() <= 0) {
            outputMessages.add(new OutputMessage(1306, PrintStyle.ONLY_TITLE));
        }

        //When battle is still active change turn
        if (gameContext.getTurnBattle().activeBattle()) {
            outputMessages.addAll(changeTurn(gameContext));
        }

        return outputMessages;
    }

    private List<OutputMessage> attackEnemyHealth(IGameContext gameContext,
        Enemy enemy) {
      List<OutputMessage> outputMessages = new ArrayList<>();

        outputMessages
            .add(new OutputMessage(enemy.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        outputMessages.add(new OutputMessage(1301, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        outputMessages.add(enemy.getStates().get(ActionState.ATTACK)
            .doAction(gameContext, gameContext.getPlayer(), enemy));
        outputMessages.add(new OutputMessage(1309, PrintStyle.ONLY_TITLE));

        if (!enemy.isAlive()) {
            outputMessages
                .add(new OutputMessage(enemy.getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
            outputMessages.add(new OutputMessage(1307, PrintStyle.ONLY_TITLE));
        }
        return outputMessages;
    }

    private List<OutputMessage> changeTurn(IGameContext gameContext) {
      List<OutputMessage> outputMessages = new ArrayList<>();

      List<OutputMessage> templist = gameContext.getTurnBattle().nextTurn(gameContext);
        while (gameContext.getTurnBattle().activeBattle() && templist != null) {
            outputMessages.addAll(templist);
            templist = gameContext.getTurnBattle().nextTurn(gameContext);
        }

        if (!gameContext.getTurnBattle().activeBattle()) {
            outputMessages.add(new OutputMessage(1310, PrintStyle.ONLY_TITLE));
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        }

        return outputMessages;
    }
}