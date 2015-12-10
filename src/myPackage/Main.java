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

    private static JFrame frame;

    public static void addComponentsToPane(Container pane) {

        int column = 0, row = 0;

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int totalButtons = 12;

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
                button.setText("Player " + (column + 1));
                // I want to have border on the name buttons
                button.setBorderPainted(true);
            }

            c.weightx = 0.5;
            c.gridx = row;
            c.gridy = column;
            pane.add(button, c);

            button.addActionListener(new java.awt.event.ActionListener() {
                int ii = 0;

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JButton test = (JButton) evt.getSource();
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

        JLabel border = new JLabel(" Use these buttons to play the game. ");
        border.setHorizontalAlignment(SwingConstants.CENTER);

        border.setForeground(Color.blue);
        border.setBackground(Color.red);
        border.setOpaque(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.gridwidth = totalButtons/2;
        c.gridx = row;
        c.gridy = column;
        pane.add(border, c);

        //creating menu buttons
        row = 0;
        column++;

        for(int i=0;i<2;i++) {
            button = new JButton();

            if(i==0) button.setText("Throw");
            else  button.setText("Score");

            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridwidth = totalButtons / 4;
            c.gridx = row;
            c.gridy = column;
            pane.add(button, c);
            row=row+totalButtons / 4;
        }

        //creating a label on the right hand side to display statistics
        row = totalButtons/2;
        column = 0;
        row++;
//todo might want to add rounded corners to labels
        border = new JLabel(" Use these statistics to develop your winning strategy. ");
        border.setHorizontalAlignment(SwingConstants.CENTER);

        border.setForeground(Color.blue);
        border.setBackground(Color.green);
        border.setOpaque(true);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.gridwidth = totalButtons/2;
        c.gridx = row;
        c.gridy = column;
        pane.add(border, c);




//todo need to find the biggest width and set it to every cell







//        // the rest of them
//        button = new JButton();
//        button.setIcon(new javax.swing.ImageIcon("images//dice_1.png"));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 40;      //make this component tall
//        c.weightx = 0.0;
//        c.gridwidth = 3;
//        c.gridx = 0;
//        c.gridy = 1;
//        pane.add(button, c);
//
//        button = new JButton();
//        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.ipady = 0;       //reset to default
//        c.weighty = 1.0;   //request any extra vertical space
//        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
//        c.insets = new Insets(10, 0, 0, 0);  //top padding
//        c.gridx = 1;       //aligned with button 2
//        c.gridwidth = 2;   //2 columns wide
//        c.gridy = 2;       //third row
//        pane.add(button, c);
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