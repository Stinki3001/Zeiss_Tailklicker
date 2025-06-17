package com.costr.tailklicker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.Logik.KachelListener;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.GUI.SwingGUI;

/**
 * HighscoreListener.java
 *
 * @author Costr
 */
public class HighscoreListener implements ActionListener {

    HighscoreFrame highscoreFrame;
    int count = KachelListener.getCount();
    String name = "Fietje";
    Schwierigkeit schwierigkeit = SwingGUI.getSelectedDifficulty();
    String level = schwierigkeit.asString();
    int id = 1;

    @Override
    public void actionPerformed(ActionEvent e) {
        highscoreFrame = new HighscoreFrame();
        System.out.println(level + " " + id + " " + name + " " + count);
        highscoreFrame.saveNewHighscore(level, id, name, count);
        highscoreFrame.dispalyHighscore(level, id, name, count);

    }
}
