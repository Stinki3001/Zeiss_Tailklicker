package com.costr.tailklicker.GUI.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class HighscoreListener implements ActionListener, Notation {

        HighscoreFrame highscoreFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        highscoreFrame = new HighscoreFrame();
        Set<Player> playerList = TailklickerApplication.getPlayerList();
        Player currentPlayer = TailklickerApplication.getPlayer();

        LOGGER.log(Level.INFO, "{0}HighscoreListener: {1} Spieler im Highscore{2}",
                new Object[] { BLAU, playerList.size(), RESET });
        

    }
}
