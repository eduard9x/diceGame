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

// button event handler class
class MyActionListener implements ActionListener {
    JFrame frame;

    MyActionListener(JFrame f) {
        frame = f;
    }


    public void actionPerformed(ActionEvent e) {

        int action = Integer.parseInt(e.getActionCommand());
        switch (action) {
            case 1:
                frame.getContentPane().setBackground(Color.red);
                break;
            case 2:
                frame.getContentPane().setBackground(Color.yellow);
                break;
            default:
                break;
        }

    }
}

public class Main {


    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

//        //creating buttons
//        JButton button = new JButton("press for red");
//        JButton button2 = new JButton("press for yellow");
//
//        //creating label for first player
//        JLabel firstPlayer = new JLabel("Dices for the first player:");
//
//        //creating dices
//        JButton dice1 = new JButton();
//        JButton dice2 = new JButton();
//        JButton dice3 = new JButton();
//        JButton dice4 = new JButton();
//        JButton dice5 = new JButton();
//
//        //setting the images
//        dice1.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//        dice2.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//        dice3.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//        dice4.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//        dice5.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
//
//        // set the content pane to be the newly created JPanel
//        frame.setContentPane(jp);
//        frame.getContentPane().add(button);
//        frame.getContentPane().add(button2);
//        frame.getContentPane().add(firstPlayer);
//        frame.getContentPane().add(dice1);
//        frame.getContentPane().add(dice2);
//        frame.getContentPane().add(dice3);
//        frame.getContentPane().add(dice4);
//        frame.getContentPane().add(dice5);
//
//        // register an event handler for button events
//        button.addActionListener(new MyActionListener(frame));
//        button2.addActionListener(new MyActionListener(frame));
//
//        button.setActionCommand("1");
//        button2.setActionCommand("2");




        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton();
        button.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(button, c);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");

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