package com.costr.tailklicker.GUI.FXGUI;


import java.util.Set;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.Player;
import com.costr.tailklicker.TailklickerApplication;

/**
 * HighscoreListener.java
 *
 * @author Costr
 */
public class HighscoreListenerFX implements Notation {

        HighscoreFrameFX highscoreScene;

    
    public void actionPerformed() {
        highscoreScene = new HighscoreFrameFX();
        Set<Player> playerList = TailklickerApplication.getPlayerList();
        Player currentPlayer = TailklickerApplication.getPlayer();

        LOGGER.log(Level.INFO, "{0}HighscoreListener: {1} Spieler im Highscore{2}",
                new Object[] { BLAU, playerList.size(), RESET });
        Boolean add = false;
        outer:
            for (Player oldPlayer : playerList) {
                if (oldPlayer.getName().equals(currentPlayer.getName())
                 && oldPlayer.getLevel().toString().equalsIgnoreCase(currentPlayer.getLevel().toString())) {
                    LOGGER.log(Level.WARNING, "{0}Der Spieler {1} mit dem Level: {2} ist bereits im Highscore.{3}",
                            new Object[] { YELLOW, currentPlayer.getName(),
                                    currentPlayer.getLevel().toString().toUpperCase(), RESET });
                    if (oldPlayer.getCount() > currentPlayer.getCount()) {
                        LOGGER.log(Level.INFO, "{0}Der Spieler {1} hat einen besseren Score ereicht.{2}",
                                new Object[] { BLAU, currentPlayer.getName(), RESET });
                        oldPlayer.setCount(currentPlayer.getCount());
                        LOGGER.log(Level.INFO, "{0}Highscore updated to {1}!{2}",
                                new Object[] { BLAU, currentPlayer.getCount(), RESET });
                        break outer;

                } else {
                    continue;
                }

            }
            add = true;
        }
        if(add){
                LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist nicht im Highscore.{2}",
                    new Object[] { BLAU, TailklickerApplication.getPlayer().getName(), RESET });
            TailklickerApplication.addPlayerToList(TailklickerApplication.getPlayer());
            LOGGER.log(Level.INFO, "{0}Der Spieler {1} wurde zum Highscore hinzugefügt.{2}",
                    new Object[] { BLAU, TailklickerApplication.getPlayer().getName(), RESET });
        }
        highscoreScene.dispalyHighscore(playerList);
        highscoreScene.write(playerList);

    }
}
