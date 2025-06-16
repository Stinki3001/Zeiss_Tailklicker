package com.costr.tailklicker.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.costr.tailklicker.Logik.MyActionListener;

public class SwingGUI {

    JFrame frame;
    private static int rows;
    private static int cols;

    public void init(int rows, int cols) {
        SwingGUI.rows = rows;
        SwingGUI.cols = cols;
        System.out.println("Initializing Swing GUI...");
        newFrame();
        setGrid(rows, cols);

    }

    private void newFrame() {
        frame = new JFrame("Tailklicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setResizable(true);
        frame.pack(); // Pack the frame to fit the preferred size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Frame was created successfully.");
    }

    private void setGrid(int rows, int cols) {
        System.out.println("Setting up grid layout...");
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new java.awt.GridLayout(3, 3));
        Kachel[][] kachelGroup = new Kachel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                System.err.println("Creating button " + (i + 1));
                Kachel kachel = new Kachel(i, j);
                kachelGroup[kachel.getX()][kachel.getY()] = kachel;
                System.err.println("Button " + (i + 1) + " created successfully.");
                gridPanel.add(kachelGroup[i][j].getButton());
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.err.println("Setting neighbours for button " + (i + 1));
                kachelGroup[i][j].setNeighbours(kachelGroup, kachelGroup[i][j]);
                System.err.println("Adding action listener to button " + (i + 1));
                kachelGroup[i][j].getButton().addActionListener(new MyActionListener(kachelGroup, kachelGroup[i][j]));
                System.err.println("Action listener added to button " + (i + 1) + " successfully.");

            }
        }
        System.err.println("Finished setting up grid layout.");
        frame.add(gridPanel);
        frame.revalidate();

    }

    public static int getRows() {
        return rows;
    }

    public static int getCols() {
        return cols;
    }

    public static void setCols(int cols) {
        SwingGUI.cols = cols;
    }

    public static void setRows(int rows) {
        SwingGUI.rows = rows;
    }
}
