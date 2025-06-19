package com.costr.tailklicker.GUI.AWTGUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.TailklickerApplication;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AWTGUI implements Notation {

    static Stage mainStage;
    private Scene startScene;
    private ComboBox<Schwierigkeit> schwierigkeitenMenu;

    public void init(int rows, int cols) {
        // this.mainStage = primaryStage;
        LOGGER.log(Level.INFO, "{0}Initializing AWT GUI with {1} rows and {2} columns.{3}",
                new Object[] { GREEN, rows, cols, RESET });
        if (startScene != null) {
            LOGGER.log(Level.INFO, "{0}Disposing of the old start frame...{1}", new Object[] { GREEN, RESET });
            startScene.getWindow().hide();
        }
        createGameStage();
        setGrid(rows, cols);
        addMenue();

    }

    public void createStartStage(Stage stage) {
        LOGGER.log(Level.INFO, "{0}Creating start stage...{1}", new Object[] { GREEN, RESET });
        TextArea intro = new TextArea("""
                Welcome to Tailklicker!
                Click on the tiles to invert them.
                Try to invert all tiles with the least clicks possible.
                Select the difficulty from the menu above.
                Have fun!
                """);
        intro.setWrapText(true);
        intro.setEditable(false);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your Name");

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            TailklickerApplication.getPlayer().setName(nameField.getText());
            createGameStage();
            LOGGER.log(Level.INFO, "{0}Starting game for player: {1}{2}",
                    new Object[] { GREEN, TailklickerApplication.getPlayer().getName(), RESET });
            // startScene.getWindow().hide();
        });

        VBox layout = new VBox(10, intro, nameField, startButton);
        layout.setPadding(new Insets(10));
        layout.setPrefWidth(400);
        layout.setPrefHeight(200);

        Scene scene = new Scene(layout);
        stage.setTitle("Tailklicker - Start");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();

    }

    private void createGameStage() {
        LOGGER.log(Level.SEVERE, "{0}createGameStage not supported yet.{1}",
                new Object[] { RED, RESET });
    }

    private void setGrid(int rows, int cols) {
        LOGGER.log(Level.SEVERE, "{0}setGrid not supported yet.{1}",
                new Object[] { RED, RESET });
    }

    private void addMenue() {
        LOGGER.log(Level.SEVERE, "{0}addMenu not supported yet.{1}",
                new Object[] { RED, RESET });
    }
}