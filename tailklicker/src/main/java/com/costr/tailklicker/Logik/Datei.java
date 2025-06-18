package com.costr.tailklicker.Logik;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import com.costr.tailklicker.GUI.Notation;

public abstract class Datei implements Notation {

    static File file = new File("default.json");
    private String dateiname = file.getName();

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
            LOGGER.log(Level.WARNING, "{0}Der Dateiname ist null oder leer.{1}",
                    new Object[] { RED, RESET });
            return;
        } else {
            File zdatei = new File(dateiname);
            if (Datei.file.renameTo(zdatei)) {
            }
            this.dateiname = Datei.file.getName();
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
    public static Set<Player> loadJSONFile() {
        LOGGER.log(Level.INFO, "{0}Lade den Inhalt der Datei: {1}{2}", new Object[] { BLAU, file.getName(), RESET });
        if (!file.exists()) {
            LOGGER.log(Level.WARNING, "{0}Die Datei {1} existiert nicht.{2}",
                    new Object[] { RED, file.getName(), RESET });
            return null;
        } else if (!file.canRead()) {
            LOGGER.log(Level.WARNING, "{0}Die Datei {1} kann nicht gelesen werden.{2}",
                    new Object[] { RED, file.getName(), RESET });
            return null;
        } else {

            Set<Player> playerList = new HashSet<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                LOGGER.log(Level.INFO, "{0}1Lese den Inhalt der Datei: {1}{2}",
                        new Object[] { BLAU, file.getName(), RESET });
                String line;
                StringBuilder jsonBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {

                    jsonBuilder.append(line);

                }
                // System.out.println(jsonBuilder.toString());
                JsonReader jsonReader = Json.createReader(new java.io.StringReader(jsonBuilder.toString()));
                JsonObject jsonObject = jsonReader.readObject();
                jsonReader.close();

                jsonObject.getJsonArray("highscores").forEach(jsonValue -> {
                    playerList.add(new Player(
                            jsonValue.asJsonObject().getInt("id"),
                            jsonValue.asJsonObject().getString("name"),
                            Schwierigkeit.valueOf(jsonValue.asJsonObject().getString("level")),
                            jsonValue.asJsonObject().getInt("count")));
                });

                LOGGER.log(Level.INFO, "{0}Inhalt der Datei {1} erfolgreich geladen.{2}",
                        new Object[] { BLAU, file.getName(), RESET });

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "{0}Fehler beim Lesen der Datei: {1}{2}",
                        new Object[] { RED, e, RESET });
            }

            return playerList;
        }
    }

    /**
     * Speichert den Inhalt der Datei.
     */
    public void save(File file) {
        throw new UnsupportedOperationException("Diese Methode muss in der Unterklasse implementiert werden.");
    }

    public void write(Set<Player> playerList) {
        LOGGER.log(Level.INFO, "{0}Schreibe in die Datei: {1}{2}", new Object[] { BLAU, file.getName(), RESET });
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Player currentplayer : playerList) {
            arrayBuilder.add(Json.createObjectBuilder()
                    .add("id", currentplayer.getID())
                    .add("name", currentplayer.getName())
                    .add("level", currentplayer.getLevel().asString().toUpperCase())
                    .add("count", currentplayer.getCount()));
        }

        JsonObject root = Json.createObjectBuilder()
                .add("highscores", arrayBuilder)
                .build();

        // Datei schreiben
        try (OutputStream os = Files.newOutputStream(Paths.get(file.getAbsolutePath()));
                JsonWriter writer = Json.createWriter(os)) {
            writer.writeObject(root);
            LOGGER.log(Level.INFO, "{0}Inhalt der Datei {1} erfolgreich geschrieben.{2}",
                    new Object[] { BLAU, file.getName(), RESET });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "{0}Fehler beim Schreiben der Datei: {1}{2}",
                    new Object[] { RED,this.getDateiname() ,RESET});
            e.printStackTrace();
        }
    }

}
