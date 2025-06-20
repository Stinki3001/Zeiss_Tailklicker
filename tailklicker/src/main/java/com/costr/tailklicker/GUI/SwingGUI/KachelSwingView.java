package com.costr.tailklicker.GUI.SwingGUI;

import com.costr.tailklicker.Logik.Kachel;
import com.costr.tailklicker.Logik.KachelListener;

import javax.swing.JButton;

import java.awt.Color;
import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;

public class KachelSwingView implements Notation {

    private final Kachel kachel;
    private final JButton button;
    private final KachelSwingView[][] views;

    public KachelSwingView(KachelSwingView[][] views, Kachel kachel) {
        this.views = views;
        this.kachel = kachel;
        this.button = new JButton();
        button.setText("Button: " + kachel.getX() + kachel.getY());
    }

    public void update() {

        if (Kachel.istInFeld(kachel.getX(), kachel.getY())) {
            views[kachel.getX()][kachel.getY()].updateStyle();
            if (Kachel.istInFeld(kachel.getX() - 1, kachel.getY())) {
                views[kachel.getX() - 1][kachel.getY()].updateStyle(); // left
            }
            if (Kachel.istInFeld(kachel.getX() + 1, kachel.getY())) {
                views[kachel.getX() + 1][kachel.getY()].updateStyle(); // right
            }
            if (Kachel.istInFeld(kachel.getX(), kachel.getY() - 1)) {
                views[kachel.getX()][kachel.getY() - 1].updateStyle(); // up
            }
            if (Kachel.istInFeld(kachel.getX(), kachel.getY() + 1)) {
                views[kachel.getX()][kachel.getY() + 1].updateStyle(); // down
            }
        } else {
            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
        }
    }

    void updateStyle() {
        if (kachel.isInverted) {
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
        }
        button.invalidate();

    }

    public JButton getButton() {
        return button;
    }

    public Kachel getKachel() {
        return kachel;
    }

    public void refresh() {
        updateStyle();
    }
}
