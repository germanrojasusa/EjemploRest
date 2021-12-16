package com.example.ejemplorest;

public class Character {
    private int id;
    private String name;
    private String description;
    private String power;
    private int agility;
    private int strength;
    private String link;

    public Character(int id, String name, String description, String power, int agility, int strength, String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.power = power;
        this.agility = agility;
        this.strength = strength;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
