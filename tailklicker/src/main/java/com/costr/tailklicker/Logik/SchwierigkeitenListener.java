package com.costr.tailklicker.Logik;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.GUI;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;


/** 
 * @author Costr
 */

 public class SchwierigkeitenListener implements Notation {
    
        
    
    public void actionPerformed(Schwierigkeit event) {
        LOGGER.log(Level.INFO, "{0}SchwierigkeitenListener: Schwierigkeit {1} wurde ausgewählt.{2}",
                new Object[] { BOLD_BLUE, event, RESET });
        TailklickerApplication.getPlayer().setLevel(event);
        TailklickerApplication.getPlayer().setCount(0);
        LOGGER.log(Level.INFO, "{0}Counter zurückgesetzt. {1}",
                new Object[] { GREEN, RESET});
        
        GUI gui = new GUI();
        TailklickerApplication.setRows(TailklickerApplication.getPlayer().getLevel().getRows());
        TailklickerApplication.setCols(TailklickerApplication.getPlayer().getLevel().getCols());
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols(), TailklickerApplication.getGUIType());
        
        LOGGER.log(Level.INFO, "{0}Die Schwierigkeit wurde auf {1} gesetzt.{2}",
                new Object[] { GREEN, TailklickerApplication.getPlayer().getLevel().asString(), RESET });
    }
 }