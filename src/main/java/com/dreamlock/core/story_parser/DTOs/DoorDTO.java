package com.dreamlock.core.story_parser.DTOs;

public class DoorDTO {
    private final String name;
    private final String direction;
    private final String path;
    private final int id;
    private final int requiredKey;

    public DoorDTO(String name, String direction, String path, int id, int requiredKey) {
        this.name = name;
        this.direction = direction;
        this.path = path;
        this.id = id;
        this.requiredKey = requiredKey;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRequiredKey() {
        return requiredKey;
    }

    public String getDirection() {
        return direction.toLowerCase();
    }

    public String getPath() {
        return path;
    }
}
