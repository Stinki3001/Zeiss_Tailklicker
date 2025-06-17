package com.costr.tailklicker.Logik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.costr.tailklicker.GUI.SwingGUI;
import com.costr.tailklicker.TailklickerApplication;


/** 
 * @author Costr
 */

 public class SchwierigkeitenListener implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Schwierigkeit selectedDifficulty = SwingGUI.getSelectedDifficulty();
        TailklickerApplication.player.setLevel(selectedDifficulty);
        int rows = selectedDifficulty.getRows();
        int cols = selectedDifficulty.getCols();
        
        SwingGUI gui = new SwingGUI();
        TailklickerApplication.setRows(rows);
        TailklickerApplication.setCols(cols);
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols());
        
        System.out.println("Selected difficulty: " + selectedDifficulty + " with rows: " + rows + " and cols: " + cols);
    }
 }