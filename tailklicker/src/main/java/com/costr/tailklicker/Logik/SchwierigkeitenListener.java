package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.GUI.SwingGUI;
import com.costr.tailklicker.TailklickerApplication;


/** 
 * @author Costr
 */

 public class SchwierigkeitenListener implements ActionListener, Notation{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Schwierigkeit selectedDifficulty = SwingGUI.getSelectedDifficulty();
        TailklickerApplication.getPlayer().setLevel(selectedDifficulty);
        int rows = selectedDifficulty.getRows();
        int cols = selectedDifficulty.getCols();
        TailklickerApplication.getPlayer().setCount(0);
        LOGGER.log(Level.INFO, "{0}Counter zurückgesetzt. {1}",
                new Object[] { GREEN, RESET});
        
        SwingGUI gui = new SwingGUI();
        TailklickerApplication.setRows(rows);
        TailklickerApplication.setCols(cols);
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols());
        
        LOGGER.log(Level.INFO, "{0}Die Schwierigkeit wurde auf {1} gesetzt.{2}",
                new Object[] { GREEN, selectedDifficulty, RESET });
    }
 }