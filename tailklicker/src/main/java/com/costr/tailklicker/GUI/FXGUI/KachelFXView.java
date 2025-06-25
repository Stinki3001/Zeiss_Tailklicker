package com.costr.tailklicker.GUI.FXGUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.Kachel;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class KachelFXView implements Notation {

    private final Kachel kachel;
    private final Button button;
    private final KachelFXView[][] views;

    public KachelFXView(KachelFXView[][] views, Kachel kachel) {
        this.views = views;
        this.kachel = kachel;
        this.button = new QuadraticButton("[" + kachel.getX() + "," + kachel.getY() + "]");
        // this.button.setPrefSize(60, 60);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        updateStyle();
    }

    public void update() {
        if (Kachel.istInFeld(kachel.getX(), kachel.getY())) {
            views[kachel.getX()][kachel.getY()].updateStyle();
            if (Kachel.istInFeld(kachel.getX() - 1, kachel.getY())) {
                views[kachel.getX() - 1][kachel.getY()].updateStyle(); // oben
            }
            if (Kachel.istInFeld(kachel.getX() + 1, kachel.getY())) {
                views[kachel.getX() + 1][kachel.getY()].updateStyle(); // unten
            }
            if (Kachel.istInFeld(kachel.getX(), kachel.getY() - 1)) {
                views[kachel.getX()][kachel.getY() - 1].updateStyle(); // links
            }
            if (Kachel.istInFeld(kachel.getX(), kachel.getY() + 1)) {
                views[kachel.getX()][kachel.getY() + 1].updateStyle(); // rechts
            }
        } else {
            LOGGER.log(Level.WARNING, "{0}Kachel {1},{2} is out of bounds.{3}",
                    new Object[] { BRIGHT_RED, kachel.getX(), kachel.getY(), RESET });
        }
    }

    void updateStyle() {
        if (kachel.isInverted) {
            button.getStyleClass().remove("button-white");
            button.getStyleClass().add("button-black");
        } else {
            button.getStyleClass().remove("button-black");
            button.getStyleClass().add("button-white");
        }
    }

    public Button getButton() {
        return button;
    }

    public Kachel getKachel() {
        return kachel;
    }

    public void refresh() {
        updateStyle();
    }
}

class QuadraticButton extends Button {

    public QuadraticButton() {
        super();
    }

    public QuadraticButton(String text) {
        super(text);
    }

}
