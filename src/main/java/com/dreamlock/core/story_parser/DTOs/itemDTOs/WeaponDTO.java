package com.dreamlock.core.story_parser.DTOs.itemDTOs;

import com.dreamlock.core.game.constants.EquipmentSlot;

public class WeaponDTO {
    private final int id;
    private final String type;
    private final String name;
    private final String description;
    private final String attack;
    private final String stamina;
    private final String strength;
    private final String agility;
    private final String equipmentSlot;

    public WeaponDTO(int id, String type, String name, String description, String attack,
        String stamina, String strength, String agility, String equipmentSlot) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.attack = attack;
        this.stamina = stamina;
        this.strength = strength;
        this.agility = agility;
        this.equipmentSlot = equipmentSlot;
    }

    public EquipmentSlot getEquipmentSlot() {
        if(equipmentSlot.equalsIgnoreCase("main_hand"))
            return EquipmentSlot.MAIN_HAND;
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

    public String getAttack() {
        return attack;
    }

    public String getStrength() {
        return strength;
    }

    public String getAgility() {
        return agility;
    }

    public String getStamina() {
        return stamina;
    }
}
