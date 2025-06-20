package com.costr.tailklicker.GUI.SwingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.GUI;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

/**
 * @author Costr
 */
public class WinningActionListener implements ActionListener,Notation {
    @Override
    public void actionPerformed(ActionEvent e) {
        LOGGER.log(Level.INFO, "{0}Der Spieler {1} hat das Spiel gewonnen!{2}",
                new Object[] { BOLD_BLUE, TailklickerApplication.getPlayer().getName(), RESET });
        GUI gui = new GUI();
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols(), TailklickerApplication.getGUIType());
        Winning.winFrame.dispose();
        LOGGER.log(Level.INFO, "{0}Das Spiel wurde zurückgesetzt.{1}",
                new Object[] { BOLD_BLUE, RESET });

    }
}
