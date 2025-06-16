package com.costr.tailklicker.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.costr.tailklicker.Logik.MyActionListener;

public class SwingGUI {

    JFrame frame;

    public void init(int rows, int cols) {
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
                System.err.println("Adding action listener to button " + (i + 1));
                kachelGroup[i][j].getButton().addActionListener(new MyActionListener(kachelGroup, kachelGroup[i][j]));
                System.err.println("Action listener added to button " + (i + 1) + " successfully.");
                
            }
        }

        frame.add(gridPanel);
        frame.revalidate();

    }
}
