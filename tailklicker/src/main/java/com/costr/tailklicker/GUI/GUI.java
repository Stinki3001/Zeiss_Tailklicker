package com.costr.tailklicker.GUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.FXGUI.FXGUI;
import com.costr.tailklicker.GUI.SwingGUI.SwingGUI;
import com.costr.tailklicker.TailklickerApplication;

public class GUI implements Notation {

    public enum Type {
        FX, SWING
    }

    public void init(int rows, int cols, Type guiType) {

        TailklickerApplication.getPlayer().setCount(0);
        TailklickerApplication.setRows(rows);
        TailklickerApplication.setCols(cols);
        LOGGER.log(Level.INFO, "{0}Initializing {1} with {2} rows and {3} columns.{4}",
                new Object[] { GREEN, guiType, rows, cols, RESET });

        switch (guiType) {
            case FX -> new FXGUI().init(rows, cols);
            case SWING -> new SwingGUI().init(rows, cols);

            default -> LOGGER.log(Level.WARNING, "{0}Unsupported GUI type: {1}{2}",
                    new Object[] { BOLD_RED, guiType, RESET });
        }
    }

    public void createStartFrame(Type guiType) {
        LOGGER.log(Level.INFO, "{0}Creating start frame for {1}.{2}",
                new Object[] { GREEN, guiType, RESET });

        switch (guiType) {
            case FX -> TailklickerApplication.showGUI();
            case SWING -> new SwingGUI().createStartframe();

            default -> LOGGER.log(Level.WARNING, "{0}Unsupported GUI type: {1}{2}",
                    new Object[] { BOLD_RED, guiType, RESET });
        }
    }
}