package com.costr.tailklicker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.costr.tailklicker.GUI.SwingGUI;

/**
 * Tailklicker Application
 *
 * @author Costr
 */
@SpringBootApplication
public class TailklickerApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(TailklickerApplication.class, args);
    }

    @Profile("gui")
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            SwingGUI gui = new SwingGUI();
            int rows = 3;
            int cols = 3;
            gui.init(rows, cols);

        };
    }

}
