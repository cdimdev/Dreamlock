package com.dreamlock.core.story_parser.DTOs;

public class ItemDTO {
    private final String path;
    private final int id;

    public ItemDTO(String path, int id) {
        this.path = path;
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public int getId() {
        return id;
    }
}
