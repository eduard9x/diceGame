package myPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class MainFrame extends JFrame {

    public String playerName = "Human";
    public final String COMPUTERNAME = "Computer";
    public final String DEFAULTICON = "images//dice_0.png";

    public int playerPosition, computerPosition, playerWon, computerWon;


    private ArrayList<JButton> myButtons = new ArrayList<>();
    private ArrayList<JLabel> myLabels = new ArrayList<>();
    private Game myGame = null;

    private final String NEWGAME = "New Game", THROW = "Throw", SCORE = "Score", OVERALLLABEL = "Overall:", SCORELABEL = "Score:", TURNS = "Current throw:", POINTS = "Points:", ATTEMPTS = "Attempts:";

    public MainFrame(String title) {
        super(title);

        JButton ordinaryButton;
        JLabel ordinaryLabel;

        int row = 0, column = 0;
        final int GAMEBTNS = 12, USERBTNS = 3, SHORT = 10, TALL = 40;


        // end of declarations

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);

        readFile();

        // start of first Label
        ordinaryLabel = new JLabel("#1 To change a dice, click on it, then throw again. ");
        ordinaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ordinaryLabel.setBackground(Color.blue);
        ordinaryLabel.setOpaque(true);

        gbc.ipady = TALL;
        gbc.gridwidth = 6;

        gbc.gridx = column;
        gbc.gridy = row;
        add(ordinaryLabel, gbc); //add in the frame
        myLabels.add(ordinaryLabel); //add in the list
        // end of first label

        row++;
        //row = 1
        //column = 0

        // starting to create 12 buttons
        for (int i = 0; i < GAMEBTNS; i++) {

            ordinaryButton = new JButton();

            //disable the focus border
            ordinaryButton.setFocusable(false);
            //disable the background in order to have just an icon
            ordinaryButton.setBorderPainted(false);

            if (i % (GAMEBTNS / 2) != 0) {
                //set this icon to everything, except the first and the 6th button
                ordinaryButton.setIcon(new ImageIcon(DEFAULTICON));
            } else {
                if (i == (GAMEBTNS / 2)) row++;
                column = 0;
                // I want to have 6 buttons on a single row

                if (row == 1) {
                    ordinaryButton.setText(playerName);
                    playerPosition = i + 1;
                    //set player position (add 1 because i starts from 0)
                    ordinaryButton.setFocusable(true);
                    //enable the focus border
                } else {
                    ordinaryButton.setText(COMPUTERNAME);
                    computerPosition = i + 1;
                    //set computer position (add 1 because i starts from 0)
                }

                // I want to have border on the name buttons
                ordinaryButton.setBorderPainted(true);
            }

            gbc.ipady = SHORT;
            gbc.gridwidth = 1;

            gbc.gridx = column;
            gbc.gridy = row;
            add(ordinaryButton, gbc); //add in the frame
            myButtons.add(ordinaryButton); //add in the list

            column++;

            //adding event listeners
            ordinaryButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    JButton clicked = (JButton) evt.getSource();

                    if (clicked.getIcon() == null)
                        if (myGame != null) myGame.doName(clicked);
                        else System.out.println("Create new game first.");
                    else if (myGame != null)
                        myGame.doDice(clicked);
                    else System.out.println("Create new game first.");
                }
            });
            //finished adding event listeners

        }// end of create 12 buttons

        row++;
        column = 0;
        //row = 3

        //creating a label above the menu buttons
        ordinaryLabel = new JLabel(" Use these buttons to play the game. ");
        ordinaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ordinaryLabel.setForeground(Color.blue);
        ordinaryLabel.setBackground(Color.red);
        ordinaryLabel.setOpaque(true);

        gbc.ipady = TALL;
        gbc.gridwidth = 6;

        gbc.gridx = column;
        gbc.gridy = row;
        add(ordinaryLabel, gbc); //add in the frame
        myLabels.add(ordinaryLabel); //add in the list
        //ended creating label above menu buttons

        row++;
        column = 0;
        //row = 4

        //starting to create menu buttons
        for (int i = 0; i < USERBTNS; i++) {
            ordinaryButton = new JButton();

            if (i == 0) ordinaryButton.setText(NEWGAME);
            else if (i == 1) ordinaryButton.setText(THROW);
            else ordinaryButton.setText(SCORE);

            gbc.ipady = TALL;
            gbc.gridwidth = 2;

            gbc.gridx = column;
            gbc.gridy = row;
            add(ordinaryButton, gbc); //add in the frame
            myButtons.add(ordinaryButton); //add in the list

            column = column + 2;
            //because the grid width is 2

            //adding event listeners
            ordinaryButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    JButton clicked = (JButton) evt.getSource();

                    if (clicked.getText().equals(NEWGAME)) doNewGame();
                    else if (clicked.getText().equals(THROW)) doThrow();
                    else if (clicked.getText().equals(SCORE)) doScore();
                }
            });
            //finished adding event listeners
        }
        //finished creating menu buttons

        column++;
        row = 0;
        //column = 7

        // start of first Label - Right hand side
        ordinaryLabel = new JLabel(" Use these statistics to develop your winning strategy. ");
        ordinaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        ordinaryLabel.setForeground(Color.blue);
        ordinaryLabel.setBackground(Color.green);
        ordinaryLabel.setOpaque(true);

        gbc.ipady = TALL;
        gbc.gridwidth = 6;

        gbc.gridx = column;
        gbc.gridy = row;
        add(ordinaryLabel, gbc); //add in the frame
        myLabels.add(ordinaryLabel); //add in the list
        // end of first label - Right hand side

        row++;
        // row = 1
        //column = 7

        // start creating 6 labels - Right hand side
        for (int i = 0; i < 6; i++) {

            if (i % 3 == 0) {
                ordinaryLabel = new JLabel(TURNS);
                ordinaryLabel.setBackground(Color.pink);
            }
            if (i % 3 == 1) {
                ordinaryLabel = new JLabel(POINTS);
                ordinaryLabel.setBackground(Color.lightGray);
            } else if (i % 3 == 2) {
                ordinaryLabel = new JLabel(ATTEMPTS);
                ordinaryLabel.setBackground(Color.pink);
            }

            ordinaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            ordinaryLabel.setOpaque(true);
            gbc.ipady = TALL;
            gbc.gridwidth = 2;

            gbc.gridx = column;
            gbc.gridy = row;
            add(ordinaryLabel, gbc); //add in the frame
            myLabels.add(ordinaryLabel); //add in the list

            column = column + 2;

            if (i == 2) {
                row++;
                column = 7;
            }

        }
        // finished creating 6 labels - Right hand side

        row++;
        column = 7;
        //row - 3

        // start creating 2 labels - Right hand side
        for (int i = 0; i < 2; i++) {

            if (i == 0) {
                ordinaryLabel = new JLabel(SCORELABEL);
                ordinaryLabel.setBackground(Color.magenta);
            } else {
                ordinaryLabel = new JLabel(OVERALLLABEL + playerWon + "-" + computerWon);
                ordinaryLabel.setBackground(Color.pink);
            }

            ordinaryLabel.setHorizontalAlignment(SwingConstants.CENTER);

            ordinaryLabel.setOpaque(true);

            gbc.ipady = TALL;
            gbc.gridwidth = 6;

            gbc.gridx = column;
            gbc.gridy = row;
            add(ordinaryLabel, gbc); //add in the frame
            myLabels.add(ordinaryLabel); //add in the list
            row++;
        }
        // finished creating 2 labels - Right hand side


        pack();
        setVisible(true);
        //region CLOSE_EVENT
        // window event Handler class
        class MyWindowListener extends WindowAdapter {
            public void windowClosing(WindowEvent e) {

                BufferedWriter writer = null;
                try {
                    File myFile = new File("files//stats.txt");

                    writer = new BufferedWriter(new FileWriter(myFile));

                    if (myGame != null) {
                        playerWon = myGame.getPlayerWon();
                        computerWon = myGame.getComputerWon();
                    } else {
                        playerWon = 0;
                        computerWon = 0;
                    }

                    String toWrite = playerWon + "-" + computerWon;

                    writer.write(toWrite);
                } catch (Exception exc) {
                    exc.printStackTrace();
                } finally {
                    try {
                        // Close the writer regardless of what happens...
                        writer.close();
                    } catch (Exception exc) {
                    }
                }

                System.exit(0);
            }
        }

        addWindowListener(new MyWindowListener());

    }


    public void readFile() {

        // here starts the reading from a file
        try {
            Scanner in = new Scanner(new FileReader("files//stats.txt"));

            while (in.hasNext()) {
                String x = in.next();

                playerWon = Integer.parseInt(x.substring(0, x.indexOf("-")));
                computerWon = Integer.parseInt(x.substring(x.indexOf("-") + 1, x.length()));

            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        // here ends the reading from a file

    }

    public void doNewGame() {
        if ((playerWon != 0 || computerWon != 0) && myGame != null) {
            playerWon = myGame.getPlayerWon();
            computerWon = myGame.getComputerWon();
        }
        myGame = new Game(myButtons, myLabels, DEFAULTICON, playerName, COMPUTERNAME, playerPosition, computerPosition, OVERALLLABEL, SCORELABEL, TURNS, POINTS, ATTEMPTS, playerWon, computerWon);
    }

    public void doThrow() {
        if (myGame != null) {
            myGame.doThrow();
        } else System.out.println("Create new game first.");
    }

    public void doScore() {
        if (myGame != null) {
            myGame.doScore(0);
        } else System.out.println("Create new game first.");
    }


}