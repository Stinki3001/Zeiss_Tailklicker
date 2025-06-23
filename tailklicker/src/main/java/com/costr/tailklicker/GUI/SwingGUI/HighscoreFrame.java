package com.costr.tailklicker.GUI.SwingGUI;

import java.awt.TextArea;
import java.util.Set;

import javax.swing.JFrame;

import com.costr.tailklicker.Logik.Player;

/**
 * HighscoreFrame.java
 *
 * @author Costr
 */
public class HighscoreFrame {
    private JFrame highscoreFrame;

    public HighscoreFrame() {
        highscoreFrame = new JFrame("Highscores");
        highscoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        highscoreFrame.setSize(400, 300);
        highscoreFrame.setLocationRelativeTo(null);

        highscoreFrame.setVisible(true);
    }

    // void saveNewHighscore(Set<Player> playerList) {
    // write(RESET);
    // }

    void dispalyHighscore(Set<Player> playerList) {
        TextArea highscore = new TextArea();
        highscore.setEditable(false);
        for (Player player : playerList) {
            highscore.append("Name: " + player.getName() + "\n" +
                    "Count: " + player.getCount() + "\n" +
                    "Level: " + player.getLevel().asString() + "\n\n");
        }
        highscoreFrame.add(highscore);
        highscoreFrame.invalidate();
    }
}
