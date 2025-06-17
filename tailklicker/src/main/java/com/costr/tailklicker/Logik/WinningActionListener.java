package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import com.costr.tailklicker.TailklickerApplication;
import com.costr.tailklicker.GUI.SwingGUI;

/**
 * @author Costr
 */
public class WinningActionListener implements ActionListener {
    Logger logger = Logger.getLogger(WinningActionListener.class.getName());
    @Override
    public void actionPerformed(ActionEvent e) {
        logger.info("Restarting game...");
        SwingGUI gui = new SwingGUI();
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols());
        Winning.winFrame.dispose();
        logger.info("Game restarted successfully.");

    }
}
