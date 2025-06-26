package com.costr.tailklicker.GUI.FXGUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
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

                LOGGER.log(Level.INFO, "{0}HighscoreListener: {1} Spieler im Highscore{2}",
                                new Object[] { BLAU, TailklickerApplication.getPlayerList().size(), RESET });

                highscoreScene.dispalyHighscore();

        }
}
