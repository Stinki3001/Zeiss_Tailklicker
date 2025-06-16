package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.GUI.SwingGUI;

/**
 * @author Costr
 */
public class WinningActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingGUI gui = new SwingGUI();
        gui.init(SwingGUI.getRows(), SwingGUI.getCols());

    }
}
