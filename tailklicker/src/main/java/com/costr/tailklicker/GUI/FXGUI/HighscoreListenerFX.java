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

                LOGGER.log(Level.INFO, "{0}HighscoreListener: {1} Spieler im Highscore{2}",
                                new Object[] { BLAU, playerList.size(), RESET });

                highscoreScene.dispalyHighscore(playerList);

        }
}
