package com.costr.tailklicker.GUI.FXGUI;

import java.util.logging.Level;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXGUI implements Notation {

    protected static Stage mainStage;
    private Scene startScene;
    private ComboBox<Schwierigkeit> schwierigkeitenMenu;
    private GridPane gridPane;

    // 🔧 hinzugefügt: View-Array wie in SwingGUI
    private KachelFXView[][] kachelViews;

    public void init(int rows, int cols) {
        LOGGER.log(Level.INFO, "{0}Initializing FX GUI with {1} rows and {2} columns.{3}",
                new Object[] { GREEN, rows, cols, RESET });
        if (startScene != null) {
            LOGGER.log(Level.INFO, "{0}Disposing of the old start frame...{1}", new Object[] { GREEN, RESET });
            startScene.getWindow().hide();
        }
        createGameStage();
    }

    public void createStartStage(Stage stage) {
        LOGGER.log(Level.INFO, "{0}Creating start stage...{1}", new Object[] { GREEN, RESET });

        // 🔧 gesetzt: mainStage speichern
        mainStage = stage;

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
        });

        VBox layout = new VBox(10, intro, nameField, startButton);
        layout.setPadding(new Insets(10));
        layout.setPrefWidth(400);
        layout.setPrefHeight(200);

        startScene = new Scene(layout);
        startScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Tailklicker - Start");
        stage.setScene(startScene);
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }

    private void createGameStage() {
        BorderPane root = new BorderPane();
        gridPane = new GridPane();
        gridPane.prefHeightProperty().bind(root.widthProperty());
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        addMenuBar(root);
        setGrid(TailklickerApplication.getRows(), TailklickerApplication.getCols());

        root.setCenter(gridPane);

        Scene gameScene = new Scene(root, 800, 800);
        gameScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm()); // ✅ bleibt

        mainStage.setTitle("Tailklicker");
        mainStage.setScene(gameScene);
        mainStage.show();
    }

    private void setGrid(int rows, int cols) {
        Kachel[][] kachelGroup = new Kachel[cols][rows];
        kachelViews = new KachelFXView[cols][rows];

        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        // Seitenverhältnis anpassen, damit Buttons quadratisch bleiben
        if (cols > rows) {
            // Höhe richtet sich nach Breite
            gridPane.maxHeightProperty().bind(gridPane.widthProperty().multiply((double) rows / cols));
            gridPane.minHeightProperty().bind(gridPane.widthProperty().multiply((double) rows / cols));
        } else {
            // Breite richtet sich nach Höhe
            gridPane.maxWidthProperty().bind(gridPane.heightProperty().multiply((double) cols / rows));
            gridPane.minWidthProperty().bind(gridPane.heightProperty().multiply((double) cols / rows));
        }

        // Spalten- und Zeilen-Verteilung
        for (int i = 0; i < cols; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / cols);
            cc.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rc = new javafx.scene.layout.RowConstraints();
            rc.setPercentHeight(100.0 / rows);
            rc.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rc);
        }

        // Buttons anlegen
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                Kachel kachel = new Kachel(x, y);
                kachelGroup[x][y] = kachel;

                KachelFXView view = new KachelFXView(kachelViews, kachel);
                kachelViews[x][y] = view;

                Button btn = view.getButton();
                gridPane.add(btn, x, y);

                btn.setOnAction(e -> {
                    new KachelListener(kachelGroup, kachel).actionPerformed();
                    view.update();
                });
            }
        }

        // Nachbarn setzen
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                kachelGroup[x][y].setNeighbours(kachelGroup, kachelGroup[x][y]);
            }
        }
    }

    private void addMenuBar(BorderPane root) {
        MenuBar menuBar = new MenuBar();
        Menu gameMenu = new Menu("Game");
        Menu settingsMenu = new Menu("Settings");

        settingsMenu.getItems().add(new CustomMenuItem(new Button("Restart Game"), false));
        settingsMenu.getItems().add(new CustomMenuItem(new Button("Exit Game"), false));

        settingsMenu.getItems().get(0).setOnAction(e -> {
            new RestartListener().actionPerformed();
        });
        settingsMenu.getItems().get(1).setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        schwierigkeitenMenu = new ComboBox<>();
        schwierigkeitenMenu.getItems().addAll(Schwierigkeit.values());
        schwierigkeitenMenu.setValue(TailklickerApplication.getPlayer().getLevel());
        schwierigkeitenMenu.setOnAction(e -> {
            new SchwierigkeitenListener().actionPerformed(schwierigkeitenMenu.getValue());
        });

        CustomMenuItem comboMenuItem = new CustomMenuItem(schwierigkeitenMenu);
        comboMenuItem.setHideOnClick(false);

        gameMenu.getItems().add(new CustomMenuItem(new Button("Custom"), false));
        gameMenu.getItems().get(0).setOnAction(e -> {
            LOGGER.log(Level.INFO, "{0}Custom game option selected.{1}", new Object[] { GREEN, RESET });
            createCustomGame();
        });

        gameMenu.getItems().add(comboMenuItem);
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(settingsMenu);
        root.setTop(menuBar);
    }

    private void createCustomGame() {
        Scene customScene = new Scene(new BorderPane(), 400, 300);
        Stage customStage = new Stage();

        BorderPane customRoot = new BorderPane();
        VBox customLayout = new VBox(10);
        customLayout.setPadding(new Insets(10));
        TextArea customIntro = new TextArea("""
                Create your own game!
                Set the number of rows and columns.
                Click 'Start Custom Game' to begin.
                """);
        customIntro.setWrapText(true);
        customIntro.setEditable(false);
        TextField rowsField = new TextField();
        rowsField.setPromptText("Enter number of rows");
        TextField colsField = new TextField();
        colsField.setPromptText("Enter number of columns");
        Button startCustomButton = new Button("Start Custom Game");
        startCustomButton.setOnAction(e -> {
            try {
                int rows = Integer.parseInt(rowsField.getText());
                int cols = Integer.parseInt(colsField.getText());
                if (rows > 0 && cols > 0) {
                    TailklickerApplication.setRows(rows);
                    TailklickerApplication.setCols(cols);
                    new RestartListener().actionPerformed();
                    customStage.close();
                } else {
                    LOGGER.log(Level.WARNING, "{0}Invalid input for rows or columns.{1}", new Object[] { RED, RESET });
                }
            } catch (NumberFormatException ex) {
                LOGGER.log(Level.WARNING, "{0}Please enter valid numbers.{1}", new Object[] { RED, RESET });
            }
        });
        customLayout.getChildren().addAll(customIntro, rowsField, colsField, startCustomButton);
        customRoot.setCenter(customLayout);
        customRoot.setPadding(new Insets(10));
        customScene.setRoot(customRoot);
        customScene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        customStage.setWidth(400);
        customStage.setHeight(300);

        customStage.setTitle("Custom Game");
        customStage.setScene(customScene);
        customStage.setOnCloseRequest(event -> {
            customStage.close();
        });
        customStage.show();
    }
}
