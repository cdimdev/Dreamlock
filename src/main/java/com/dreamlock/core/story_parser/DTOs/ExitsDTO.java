package com.dreamlock.core.story_parser.DTOs;

public class ExitsDTO {
    private final int north;
    private final int east;
    private final int south;
    private final int west;

    public ExitsDTO(int north, int east, int south, int west) {
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public int getNorth() {
        return north;
    }

    public int getEast() {
        return east;
    }

    public int getSouth() {
        return south;
    }

    public int getWest() {
        return west;
    }
}
