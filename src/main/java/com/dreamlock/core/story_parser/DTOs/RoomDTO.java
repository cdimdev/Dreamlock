package com.dreamlock.core.story_parser.DTOs;

import java.util.ArrayList;

public class RoomDTO {
    private final int id;
    private final String path;
    private final ArrayList<ItemDTO> items;
    private final ArrayList<ExitsDTO> exits;
    private final ArrayList<EnemyDTO> enemies;
    private final ArrayList<DoorDTO> doors;

    public RoomDTO(int id, String path,
        ArrayList<ItemDTO> items,
        ArrayList<ExitsDTO> exits,
        ArrayList<EnemyDTO> enemies,
        ArrayList<DoorDTO> doors) {
        this.id = id;
        this.path = path;
        this.items = items;
        this.exits = exits;
        this.enemies = enemies;
        this.doors = doors;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public ArrayList<ItemDTO> getItems() {
        return items;
    }

    public ArrayList<ExitsDTO> getExits() {
        return exits;
    }

    public ArrayList<EnemyDTO> getEnemies(){
        return enemies;
    }

    public ArrayList<DoorDTO> getDoors() {
        return doors;
    }
}
