package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.story_parser.DTOs.ItemDTO;

import java.util.ArrayList;

public class ContainerDTO {
    private final int id;
    private final String type;
    private final String name;
    private final String description;
    private final boolean locked;
    private final int match;
    private final ArrayList<ItemDTO> items;

    public ContainerDTO(int id, String type, String name, String description, boolean locked,
        int match, ArrayList<ItemDTO> items) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.locked = locked;
        this.match = match;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getDescription() {
        return description;
    }

    public int getMatch() {
        return match;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }
}
