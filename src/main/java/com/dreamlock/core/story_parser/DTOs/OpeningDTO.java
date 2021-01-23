package com.dreamlock.core.story_parser.DTOs;

public class OpeningDTO {
    private final String opening;
    private final String welcome;

    public OpeningDTO(String opening, String welcome) {
        this.opening = opening;
        this.welcome = welcome;
    }

    public String getOpening() {
        return opening;
    }

    public String getWelcome() {
        return welcome;
    }
}
