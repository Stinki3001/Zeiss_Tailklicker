package com.costr.tailklicker;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.costr.tailklicker.GUI.SwingGUI;
import com.costr.tailklicker.Logik.Datei;
import com.costr.tailklicker.Logik.Player;

/**
 * Tailklicker Application
 *
 * @author Costr
 */
@SpringBootApplication
public class TailklickerApplication extends Datei {

    private static int rows = 3;
    private static int cols = 3;
    private static Player player;
    private static Set<Player> playerList = loadJSONFile();
    private static final int currentID = playerList.size();

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(TailklickerApplication.class, args);
    }

    // @Profile("gui")
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            SwingGUI gui = new SwingGUI();
            player = gui.createStartframe();
            player.setID();

        };
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

}
