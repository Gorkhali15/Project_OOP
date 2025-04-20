package com.example.lutemon_games;

import java.io.Serializable;

public class Lutemon implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String name, type;
    protected int attack, defense, maxHealth, currentHealth, experience;

    public Lutemon(String name, String type, int attack, int defense, int maxHealth) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.experience = 0;
    }

    public int attack() {
        return attack + experience;
    }

    public void defend(int damage) {
        int reduced = damage - defense;
        if (reduced > 0) currentHealth -= reduced;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void gainExperience() {
        experience++;
    }

    public void heal() {
        currentHealth = maxHealth;
    }

    public String getStats() {
        return name + " (" + type + ") - HP: " + currentHealth + "/" + maxHealth + ", XP: " + experience;
    }

    public String getName() {
        return name;
    }
}

