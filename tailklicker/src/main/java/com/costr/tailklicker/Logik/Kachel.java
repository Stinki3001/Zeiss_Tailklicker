package com.costr.tailklicker.Logik;

import java.util.ArrayList;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

public class Kachel implements Notation {

    private final int x;
    private final int y;
    public boolean isInverted;
    private ArrayList<Kachel> neighbours;

    public Kachel(int x, int y) {
        this.x = x;
        this.y = y;
        this.isInverted = false;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setNeighbours(Kachel[][] kachelGroup, Kachel kachel) {
        neighbours = new ArrayList<>();
        if (istInFeld(kachel.getX(), kachel.getY())) {
            if (istInFeld(kachel.getX() - 1, kachel.getY())) {
                neighbours.add(kachelGroup[kachel.getX() - 1][kachel.getY()]); // left
            }
            if (istInFeld(kachel.getX() + 1, kachel.getY())) {
                neighbours.add(kachelGroup[kachel.getX() + 1][kachel.getY()]); // right
            }
            if (istInFeld(kachel.getX(), kachel.getY() - 1)) {
                neighbours.add(kachelGroup[kachel.getX()][kachel.getY() - 1]); // up
            }
            if (istInFeld(kachel.getX(), kachel.getY() + 1)) {
                neighbours.add(kachelGroup[kachel.getX()][kachel.getY() + 1]); // down
            }
        } else {
            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
            return;
        }

    }

    public ArrayList<Kachel> getNeighbours() {
        return neighbours;
    }

    public static boolean istInFeld(int x, int y) {
        if (y >= 0 && x >= 0 && y < TailklickerApplication.getRows() && x < TailklickerApplication.getCols()) {
            return true;
        } else {

            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { BRIGHT_RED, x, y, RESET });
            return false;
        }
    }
}
