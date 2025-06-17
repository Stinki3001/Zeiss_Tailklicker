package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.GUI.Kachel;
import static com.costr.tailklicker.Logik.InvertedClick.invert;

public class KachelListener implements ActionListener, KlickZähler {

    private Kachel kachel;
    public static Kachel[][] kachelGroup;
    private static int count;

    public KachelListener(Kachel[][] kachelGroup, Kachel kachel) {
        KachelListener.kachelGroup = kachelGroup;
        this.kachel = kachel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        invert(kachel);
        if (Winning.checkWinCondition(kachelGroup)) {
            System.err.println("You win!");
            Winning.createWinningMessage();
        } else {
            increment();
        }

    }

    @Override
    public void increment() {
        count++;
    }

    @Override
    public void reset() {
        count = 0;
    }

    public static int getCount() {
        return count;
    }

}