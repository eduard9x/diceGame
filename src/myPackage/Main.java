package myPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// window event Handler class
class MyWindowListener extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing window!");
        System.exit(0);
    }
}

public class Main {
    //todo might want to add rounded corners to labels
    //todo try to make the font bigger
    //todo try to use different fonts. console game type.
    //todo start connecting the GUI with the back end stuff
    //todo implement swing threads
    //todo need to add the new game button
    //todo when changing the player name, say Hello on the blue label. after that show tips



    public static JFrame frame;

    public static void addComponentsToPane(Container pane) {
//todo need to change row and column between them
        int column, row;
        final String NEWGAME = "New Game";

        JButton button;
        JLabel border;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int totalButtons = 12;


        //creating a label at the top
        row = 0;
        column = 0;

        border = new JLabel("#1 To keep a dice, just click on it. ");
        border.setHorizontalAlignment(SwingConstants.CENTER);

        border.setForeground(Color.black);
        border.setBackground(Color.blue);
        border.setOpaque(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.gridwidth = totalButtons / 2;
        c.gridx = row;
        c.gridy = column;
        pane.add(border, c);

        row = -1;//because I start the for loop with row++ todo need to change it to 0 for consistency
        column++;

        // creating 5 buttons as dices
        for (int i = 0; i < totalButtons; i++) {

            row++;
            button = new JButton();

            //disable the focus border
            button.setFocusable(false);
            //disable the background in order to have just the icon
            button.setBorderPainted(false);

            c.fill = GridBagConstraints.HORIZONTAL;

            if (i % (totalButtons / 2) != 0) {
                button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
            } else {
                if (i == (totalButtons / 2)) column++;
                row = 0;
                button.setText("Player " + column);
                // I want to have border on the name buttons
                button.setBorderPainted(true);
            }

            c.weightx = 0.5;
            c.gridwidth = 1;
            c.ipady = 10;
            c.gridx = row;
            c.gridy = column;
            pane.add(button, c);

            button.addActionListener(new java.awt.event.ActionListener() {
                int ii = 0;

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JButton test = (JButton) evt.getSource();

                    //testing the click button thing
                    ii++;
                    if (test.getIcon() == null) {//when it is a label
                        String myName = JOptionPane.showInputDialog("Please enter your name:");
                        test.setText(myName);
                        //todo need to resize the window now, because if you add a bigger name, it will look weird
                        //todo need to let the user to change his name. after that do the other one as Computer Player
                        //todo after deciding which one is computer, make the other buttons unclickable. maybe using something like:   test.setEnabled(false);
                    } else {//when it is a dice
                        test.setIcon(new javax.swing.ImageIcon("images//dice_" + ii + ".png"));
                    }

                    if (ii == 6) ii = 0; //because the dice has 6 faces -- 7 would be out of boundaries
                }
            });

        }


        //creating the rest of the menu

        //creating a label above the buttons
        row = 0;
        column++;

        border = new JLabel(" Use these buttons to play the game. ");
        border.setHorizontalAlignment(SwingConstants.CENTER);

        border.setForeground(Color.blue);
        border.setBackground(Color.red);
        border.setOpaque(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.gridwidth = totalButtons / 2;
        c.gridx = row;
        c.gridy = column;
        pane.add(border, c);

        //creating menu buttons
        row = 0;
        column++;

        for (int i = 0; i < 3; i++) {
            button = new JButton();

            if (i == 0) button.setText(NEWGAME);
            else if (i == 1) button.setText("Throw");
            else button.setText("Score");

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = totalButtons / 6;
            c.gridx = row;
            c.gridy = column;
            pane.add(button, c);
            row = row + totalButtons / 6;
            //todo need to turn that 6 into total/numberofbuttons * 2


            button.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    Game createNewGame;
                    JButton test = (JButton) evt.getSource();
                    if (test.getText() == NEWGAME) {
                        createNewGame = new Game();
                    }
                }
            });


        }

        //creating a label on the right hand side to display statistics
        row = totalButtons / 2;
        column = 0;
        row++;

        border = new JLabel(" Use these statistics to develop your winning strategy. ");
        border.setHorizontalAlignment(SwingConstants.CENTER);

        border.setForeground(Color.blue);
        border.setBackground(Color.green);
        border.setOpaque(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.gridwidth = totalButtons / 2;
        c.gridx = row;
        c.gridy = column;
        pane.add(border, c);

        for (int i = 0; i < 4; i++) {

            //creating a new label on the right hand side to display statistics
            row = totalButtons / 2;
            column++;
            row++;

            border = new JLabel(" Player 1 : points ");
            border.setHorizontalAlignment(SwingConstants.CENTER);


            border.setForeground(Color.blue);
            border.setBackground(Color.lightGray);
            border.setOpaque(true);

            //todo add if statements here

            if (i == 2) {
                border.setBackground(Color.magenta);
                border.setText("Score: 42 - 93");
            } else if (i == 3) {
                border.setBackground(Color.pink);
                border.setText("Overall Score: 2 - 1");
            }


            //todo end if statements here

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 40;      //make this component tall
            c.gridwidth = totalButtons / 2;
            c.gridx = row;
            c.gridy = column;
            pane.add(border, c);

        }


//todo need to find the biggest width and set it to every cell
//todo how to add padding        c.insets = new Insets(10, 0, 0, 0);  //top padding

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame(" The dice game ");

        // register an event handler for frame events
        frame.addWindowListener(new MyWindowListener());

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

}

//todo make everything reset when you change player's name
//todo when click new game, don't reset overall. maybe you want to keep it. but read specs first.