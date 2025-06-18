package com.costr.tailklicker.Logik;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

/**
 * @author costr
 */

public class Player implements Notation {

    private int ID;
    private String name;
    private Schwierigkeit level;
    private int count;

    public Player(int id, String name, Schwierigkeit level, int count) {
        this.ID = id;
        this.name = name;
        this.level = level;
        this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getName().equals(((Player) obj).getName())) {

            LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist gleich wie {2} selbst.{3}",
                    new Object[] { GREEN, this.name, ((Player) obj).getName(), RESET });
            return false;
        } else if (!(obj instanceof Player)) {
            LOGGER.log(Level.WARNING, "{0}Das Objekt {1} ist kein Spieler.{2}",
                    new Object[] { RED, obj.getClass().getSimpleName(), RESET });
            return false;
        } else if (this.getID() == ((Player) obj).getID()) {
            LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist gleich wie {2}.{3}",
                    new Object[] { GREEN, this.name, ((Player) obj).getName(), RESET });
            return false;
        } else {
            return true;
        }

    }

    public int hashCode() {
        return Integer.hashCode(ID);
    }

    public int getID() {
        return ID;
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

    public void setID() {
        int levelID;
        switch (level) {
            case LEICHT:
                levelID = 1;
                break;
            case MITTEL:
                levelID = 2;
                break;
            case SCHWER:
                levelID = 3;
                break;
            case EXTREM:
                levelID = 4;
                break;
            default:
                levelID = 0; // Default case if no level matches
        }
        // System.out.println("Level ID: " + levelID);
        // System.out.println("Current ID: " + TailklickerApplication.getCurrentID());
        String idString = String.format("%02d%02d", levelID, TailklickerApplication.getCurrentID());
        // System.out.println("ID String: " + idString);
        this.ID = Integer.parseInt(idString);
        // System.out.println("Set ID: " + this.ID);
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