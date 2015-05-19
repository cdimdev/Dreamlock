package com.dreamlock.game.commands;

import com.dreamlock.game.IGameContext;
import com.dreamlock.game.jsonParser.items.Item;
import com.dreamlock.game.models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inspect implements ICommand {
    //TODO: FIX THIS SHIT
    @Override
    public List<Integer> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<Integer> execute(IGameContext gameContext, Map<Integer, Word> words) {
        List<Integer> output = new ArrayList<>();

        Word word = words.get(2);

        List<Item> foundItems = gameContext.getPlayer().getInventory().containsItems(words.get(2));

        Item item = gameContext.getPlayer().getSlot(Item.EquipmentSlot.HEAD);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(Item.EquipmentSlot.CHEST);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        item = gameContext.getPlayer().getSlot(Item.EquipmentSlot.MAIN_HAND);
        if(item != null){
            if (item.getName().toLowerCase().equals(word.getDescription())) {
                foundItems.add(item);
            }
        }
        if (foundItems != null) {
            output.add(10000);
            if (foundItems.size() ==1 ) {
                if (foundItems.get(0).getType().equalsIgnoreCase("armor")) {
                    output.add(foundItems.get(0).getId());
                    output.add(1131);
                    output.add( Integer.parseInt(foundItems.get(0).getStats().get("defense").toString()));
                    output.add(1308);
                }else if (foundItems.get(0).getType().equalsIgnoreCase("weapon")){
                    output.add(foundItems.get(0).getId());
                    output.add(1130);
                    output.add( Integer.parseInt(foundItems.get(0).getStats().get("attack").toString()));
                    output.add(1308);
                }
                return output;
            }
            else if (foundItems.size() > 1) {
                output.add(2001);
                return output;
            }
        }
        output.add(10000);          // print only title
        output.add(1020);           // I can't find anything with that name!
        return output;
    }


}
