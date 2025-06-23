package com.costr.tailklicker.GUI.FXGUI;

import java.util.Set;

import com.costr.tailklicker.Logik.Player;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * HighscoreFrame.java
 *
 * @author Costr
 */
public class HighscoreFrameFX {
    private Stage highscoreStage;
    private Scene highscoreScene;

    public HighscoreFrameFX() {
        highscoreStage = new Stage();
        highscoreStage.setTitle("Highscores");
        highscoreStage.setWidth(400);
        highscoreStage.setHeight(300);
        highscoreStage.initModality(Modality.APPLICATION_MODAL);
    }

    // void saveNewHighscore(Set<Player> playerList) {
    // write(RESET);
    // }

    void dispalyHighscore(Set<Player> playerList) {
        TextArea highscoreTextField = new TextArea();
        highscoreTextField.setEditable(false);

        StringBuilder highscores = new StringBuilder();
        highscores.append("Name\tLevel\tCount\n");
        for (Player player : playerList) {

            highscores.append(player.getName()).append("\t")
                    .append(player.getCount()).append("\t")
                    .append(player.getLevel().asString()).append("\n");
        }
        highscoreTextField.setText(highscores.toString());

        VBox layout = new VBox(highscoreTextField);
        highscoreScene = new Scene(layout);

        highscoreStage.setScene(highscoreScene);
        highscoreStage.show();
    }
}
