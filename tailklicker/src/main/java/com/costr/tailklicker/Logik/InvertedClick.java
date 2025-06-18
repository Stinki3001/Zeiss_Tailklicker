package com.costr.tailklicker.Logik;

import java.awt.Color;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Kachel;
import com.costr.tailklicker.GUI.Notation;

public class InvertedClick implements Notation {

    static void invert(Kachel kachel) {
        if (Kachel.istInFeld(kachel.getX(), kachel.getY())) {
            kachel.isInverted = !kachel.isInverted;
            if (kachel.isInverted) {
                kachel.getButton().setBackground(Color.BLACK);
            } else if (!kachel.isInverted) {
                kachel.getButton().setBackground(Color.WHITE);
            }
        } else {
            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
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

                LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                        new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
                return;
            }
        }

    }
}
