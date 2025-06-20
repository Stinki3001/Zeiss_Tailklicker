package com.costr.tailklicker.GUI.SwingGUI;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.costr.tailklicker.GUI.SwingGUI.HighscoreListener;
import com.costr.tailklicker.Logik.Kachel;
import com.costr.tailklicker.Logik.KachelListener;


/**
 * @author Costr
 */
public class Winning {

    static JFrame winFrame;

    public static boolean checkWinCondition(Kachel[][] kachelGroup) {
        for (Kachel[] row : kachelGroup) {
            for (Kachel kachel : row) {
                if (!kachel.isInverted) {
                    return false;
                }
            }
        }
        return true;
    }

    public static JFrame createWinningMessage() {
        winFrame = new JFrame("You Win!");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setPreferredSize(new Dimension(350, 350));
        winFrame.setResizable(true);
        winFrame.setLocationRelativeTo(null);
        JPanel winnMessagePanel = new JPanel();
        

        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new WinningActionListener());
        JButton highscoreButton = new JButton("Show Highscores");
        highscoreButton.addActionListener(new HighscoreListener());
        winnMessagePanel.add(restartButton);
        winnMessagePanel.add(highscoreButton);

        JTextArea winMessage = new JTextArea("""
                Congratulations! You have won the game!
                You can restart the game by clicking the button below.
                You won with a total of: 
                """ + KachelListener.getCount() + " Clicks");
        winMessage.setPreferredSize(new Dimension(300, 250));
        winMessage.setEditable(false);
        winMessage.setLineWrap(true);
        winMessage.setWrapStyleWord(true);
        winMessage.setOpaque(true);

 
        winnMessagePanel.add(winMessage);

        winFrame.add(winnMessagePanel);
        winFrame.pack();
        winFrame.setVisible(true);
        return winFrame;

    }
}
