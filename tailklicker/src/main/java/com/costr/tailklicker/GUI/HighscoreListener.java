package com.costr.tailklicker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

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

        LOGGER.log(Level.INFO, "{0}HighscoreListener: {1} Spieler im Highscore{2}",
                new Object[] { BLAU, TailklickerApplication.getPlayerList().size(), RESET });
        outer:
        if (TailklickerApplication.getPlayerList().contains(TailklickerApplication.getPlayer())) {
            LOGGER.log(Level.WARNING, "{0}Der Spieler {1} ist bereits im Highscore.{2}",
                    new Object[] { YELLOW, TailklickerApplication.getPlayer().getName(), RESET });

        } else {
            for (Player oldPlayer : TailklickerApplication.getPlayerList()) {
                if (((oldPlayer.getName()).equals(TailklickerApplication.getPlayer().getName()))
                        && (oldPlayer.getLevel().toString()
                                .equalsIgnoreCase(TailklickerApplication.getPlayer().getLevel().toString()))) {
                    LOGGER.log(Level.WARNING, "{0}Der Spieler {1} mit dem Level: {2} ist bereits im Highscore.{3}",
                            new Object[] { YELLOW, TailklickerApplication.getPlayer().getName(),
                                    TailklickerApplication.getPlayer().getLevel().toString().toUpperCase(), RESET });
                    if (oldPlayer.getCount() > TailklickerApplication.getPlayer().getCount()) {
                        LOGGER.log(Level.INFO, "{0}Der Spieler {1} hat einen besseren Score ereicht.{2}",
                                new Object[] { BLAU, TailklickerApplication.getPlayer().getName(), RESET });
                        oldPlayer.setCount(TailklickerApplication.getPlayer().getCount());
                        LOGGER.log(Level.INFO, "{0}Highscore updated to {1}!{2}",
                                new Object[] { BLAU, TailklickerApplication.getPlayer().getCount(), RESET });
                        break outer;
                    } else
                        continue;
                } else {
                    continue;
                }

            }
            LOGGER.log(Level.INFO, "{0}Der Spieler {1} ist nicht im Highscore.{2}",
                    new Object[] { BLAU, TailklickerApplication.getPlayer().getName(), RESET });
            TailklickerApplication.addPlayerToList(TailklickerApplication.getPlayer());
            LOGGER.log(Level.INFO, "{0}Der Spieler {1} wurde zum Highscore hinzugefügt.{2}",
                    new Object[] { BLAU, TailklickerApplication.getPlayer().getName(), RESET });
        }
        highscoreFrame.dispalyHighscore(TailklickerApplication.getPlayerList());
        highscoreFrame.write(TailklickerApplication.getPlayerList());

    }
}
