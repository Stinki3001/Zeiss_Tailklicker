package com.costr.tailklicker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import com.costr.tailklicker.TailklickerApplication;

/**
 * HighscoreListener.java
 *
 * @author Costr
 */
public class HighscoreListener implements ActionListener, Notation{

    HighscoreFrame highscoreFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        highscoreFrame = new HighscoreFrame();
        
        System.err.println("HighscoreListener: " + TailklickerApplication.getPlayerList().size() + " Spieler im Highscore");
        if (TailklickerApplication.getPlayerList().contains(TailklickerApplication.getPlayer())) {
            LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist bereits im Highscore.{2}",
                    new Object[] { GREEN, TailklickerApplication.getPlayer().getName(), RESET });
        } else {
            TailklickerApplication.addPlayerToList(TailklickerApplication.getPlayer());
        }

        highscoreFrame.dispalyHighscore(TailklickerApplication.getPlayerList());
        highscoreFrame.write(TailklickerApplication.getPlayerList());

    }
}
