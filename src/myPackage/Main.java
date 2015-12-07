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
    private int i = 1;
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
    public static void main(String[] args) {
        //creating the frame
        JFrame frame = new JFrame("ComponentExample");
        //creating buttons
        JButton button = new JButton("press for red");
        JButton button2 = new JButton("press for yellow");

        //creating label for first player
        JLabel firstPlayer = new JLabel("Dices for the first player:");

        //creating dices
        JButton dice1 = new JButton();
        JButton dice2 = new JButton();
        JButton dice3 = new JButton();
        JButton dice4 = new JButton();
        JButton dice5 = new JButton();

        //setting the images
        dice1.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        dice2.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        dice3.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        dice4.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));
        dice5.setIcon(new javax.swing.ImageIcon("images//dice_0.png"));

        //creating environment
        JPanel jp = new JPanel();
        jp.setBackground(Color.white);

        // set the content pane to be the newly created JPanel
        frame.setContentPane(jp);
        frame.getContentPane().add(button);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(firstPlayer);
        frame.getContentPane().add(dice1);
        frame.getContentPane().add(dice2);
        frame.getContentPane().add(dice3);
        frame.getContentPane().add(dice4);
        frame.getContentPane().add(dice5);

        // register an event handler for frame events
        frame.addWindowListener(new MyWindowListener());

        // register an event handler for button events
        button.addActionListener(new MyActionListener(frame));
        button2.addActionListener(new MyActionListener(frame));

        button.setActionCommand("1");
        button2.setActionCommand("2");

        frame.setSize(400, 400);
        frame.setVisible(true);

//        Game test = new Game();

    }
}
