package com.costr.tailklicker.Logik;

import java.awt.Color;

import com.costr.tailklicker.GUI.Kachel;

public class InvertedClick {

    static void invert(Kachel kachel) {
        if (kachel.getY() > 3 || kachel.getX() > 3 || kachel.getY() < 0 || kachel.getX() < 0) {
            System.err.println("Kachel is out of bounds");
            return;
        } else {
            kachel.isInverted = !kachel.isInverted;
            if (kachel.isInverted) {
                kachel.getButton().setBackground(Color.BLACK);
            } else if (!kachel.isInverted) {
                kachel.getButton().setBackground(Color.WHITE);
            }
        }

        for (Kachel neighbour : kachel.getNeighbours()) {
            if (neighbour.getY() > 3 || neighbour.getX() > 3 || neighbour.getY() < 0 || neighbour.getX() < 0) {
                System.err.println("Kachel is out of bounds");
                return;
            } else {
                neighbour.isInverted = !neighbour.isInverted;
                if (neighbour.isInverted) {
                    neighbour.getButton().setBackground(Color.BLACK);
                } else if (!neighbour.isInverted) {
                    neighbour.getButton().setBackground(Color.WHITE);
                }
            }
        }

    }
}
