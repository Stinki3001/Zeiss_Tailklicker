package com.costr.tailklicker.Logik;

import java.util.Set;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.FXGUI.WinningFX;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

public class KachelListener implements KlickZähler, Notation {

    private Kachel kachel;
    public static Kachel[][] kachelGroup;

    public KachelListener(Kachel[][] kachelGroup, Kachel kachel) {
        KachelListener.kachelGroup = kachelGroup;
        this.kachel = kachel;

    }

    public void actionPerformed() {
        InvertedClick.invert(kachel);
        for (Kachel elm : kachel.getNeighbours()) {
            InvertedClick.invert(elm);
        }

        if (Check.checkWinCondition(kachelGroup)) {
            LOGGER.log(Level.INFO, "{0}Gewonnen!{1}", new Object[] { GREEN, RESET });
            WinningFX.createWinningMessage();
            if (Check.checkIfPlayerNeedsOverride(TailklickerApplication.getPlayerList())) {
                LOGGER.log(Level.INFO, "{0}Highscore updated!{1}", new Object[] { YELLOW, RESET });
                Set<Player> playerList = TailklickerApplication.getPlayerList();
                Player currentPlayer = TailklickerApplication.getPlayer();
                if (playerList.contains(currentPlayer)) {
                    playerList.remove(currentPlayer);
                    TailklickerApplication.addPlayerToList(currentPlayer);
                    TailklickerApplication.dateiVerwalter.write(playerList);
                }
            } else {
                LOGGER.log(Level.INFO, "{0}Highscore not updated.{1}", new Object[] { YELLOW, RESET });
            }
        } else {
            increment();
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