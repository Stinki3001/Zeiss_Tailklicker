package com.costr.tailklicker;

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
import com.costr.tailklicker.Logik.Datei;
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

    private static Set<Player> playerList = Datei.loadJSONFile();
    private static final int currentID = playerList.size();
    private static Player player = new Player(1, "default", Schwierigkeit.LEICHT, 0);
    private static GUI.Type guiType = GUI.Type.SWING;
    private static Stage primaryStageInstance = new Stage();

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(TailklickerApplication.class, args);

    }

    // @Profile("gui")
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            player.setID();
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
            new Object[] {Notation.GREEN, Notation.RESET});
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

    public static int getCurrentID() {
        return currentID;
    }

    public static Schwierigkeit getSelectedDifficulty() {
        return player.getLevel();
    }

    public static GUI.Type getGUIType() {
        return guiType;
    }

}
