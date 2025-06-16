package com.costr.tailklicker.GUI;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.costr.tailklicker.Logik.MyActionListener;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.Logik.SchwierigkeitenListener;

public class SwingGUI {

    static JFrame frame;
    private static int rows;
    private static int cols;
    private static JComboBox<Schwierigkeit> schwierigkeitenMenu;

    public static Schwierigkeit getSelectedDifficulty() {
        return (Schwierigkeit) schwierigkeitenMenu.getSelectedItem();
    }

    public void init(int rows, int cols) {
        SwingGUI.rows = rows;
        SwingGUI.cols = cols;
        System.err.println("Initializing Swing GUI...");
        newFrame();
        setGrid(rows, cols);
        addMenue();

    }

    private void newFrame() {
        if (frame != null) {
            System.err.println("Frame already exists, disposing of the old frame.");
            frame.dispose();
        } else {
            System.err.println("Creating a new frame.");
        }
        frame = new JFrame("Tailklicker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new java.awt.Dimension(800, 800));
        frame.setResizable(true);
        frame.pack(); // Pack the frame to fit the preferred size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Frame was created successfully.");
    }

    private void setGrid(int rows, int cols) {
        System.out.println("Setting up grid layout...");
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new java.awt.GridLayout(rows, cols));
        
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

    private void addMenue() {
        System.err.println("Adding menu...");
        JMenuBar menueBar = new JMenuBar();
        menueBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        // menuePanel.setPreferredSize(new java.awt.Dimension(800, 50));
        
        
        // JMenu SchwierigkeitenMenu = new JMenu("Schwierigkeit");
        schwierigkeitenMenu = new JComboBox<>(Schwierigkeit.values());
        schwierigkeitenMenu.setSelectedItem(Schwierigkeit.LEICHT);
        schwierigkeitenMenu.addActionListener(new SchwierigkeitenListener());
        schwierigkeitenMenu.setToolTipText("Select the difficulty of the game. The game will restart with the selected difficulty.");
        // SchwierigkeitenMenu.add(schwierigkeitenComboBox);
        // menueBar.add(SchwierigkeitenMenu);
        menueBar.add(schwierigkeitenMenu);
        frame.add(menueBar, java.awt.BorderLayout.NORTH);
        frame.setJMenuBar(menueBar);
        frame.invalidate();
        System.err.println("Menu added successfully.");
    }
}
