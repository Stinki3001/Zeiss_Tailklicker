package com.costr.tailklicker.Logik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Datei {

    
    File file = new File("default.txt");
    private String dateiname = file.getName();
    String reset = "\u001B[0m";
    String red = "\u001B[31m";
    String blau = "\u001B[34m";
    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Gibt den Dateinamen zurück.
     *
     * @return der Dateiname
     */
    String getDateiname() {
        return dateiname;
    }


    /**
     * Setzt den Dateinamen.
     *
     * @param dateiname der neue Dateiname
     */
    void setDateiname(String dateiname) {

        if (dateiname == null || dateiname.isEmpty()) {
            throw new IllegalArgumentException("Dateiname darf nicht null oder leer sein.");
        } else {
            File zdatei = new File(dateiname);
            if (this.file.renameTo(zdatei)) {
            }
            this.dateiname = this.file.getName();
        }
    }

    /**
     * Gibt die Datei zurück.
     *
     * @return die Datei
     */
    File getDatei() {
        return file;
    }

    /**
     * Setzt die Datei.
     *
     * @param file die neue Datei
     */
    void setDatei(File file) {
        this.file = new File(file.getAbsolutePath());
        this.dateiname = file.getName();
    }

    /**
     * Gibt die Dateigröße in Bytes zurück.
     *
     * @return die Dateigröße
     */
    long getDateigroesse() {
        return new File(dateiname).length();
    }

    /**
     * Lädt den Inhalt der Datei.
     */
    public abstract String load();

    /**
     * Speichert den Inhalt der Datei.
     */
    public abstract void save(File file);

    public void write(String text) {
        logger.log(Level.INFO, "{0}Schreibe in die Datei: {1}{2}", new Object[] { blau, file.getName(), reset });
        try (FileWriter writer = new FileWriter(file, true)) {
            if (text == null || text.isEmpty()) {
                logger.log(Level.WARNING, "{0}Der zu schreibende Text ist null oder leer.{1}",
                        new Object[] { red, reset });
                return;
            } else {
                writer.write(text);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "{0}Fehler beim Schreiben in die Datei: {1}{2}",
                    new Object[] { red, e.getMessage(), reset });
        }
    }

}
