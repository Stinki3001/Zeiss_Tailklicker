package com.costr.tailklicker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.logging.Level;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.costr.tailklicker.GUI.FXGUI.FXGUI;
import com.costr.tailklicker.GUI.GUI;
import com.costr.tailklicker.GUI.Notation;
import com.costr.tailklicker.Logik.JSONDatei;
import com.costr.tailklicker.Logik.Player;
import com.costr.tailklicker.Logik.Schwierigkeit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Tailklicker Application
 *
 * @author Costr
 */
@SpringBootApplication
public class TailklickerApplication extends Application {

    private static int rows = 3;
    private static int cols = 3;

    public static final JSONDatei dateiVerwalter = new JSONDatei();
    private static Set<Player> playerList = dateiVerwalter.loadJSONFile();
    private static Player player = new Player("default", Schwierigkeit.LEICHT, 0);
    private static GUI.Type guiType = GUI.Type.FX;
    private static Stage primaryStageInstance = new Stage();

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(TailklickerApplication.class, args);

    }

    // @Profile("gui")
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            Path saveDir = Paths.get("tailklicker", "saves");

            if( !Files.exists(saveDir)) {
                Files.createDirectories(saveDir);
                Notation.LOGGER.log(Level.INFO, "{0}Erstelle Verzeichnis: {1}{2}",
                        new Object[] { Notation.GREEN, saveDir.toAbsolutePath(), Notation.RESET });
            } else {
                Notation.LOGGER.log(Level.INFO, "{0}Verzeichnis existiert bereits: {1}{2}",
                        new Object[] { Notation.YELLOW, saveDir.toAbsolutePath(), Notation.RESET });
            }
            if( !Files.exists(saveDir.resolve("save.json"))) {
                Notation.LOGGER.log(Level.INFO, "{0}Erstelle Standard-Spielstand: {1}{2}",
                        new Object[] { Notation.GREEN, saveDir.resolve("save.json").toAbsolutePath(), Notation.RESET });
                Files.createFile(saveDir.resolve("save.json"));
            } else {
                Notation.LOGGER.log(Level.INFO, "{0}Spielstand existiert bereits: {1}{2}",
                        new Object[] { Notation.YELLOW, saveDir.resolve("save.json").toAbsolutePath(), Notation.RESET });
            }
            GUI gui = new GUI();
            gui.createStartFrame(guiType);

        };
    }

    public static Stage getPrimaryStageInstance() {
        return primaryStageInstance;
    }

    @Override
    public void start(Stage primaryStage) {
        Notation.LOGGER.log(Level.INFO, "{0}AWT Start complete{1}",
                new Object[] { Notation.GREEN, Notation.RESET });
        primaryStageInstance = new Stage();
    }

    public static void showGUI() {
        Platform.runLater(() -> {
            FXGUI awtGUI = new FXGUI();
            awtGUI.createStartStage(primaryStageInstance);
        });
    }

    public static int getRows() {
        return rows;
    }

    public static void setRows(int rows) {
        TailklickerApplication.rows = rows;
    }

    public static int getCols() {
        return cols;
    }

    public static void setCols(int cols) {
        TailklickerApplication.cols = cols;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Set<Player> getPlayerList() {
        return playerList;
    }

    public static void addPlayerToList(Player player) {
        playerList.add(player);
    }

    public static Schwierigkeit getSelectedDifficulty() {
        return player.getLevel();
    }

    public static GUI.Type getGUIType() {
        return guiType;
    }

    public static void setPlayer(Player player) {
        TailklickerApplication.player = player;
    }

}
