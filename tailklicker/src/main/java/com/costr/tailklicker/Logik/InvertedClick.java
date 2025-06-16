package com.costr.tailklicker.Logik;

import java.awt.Color;

import com.costr.tailklicker.GUI.Kachel;

public class InvertedClick {

    static void invert(Kachel kachel) {
        if (Kachel.istInFeld(kachel.getX(), kachel.getY())) {
            kachel.isInverted = !kachel.isInverted;
            if (kachel.isInverted) {
                kachel.getButton().setBackground(Color.BLACK);
            } else if (!kachel.isInverted) {
                kachel.getButton().setBackground(Color.WHITE);
            }
        } else {
            System.err.println("Kachel is out of bounds");
            return;
        }

        for (Kachel neighbour : kachel.getNeighbours()) {
            if (Kachel.istInFeld(neighbour.getX(), neighbour.getY())) {
                neighbour.isInverted = !neighbour.isInverted;
                if (neighbour.isInverted) {
                    neighbour.getButton().setBackground(Color.BLACK);
                } else if (!neighbour.isInverted) {
                    neighbour.getButton().setBackground(Color.WHITE);
                }
            } else {

                System.err.println("Kachel is out of bounds");
                return;
            }
        }

    }
}
