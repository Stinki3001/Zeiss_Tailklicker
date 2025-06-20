package com.costr.tailklicker.GUI.FXGUI;

import java.util.logging.Level;

import com.costr.tailklicker.GUI.GUI;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.Kachel;
import com.costr.tailklicker.Logik.KachelListener;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.Logik.SchwierigkeitenListener;
import com.costr.tailklicker.TailklickerApplication;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXGUI implements Notation {

    protected static Stage mainStage;
    private Scene startScene;
    private ComboBox<Schwierigkeit> schwierigkeitenMenu;
    private GridPane gridPane;

    public void init(int rows, int cols) {
        // this.mainStage = primaryStage;
        LOGGER.log(Level.INFO, "{0}Initializing AWT GUI with {1} rows and {2} columns.{3}",
                new Object[] { GREEN, rows, cols, RESET });
        if (startScene != null) {
            LOGGER.log(Level.INFO, "{0}Disposing of the old start frame...{1}", new Object[] { GREEN, RESET });
            startScene.getWindow().hide();
        }
        createGameStage();
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
            init(TailklickerApplication.getRows(), TailklickerApplication.getCols());
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
        BorderPane root = new BorderPane();
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        addMenuBar(root);
        setGrid(TailklickerApplication.getRows(), TailklickerApplication.getCols());

        root.setCenter(gridPane);

        Scene gameScene = new Scene(root, 800, 800);
        mainStage.setTitle("Tailklicker");
        gameScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        mainStage.setScene(gameScene);
        mainStage.show();
    }

    private void setGrid(int rows, int cols) {
         Kachel[][] kachelGroup = new Kachel[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Kachel kachel = new Kachel(i, j);
                kachelGroup[i][j] = kachel;
                gridPane.add(tileButton, j, i);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                kachelGroup[i][j].setNeighbours(kachelGroup, kachelGroup[i][j]);
                kachelGroup[i][j].getButton().setOnAction(new KachelListener(kachelGroup, kachelGroup[i][j]));
                
            }
        }
    }

    private void addMenuBar(BorderPane root) {
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");

        schwierigkeitenMenu = new ComboBox<>();
        schwierigkeitenMenu.getItems().addAll(Schwierigkeit.values());
        schwierigkeitenMenu.setValue(TailklickerApplication.getPlayer().getLevel());
        schwierigkeitenMenu.setOnAction(e -> {
            new SchwierigkeitenListener()
                    .actionPerformed(schwierigkeitenMenu.getValue());
        });

        CustomMenuItem comboMenuItem = new CustomMenuItem(schwierigkeitenMenu);
        comboMenuItem.setHideOnClick(false);

        gameMenu.getItems().add(comboMenuItem);
        menuBar.getMenus().add(gameMenu);
        root.setTop(menuBar);
    }
}