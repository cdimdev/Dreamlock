package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.game.constants.EquipmentSlot;

public class ArmorDTO {
    private final int id;
    private final String type;
    private final String name;
    private final String description;
    private final String defense;
    private final String stamina;
    private final String strength;
    private final String agility;
    private final String equipmentSlot;

    public ArmorDTO(int id, String type, String name, String description, String defense,
        String stamina, String strength, String agility, String equipmentSlot) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.defense = defense;
        this.stamina = stamina;
        this.strength = strength;
        this.agility = agility;
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("head"))
            return EquipmentSlot.HEAD;
        if(equipmentSlot.equalsIgnoreCase("chest"))
            return EquipmentSlot.CHEST;
        if(equipmentSlot.equalsIgnoreCase("hands"))
            return EquipmentSlot.HANDS;
        if(equipmentSlot.equalsIgnoreCase("legs"))
            return EquipmentSlot.LEGS;
        if(equipmentSlot.equalsIgnoreCase("feet"))
            return EquipmentSlot.FEET;
        return null;
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

    public String getDescription() {
        return description;
    }

    public String getDefense() {
        return defense;
    }

    public String getAgility() {
        return agility;
    }

    public String getStrength() {
        return strength;
    }

    public String getStamina() {
        return stamina;
    }
}
