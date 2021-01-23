package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.Enemy;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Look implements ICommand {

  @Override
  public List<OutputMessage> execute(IGameContext gameContext) {
    List<OutputMessage> outputMessages = new ArrayList<>();
    Room room = gameContext.getCurrentRoom();

    outputMessages.add(new OutputMessage(room.getId(), PrintStyle.ONLY_TITLE));
    outputMessages.add(new OutputMessage(room.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));

    // add available doors to outputMessages
    getAvailableDoors(gameContext, outputMessages);
    // add available items to outputMessages
    getAvailableItems(gameContext, outputMessages);
    // add available enemies to outputMessage
    getAvailableEnemies(gameContext, outputMessages);

    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

    return outputMessages;
  }

  void getAvailableEnemies(IGameContext gameContext, List<OutputMessage> outputMessages) {
    List<Enemy> enemies = gameContext.getCurrentRoom().getEnemies();
    boolean isAlive = false;
    for (Enemy enemy : enemies) {
      if (enemy.isAlive()) {
        isAlive = true;
      }
    }
    if (enemies.size() == 1 && isAlive) {
      outputMessages.add(new OutputMessage(1802, PrintStyle.ONLY_TITLE));
    } else if (enemies.size() > 1 && isAlive) {
      outputMessages.add(new OutputMessage(1803, PrintStyle.ONLY_TITLE));
    }
    for (Enemy enemy : enemies) {
      if (enemy.isAlive()) {
        outputMessages.add(new OutputMessage(2087, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        outputMessages.add(new OutputMessage(enemy.getId(), PrintStyle.ONLY_TITLE));
      }
    }
  }

  void getAvailableItems(IGameContext gameContext, List<OutputMessage> outputMessages) {
    List<Item> items = gameContext.getCurrentRoom().getItems();
    int numberOfItems = 0;
    for (Item item : items) {
      if (!item.getType().equals(ItemType.MISC)) {
        numberOfItems++;
      }
    }
    //outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
    if (numberOfItems == 1) {
      outputMessages.add(new OutputMessage(1800, PrintStyle.ONLY_TITLE));
    } else if (numberOfItems > 1) {
      outputMessages.add(new OutputMessage(1801, PrintStyle.ONLY_TITLE));
    }

    for (Item item : items) {
      if (!item.getType().equals(ItemType.MISC)) {
        outputMessages.add(new OutputMessage(2087, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
        outputMessages.add(new OutputMessage(item.getId(), PrintStyle.ONLY_TITLE));
      }
    }
  }

  private void getAvailableDoors(IGameContext gameContext, List<OutputMessage> outputMessages) {
    Map<String, Room> exits = gameContext.getCurrentRoom().getExits();
    if (exits.size() == 1) {
      outputMessages.add(new OutputMessage(1804, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
      outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
      outputMessages.add(new OutputMessage(1804, PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
    } else {
      outputMessages.add(new OutputMessage(1805, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
    }
    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
    int id = 9200;
    for (Map.Entry<String, Room> door : exits.entrySet()) {
      if (!door.getValue().getTitle().equals("wall")) {
        gameContext.registerMessage("-on your " + door.getKey(), id);
        outputMessages.add(new OutputMessage(id, PrintStyle.ONLY_TITLE));
        id++;
      }
    }
  }

  @Override
  public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
    return null;
  }
}