package com.dreamlock.core.game.commands;

import com.dreamlock.core.game.IGameContext;
import com.dreamlock.core.game.constants.Sequence;
import com.dreamlock.core.game.models.Door;
import com.dreamlock.core.game.models.OutputMessage;
import com.dreamlock.core.game.models.Room;
import com.dreamlock.core.game.models.Word;
import com.dreamlock.core.message_system.constants.PrintStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Go implements ICommand {
    @Override
    public List<OutputMessage> execute(IGameContext gameContext) {
        return null;
    }

    @Override
    public List<OutputMessage> execute(IGameContext gameContext, Map<Sequence, Word> words) {
        String direction = words.get(Sequence.SECOND).getDescription();

        if(gameContext.canMove()) {
            return GoTo(gameContext, words, direction);
        } else{
            return cannotMove();
        }
    }

    private List<OutputMessage> GoTo(IGameContext gameContext, Map<Sequence, Word> words, String direction) {
        List<OutputMessage> outputMessages = new ArrayList<>();

        Room nextRoom = gameContext.getCurrentRoom().getExits().get(direction);
        Room currentRoom = gameContext.getCurrentRoom();
        Door lockedDoor = findLockedDoor(currentRoom, direction);

        if (!nextRoom.getDescription().equals("wall") && lockedDoor == null) {
            gameContext.setCurrentRoom(nextRoom);
            Look look = new Look();
            return look.execute(gameContext);
        }
        else {
            if (lockedDoor != null) {
                outputMessages.add(new OutputMessage(lockedDoor.getId(), PrintStyle.ONLY_DESCRIPTION_IN_SAME_LINE));
                outputMessages.add(new OutputMessage(1152, PrintStyle.ONLY_TITLE));
            }
            else {
                outputMessages.add(new OutputMessage(1001, PrintStyle.ONLY_TITLE_IN_SAME_LINE));
                outputMessages.add(new OutputMessage(words.get(Sequence.SECOND).getId(), PrintStyle.ONLY_TITLE));
            }
            outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

            return outputMessages;
        }
    }


    private List<OutputMessage> cannotMove()
    {
        List<OutputMessage> outputMessages = new ArrayList<>();
        outputMessages.add(new OutputMessage(1009, PrintStyle.ONLY_TITLE));
        outputMessages.add(new OutputMessage(0, PrintStyle.BREAK));

        return outputMessages;
    }

    private Door findLockedDoor(Room roomToCheck, String directionToCheck)
    {
        List<Door> doors = roomToCheck.getDoors();
        Door roomDoor = null;
        for (Door door : doors) {
            if (door.getDirection().equals(directionToCheck)) {
                if (door.isLocked()) {
                    roomDoor = door;
                }
            }
        }

        return roomDoor;
    }
}
