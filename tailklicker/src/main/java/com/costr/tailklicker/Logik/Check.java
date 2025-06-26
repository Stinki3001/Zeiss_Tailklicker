package com.costr.tailklicker.Logik;

import java.util.LinkedHashSet;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

public class Check implements Notation{

    public static boolean checkWinCondition(Kachel[][] kachelGroup) {
        for (Kachel[] row : kachelGroup) {
            for (Kachel kachel : row) {
                if (!kachel.isInverted) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkIfPlayerNeedsOverride(LinkedHashSet<Player> playerList) {

        Player currentPlayer = TailklickerApplication.getPlayer();
        outer: for (Player oldPlayer : playerList) {
            if (oldPlayer.getName().equals(currentPlayer.getName())
                    && oldPlayer.getLevel().toString()
                            .equalsIgnoreCase(currentPlayer.getLevel().toString())) {
                LOGGER.log(Level.WARNING,
                        "{0}Der Spieler {1} mit dem Level: {2} ist bereits im Highscore.{3}",
                        new Object[] { YELLOW, currentPlayer.getName(),
                                currentPlayer.getLevel().toString().toUpperCase(),
                                RESET });
                if (oldPlayer.getCount() > currentPlayer.getCount()) {
                    LOGGER.log(Level.INFO,
                            "{0}Der Spieler {1} hat einen besseren Score ereicht.{2}",
                            new Object[] { BLAU, currentPlayer.getName(), RESET });
                    oldPlayer.setCount(currentPlayer.getCount());
                    LOGGER.log(Level.INFO, "{0}Highscore updated to {1}!{2}",
                            new Object[] { BLAU, currentPlayer.getCount(), RESET });
                    break outer;

                } else {
                    continue;
                }

            }
            return true;
        }
        return false;
    }
}