package com.costr.tailklicker.Logik;

import java.awt.Color;

import com.costr.tailklicker.GUI.Kachel;

public class InvertedClick {

    static void invert(Kachel[][] kachelGroup, Kachel kachel) {
        if (kachel.getY() > 3 || kachel.getX() > 3 || kachel.getY() < 0 || kachel.getX() < 0) {
            System.err.println("Kachel is out of bounds");
            return;
        } else {
            kachel.isInverted = !kachel.isInverted;
            if (kachel.isInverted){
                kachel.getButton().setBackground(Color.BLACK);
            } else if (!kachel.isInverted) {
                kachel.getButton().setBackground(Color.WHITE);
            }

            invert(kachelGroup, kachelGroup[kachel.getX() - 1][kachel.getY()]); // left
            invert(kachelGroup, kachelGroup[kachel.getX() + 1][kachel.getY()]); // right
            invert(kachelGroup, kachelGroup[kachel.getX()][kachel.getY() - 1]); // up
            invert(kachelGroup, kachelGroup[kachel.getX()][kachel.getY() + 1]); // down
        }
    }

}
