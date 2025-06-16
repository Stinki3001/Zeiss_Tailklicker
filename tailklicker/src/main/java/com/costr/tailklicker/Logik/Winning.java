package com.costr.tailklicker.Logik;

import java.awt.Dimension;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.costr.tailklicker.GUI.Kachel;

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
        winFrame.setPreferredSize(new Dimension(300, 300));
        winFrame.setResizable(true);
        winFrame.setLocationRelativeTo(null);
        JPanel winnMessagePanel = new JPanel();

        JButton restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new WinningActionListener());
        winnMessagePanel.add(restartButton);

        TextField winMessage = new TextField("Congratulations! You have won the game!");
        winMessage.setPreferredSize(new Dimension(250, 250));
        winMessage.setEditable(false);
        winnMessagePanel.add(winMessage);

        winFrame.add(winnMessagePanel);
        winFrame.pack();
        winFrame.setVisible(true);
        return winFrame;

    }
}
