package com.costr.tailklicker.GUI;

import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.costr.tailklicker.Logik.KachelListener;
import com.costr.tailklicker.Logik.Player;
import com.costr.tailklicker.Logik.Schwierigkeit;
import com.costr.tailklicker.Logik.SchwierigkeitenListener;
import com.costr.tailklicker.TailklickerApplication;

public class SwingGUI implements Notation{

    static JFrame mainFrame;
    private static int rows;
    private static int cols;
    private static JComboBox<Schwierigkeit> schwierigkeitenMenu;
    private JFrame startFrame;

    public static Schwierigkeit getSelectedDifficulty() {
        return (Schwierigkeit) schwierigkeitenMenu.getSelectedItem();
    }

    public void init(int rows, int cols) {
        SwingGUI.rows = rows;
        SwingGUI.cols = cols;
        System.err.println("Initializing Swing GUI...");
        if (startFrame != null) {
            System.err.println("Disposing of the old startFrame.");
            startFrame.dispose();
        }
        createMainFrame();
        setGrid(rows, cols);
        addMenue();

    }

    private void createMainFrame() {
        if (mainFrame != null) {
            LOGGER.log(Level.INFO, "{0}Disposing of the old main frame...{1}", new Object[] { GREEN, RESET });
            mainFrame.dispose();
        } else {
            LOGGER.log(Level.INFO, "{0}Creating main frame...{1}", new Object[] { GREEN, RESET });
        }
        mainFrame = new JFrame("Tailklicker");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new java.awt.Dimension(800, 800));
        mainFrame.setResizable(true);
        mainFrame.pack(); // Pack the mainFrame to fit the preferred size
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        LOGGER.log(Level.INFO, "{0}Main frame created successfully.{1}", new Object[] { GREEN, RESET });
    }

    private void setGrid(int rows, int cols) {
        LOGGER.log(Level.INFO, "{0}Setting up grid layout with {1} rows and {2} columns.{3}",
                new Object[] { GREEN, rows, cols, RESET });
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
                LOGGER.log(Level.INFO, "{0}Setting neighbours for button at ({1}, {2}){3}",
                        new Object[] { GREEN, i, j, RESET });
                kachelGroup[i][j].setNeighbours(kachelGroup, kachelGroup[i][j]);
                LOGGER.log(Level.INFO, "{0}Neighbours set for button at ({1}, {2}){3}",
                        new Object[] { GREEN, i, j, RESET });
                kachelGroup[i][j].getButton().addActionListener(new KachelListener(kachelGroup, kachelGroup[i][j]));
                System.err.println("Action listener added to button " + (i + 1) + " successfully.");

            }
        }
        System.err.println("Finished setting up grid layout.");
        mainFrame.add(gridPanel);
        mainFrame.revalidate();

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
        schwierigkeitenMenu.setToolTipText(
                "Select the difficulty of the game. The game will restart with the selected difficulty.");
        // SchwierigkeitenMenu.add(schwierigkeitenComboBox);
        // menueBar.add(SchwierigkeitenMenu);
        menueBar.add(schwierigkeitenMenu);
        mainFrame.add(menueBar, java.awt.BorderLayout.NORTH);
        mainFrame.setJMenuBar(menueBar);
        mainFrame.invalidate();
        System.err.println("Menu added successfully.");
    }

    public Player createStartframe() {
        Player player = new Player(0, null, Schwierigkeit.LEICHT, 0);
        System.err.println("Creating start frame...");
        startFrame = new JFrame("Tailklicker - Start");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setPreferredSize(new java.awt.Dimension(400, 200));
        startFrame.setResizable(false);
        startFrame.setLocationRelativeTo(null);
        JTextArea startText = new JTextArea("""
                Welcome to Tailklicker!
                Click on the tiles to invert them.
                Try to invert all tiles with the least clicks possible.
                Select the difficulty from the menu above.
                Have fun!""");
        startText.setEditable(false);
        startText.setLineWrap(true);
        JTextField nameField = new JTextField("Enter your name here");
        nameField.setToolTipText("Enter your name here. It will be used for the highscore.");

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            player.setName(nameField.getText());
            startFrame.dispose();
            init(TailklickerApplication.getRows(), TailklickerApplication.getCols());
        });

        startFrame.add(startButton, java.awt.BorderLayout.SOUTH);
        startFrame.add(startText, java.awt.BorderLayout.CENTER);
        startFrame.add(nameField, java.awt.BorderLayout.NORTH);
        startFrame.pack();
        startFrame.setVisible(true);
        return player;
    }
}
