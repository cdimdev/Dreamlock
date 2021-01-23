package com.dreamlock.core.story_parser.DTOs.itemDTOs;

public class ConsumableDTO {
    private final int id;
    private final String type;
    private final String state;
    private final String name;
    private final String description;
    private final String effect;
    private final int healthStat;
    private final int staminaStat;
    private final int strengthStat;
    private final int agilityStat;

    public ConsumableDTO(int id, String type, String state, String name,
        String description, String effect, int healthStat, int staminaStat, int strengthStat,
        int agilityStat) {
        this.id = id;
        this.type = type;
        this.state = state;
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.healthStat = healthStat;
        this.staminaStat = staminaStat;
        this.strengthStat = strengthStat;
        this.agilityStat = agilityStat;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEffect() {
        return effect;
    }

    public int getHealthStat() {
        return healthStat;
    }

    public int getStaminaStat() {
        return staminaStat;
    }

    public int getStrengthStat() {
        return strengthStat;
    }

    public int getAgilityStat() {
        return agilityStat;
    }
}
