package com.dreamlock.game.jsonParser.items;

import com.dreamlock.game.jsonParser.DTOs.itemDTOs.RoomObjectDTO;
import com.dreamlock.game.states.itemStates.CanNotPickUp;
import com.dreamlock.game.states.itemStates.CanOpen;
import com.google.gson.Gson;

import java.util.HashMap;

public class RoomObject extends Item {
    public RoomObject(String jsonItem) {
        Gson gson = new Gson();
        RoomObjectDTO roomObjectDTO = gson.fromJson(jsonItem, RoomObjectDTO.class);
        id = roomObjectDTO.getId();
        type = roomObjectDTO.getType();
        name = roomObjectDTO.getName();
        description = roomObjectDTO.getDescription();

        stats = new HashMap<>();

        states = new HashMap<>();
        // changing states
        states.put("Pick Up", new CanNotPickUp());
        states.put("Open", new CanOpen());
    }
}
