package com.costr.tailklicker.GUI.SwingGUI;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.Kachel;
import com.costr.tailklicker.Logik.KlickZähler;
import com.costr.tailklicker.TailklickerApplication;

public class KachelListener implements KlickZähler, Notation {

    private Kachel kachel;
    public static Kachel[][] kachelGroup;

    public KachelListener(Kachel[][] kachelGroup, Kachel kachel) {
        KachelListener.kachelGroup = kachelGroup;
        this.kachel = kachel;

    }

    @Override
    public void increment() {
        TailklickerApplication.getPlayer().setCount(TailklickerApplication.getPlayer().getCount() + 1);
    }

    @Override
    public void reset() {
        TailklickerApplication.getPlayer().setCount(0);
    }

    public static int getCount() {

        return TailklickerApplication.getPlayer().getCount();
    }

}