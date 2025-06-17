package com.costr.tailklicker.Logik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;

public class DateiVerwaltung extends Datei {

    @Override
    public String load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(getDatei()))) {
            String line = reader.readLine();
            StringBuilder content = new StringBuilder();
            while (line != null) {
                content.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            return content.toString();
        } catch (Exception e) {
            logger.log(Level.SEVERE, red + "Fehler beim Laden der Datei: " + getDatei().getName() + reset, e);
            return null;
        }
    }

    @Override
    public void save(File file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
