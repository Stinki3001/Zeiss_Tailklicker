package com.costr.tailklicker.GUI.FXGUI;

import com.costr.tailklicker.Logik.KachelListener;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Costr
 */
public class WinningFX {

    static Stage winStage;
    static Scene winScene;

   

    public static void createWinningMessage() {
        winStage = new Stage();
        winStage.setTitle("You Win!");
        winStage.setOnCloseRequest(e -> {
            System.exit(0);
        });
        
        winStage.setWidth(600);
        winStage.setHeight(600);
        winStage.setResizable(true);
        winStage.initModality(Modality.APPLICATION_MODAL);

        BorderPane pane = new BorderPane();

        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> {
            new RestartListener().actionPerformed();
        });
        
        Button highscoreButton = new Button("Show Highscores");
        highscoreButton.setOnAction(e -> {
            new HighscoreListenerFX().actionPerformed();
        });

        pane.setTop(restartButton);
        pane.setBottom(highscoreButton);

        TextArea winMessage = new TextArea("""
                Congratulations! You have won the game!
                You can restart the game by clicking the button below.
                You won with a total of:
                """ + KachelListener.getCount() + " Clicks");
        winMessage.setWrapText(true);
        winMessage.setEditable(false);

        pane.setCenter(winMessage);

        winScene = new Scene(pane, 400, 400);
        winStage.setScene(winScene);
        winStage.show();
        

    }
}
