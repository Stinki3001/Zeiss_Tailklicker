package com.costr.tailklicker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.Logik.KachelListener;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.TailklickerApplication;

/**
 * HighscoreListener.java
 *
 * @author Costr
 */
public class HighscoreListener implements ActionListener {

    HighscoreFrame highscoreFrame;
    private final int id = TailklickerApplication.player.getID();
    private final String name = TailklickerApplication.player.getName();
    private final int count = KachelListener.getCount();
    private final Schwierigkeit schwierigkeit = TailklickerApplication.player.getLevel();
    String level = schwierigkeit.asString();

    @Override
    public void actionPerformed(ActionEvent e) {
        highscoreFrame = new HighscoreFrame();
        System.out.println(level + " " + id + " " + name + " " + count);
        highscoreFrame.saveNewHighscore(level, id, name, count);
        highscoreFrame.dispalyHighscore(level, id, name, count);

    }
}
