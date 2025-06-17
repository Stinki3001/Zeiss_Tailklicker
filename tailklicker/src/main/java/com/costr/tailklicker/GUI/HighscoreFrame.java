package com.costr.tailklicker.GUI;

import java.awt.TextArea;

import javax.swing.JFrame;

import com.costr.tailklicker.Logik.DateiVerwaltung;

/**
 * HighscoreFrame.java
 *
 * @author Costr
 */
public class HighscoreFrame {

    private DateiVerwaltung dateiVerwaltung;
    private JFrame highscoreFrame;

    public HighscoreFrame() {
        dateiVerwaltung = new DateiVerwaltung();
        highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highscoreFrame.setSize(400, 300);
        highscoreFrame.setLocationRelativeTo(null);

        highscoreFrame.setVisible(true);
    }

    void loadHighscore() {
        dateiVerwaltung.load();
    }

    void saveNewHighscore(String level, int ID, String name, int count) {
        dateiVerwaltung.write("Id: " + ID + "\n" +
                "Name: " + name + "\n" +
                "Count: " + count + "\n" +
                "Level: " + level + "\n");
    }

    void dispalyHighscore(String level, int ID, String name, int count) {
        TextArea highscore = new TextArea();
        highscore.setEditable(false);
        highscore.setText("Id: " + ID + "\n" +
                "Name: " + name + "\n" +
                "Count: " + count + "\n" +
                "Level: " + level + "\n");
        highscoreFrame.add(highscore);
        highscoreFrame.invalidate();
    }
}
