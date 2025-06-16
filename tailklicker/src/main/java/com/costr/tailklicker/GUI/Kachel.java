package com.costr.tailklicker.GUI;

import java.util.ArrayList;

import javax.swing.JButton;

public class Kachel {

    private final int x;
    private final int y;
    public boolean isInverted;
    private final JButton button;
    private ArrayList<Kachel> neighbours;

    public Kachel(int x, int y) {
        this.x = x;
        this.y = y;
        this.isInverted = false;
        this.button = new JButton("Button " + (x) + "," + (y));

    }

    public JButton getButton() {
        return button;
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
            System.err.println("Kachel is out of bounds");
            return;
        }

    }

    public ArrayList<Kachel> getNeighbours() {
        return neighbours;
    }

    public boolean istInFeld(int x, int y) {
        if (y < 3 & x < 3 & y >= 0 & x >= 0) {
            return true;
        } else {

            System.err.println("Kachel is out of bounds");
            return false;
        }
    }
}
