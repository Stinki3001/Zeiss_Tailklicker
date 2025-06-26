package com.costr.tailklicker.GUI.FXGUI;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import com.costr.tailklicker.Logik.Player;
import com.costr.tailklicker.TailklickerApplication;

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

    void dispalyHighscore() {
        StringBuilder highscores = new StringBuilder();
        TextArea highscoreTextField = new TextArea();
        TailklickerApplication.getPlayerList().forEach((schwierigkeit, spielerSet) -> {
            LinkedHashSet<Player> sortedsSpielerSet = spielerSet.stream()
                    .sorted(Comparator.comparingInt(Player::getCount))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
            highscoreTextField.setEditable(false);

            highscores.append("Name\tLevel\tCount\n");
            for (Player player : sortedsSpielerSet) {

                highscores.append(player.getName()).append("\t")
                        .append(player.getCount()).append("\t")
                        .append(player.getLevel().asString()).append("\n");
            }
        });
        highscoreTextField.setText(highscores.toString());

        VBox layout = new VBox(highscoreTextField);
        highscoreScene = new Scene(layout);

        highscoreStage.setScene(highscoreScene);
        highscoreStage.show();

    }
}
