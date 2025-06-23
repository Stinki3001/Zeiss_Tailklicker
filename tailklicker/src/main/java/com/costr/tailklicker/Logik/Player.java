package com.costr.tailklicker.Logik;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;

/**
 * @author costr
 */

public class Player implements Notation {

    private String name;
    private Schwierigkeit level = Schwierigkeit.LEICHT; // Default level
    private int count;

    public Player(String name, Schwierigkeit level, int count) {
        this.name = name;
        this.level = level;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getName().equals(((Player) obj).getName())) {

            LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist gleich wie {2} selbst.{3}",
                    new Object[] { GREEN, this.name, ((Player) obj).getName(), RESET });
            return true;
        } else if (!(obj instanceof Player)) {
            LOGGER.log(Level.WARNING, "{0}Das Objekt {1} ist kein Spieler.{2}",
                    new Object[] { RED, obj.getClass().getSimpleName(), RESET });
            return false;
        } else {
            return true;
        }

    }

    public int hashCode() {

        int code = Integer.hashCode(this.getName().hashCode());
        code = code + Integer.hashCode(this.getLevel().hashCode());
        return code;
    }

    public String getName() {
        return name;
    }

    public Schwierigkeit getLevel() {
        return level;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(Schwierigkeit level) {
        this.level = level;
    }

    public void setCount(int count) {
        this.count = count;
    }
}