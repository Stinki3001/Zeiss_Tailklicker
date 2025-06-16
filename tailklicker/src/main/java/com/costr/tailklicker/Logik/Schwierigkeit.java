package com.costr.tailklicker.Logik;

/**
 * @author Costr
 */
public enum Schwierigkeit {
    LEICHT(3, 3),
    MITTEL(4, 4),
    SCHWER(5, 5),
    EXTREM(6, 6);

    private final int rows;
    private final int cols;

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
}
