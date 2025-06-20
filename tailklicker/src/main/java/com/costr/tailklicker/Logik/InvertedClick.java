package com.costr.tailklicker.Logik;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;

public class InvertedClick implements Notation {

    static void invert(Kachel kachel) {
        if (Kachel.istInFeld(kachel.getX(), kachel.getY())) {
            kachel.isInverted = !kachel.isInverted;
        } else {
            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { YELLOW, kachel.getX(), kachel.getY(), RESET });
            return;
        }

        for (Kachel neighbour : kachel.getNeighbours()) {
            if (Kachel.istInFeld(neighbour.getX(), neighbour.getY())) {
            } else {

                LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                        new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
                return;
            }
        }

    }
}
