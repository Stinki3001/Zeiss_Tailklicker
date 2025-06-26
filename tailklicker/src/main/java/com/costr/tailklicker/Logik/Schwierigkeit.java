package com.costr.tailklicker.Logik;

/**
 * @author Costr
 */
public enum Schwierigkeit {
    LEICHT(3, 3),
    MITTEL(4, 4),
    SCHWER(5, 5),
    EXTREM(6, 6),
    CUSTOM(0, 0);

    private int rows;
    private int cols;

    Schwierigkeit(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String asString() {
        return this.name().toUpperCase();
    }
    public static Schwierigkeit fromString(String name) {
        for (Schwierigkeit s : values()) {
            if (s.name().equalsIgnoreCase(name)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Unbekannte Schwierigkeit: " + name);
    }
}
