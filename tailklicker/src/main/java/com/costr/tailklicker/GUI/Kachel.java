package com.costr.tailklicker.GUI;

import java.util.ArrayList;

import javax.swing.JButton;

public class Kachel {
    private int x;
    private int y;
    private boolean isClicked;
    public boolean isInverted;
    private JButton button;
    // public static Kachel[][] kachelGroup;
    public ArrayList<Kachel> neighbours;

    public Kachel(int x, int y) {
        this.x = x;
        this.y = y;
        this.isClicked = false;
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
    
    public void getNeighbour(Kachel[][] kachelGroup, Kachel kachel) {
        neighbours = new ArrayList<>();
        if (kachel.getY() > 3 || kachel.getX() > 3 || kachel.getY() < 0 || kachel.getX() < 0) {
            System.err.println("Kachel is out of bounds");
            return;
        } else {
            neighbours.add(kachelGroup[kachel.getX() - 1][kachel.getY()]); // left
            neighbours.add(kachelGroup[kachel.getX() + 1][kachel.getY()]); // right
            neighbours.add(kachelGroup[kachel.getX()][kachel.getY() - 1]); // up
            neighbours.add(kachelGroup[kachel.getX()][kachel.getY() + 1]); // down
        }

    }
}
