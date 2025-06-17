package com.costr.tailklicker.Logik;

/**
 * @author costr
 */

public class Player {

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

    public void setID(int ID) {
        this.ID = ID;
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