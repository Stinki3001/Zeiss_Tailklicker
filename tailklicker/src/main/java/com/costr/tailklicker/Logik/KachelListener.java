package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.GUI.Kachel;
import static com.costr.tailklicker.Logik.InvertedClick.invert;
import com.costr.tailklicker.TailklickerApplication;

public class KachelListener implements ActionListener, KlickZähler {

    private Kachel kachel;
    public static Kachel[][] kachelGroup;

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
        TailklickerApplication.getPlayer().setCount(TailklickerApplication.getPlayer().getCount()+1);
    }

    @Override
    public void reset() {
        TailklickerApplication.getPlayer().setCount(0);
    }

    public static int getCount() {
        return TailklickerApplication.getPlayer().getCount();
    }

}