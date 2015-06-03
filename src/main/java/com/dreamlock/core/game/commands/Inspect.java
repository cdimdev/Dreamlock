package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.EquipmentSlot;
import com.dreamlock.core.game.constants.ItemType;
import com.dreamlock.core.game.constants.Stats;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;
import com.dreamlock.core.story_parser.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inspect implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        Word word = words.get(2);

        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words.get(2));

        Item item = gameContext.getPlayer().getSlot(EquipmentSlot.HEAD);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.CHEST);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(EquipmentSlot.MAIN_HAND);
        if(item != null){
            if (item.getName().toLowerCase().contains(word.getDescription())) {
                foundItems.add(item);
            }
        }
        if (foundItems != null) {
            if (foundItems.size() ==1 ) {
                if (foundItems.get(0).getType().equals(ItemType.ARMOR)) {
                    outputMessages.add(new OutputMessage(foundItems.get(0).getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1131, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItems.get(0).getStats().get(Stats.DEFENSE).toString()), PrintStyle.NUMBER));
                    outputMessages.add(new OutputMessage(1308, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                else if (foundItems.get(0).getType().equals(ItemType.WEAPON)) {
                    outputMessages.add(new OutputMessage(foundItems.get(0).getId(), PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(1130, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                    outputMessages.add(new OutputMessage(Integer.parseInt(foundItems.get(0).getStats().get(Stats.ATTACK).toString()), PrintStyle.NUMBER));
                    outputMessages.add(new OutputMessage(1308, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                else {
                    outputMessages.add(new OutputMessage(1133, PrintStyle.ONLY_TITLE));
                    outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                }
                return outputMessages;
            }
            else if (foundItems.size() > 1) {
                outputMessages.add(new OutputMessage(2001, PrintStyle.ONLY_TITLE));
                outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
                return outputMessages;
            }
        }
        outputMessages.add(new OutputMessage(1020, PrintStyle.ONLY_TITLE));           // I can't find anything with that name!
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));
        return outputMessages;
    }


}
