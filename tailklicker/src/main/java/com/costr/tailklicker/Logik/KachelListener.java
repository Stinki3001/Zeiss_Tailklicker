package com.costr.tailklicker.Logik;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

public class KachelListener implements KlickZähler, Notation {

    private Kachel kachel;
    public static Kachel[][] kachelGroup;

    public KachelListener(Kachel[][] kachelGroup, Kachel kachel) {
        KachelListener.kachelGroup = kachelGroup;
        this.kachel = kachel;

    }
    public void actionPerformed(){
         InvertedClick.invert(kachel);
        for(Kachel elm : kachel.getNeighbours()){
            InvertedClick.invert(elm);
        }
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