package com.costr.tailklicker.GUI.FXGUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.GUI;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.TailklickerApplication;

/**
 * @author Costr
 */
public class RestartListener implements Notation {


    public void actionPerformed() {
        GUI gui = new GUI();
        gui.init(TailklickerApplication.getRows(), TailklickerApplication.getCols(), TailklickerApplication.getGUIType());
        if(WinningFX.winStage != null) {
            WinningFX.winStage.close();
        }
        LOGGER.log(Level.INFO, "{0}Das Spiel wurde zurückgesetzt.{1}",
                new Object[] { BOLD_BLUE, RESET });

    }
}
