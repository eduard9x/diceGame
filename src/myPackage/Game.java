package myPackage;

import javax.swing.*;
import java.util.*;

public class Game extends Main {

    private ArrayList<Die> dices = new ArrayList<>();

    public Game() {

        Throw();
//        sortDice();
        showDice();

//        selectDice();
//        Throw();
//        showDice();
    }

    public void Throw() {
        Dice test = new Dice();
        dices = test.roll(dices);
    }

    public void Score() {

    }

    public void selectDice() {

        dices.get(3).setValue(0);
        dices.get(4).setValue(0);

    }

    public void showDice() {

        int throwNumber = 1;

        for (int i=0; i < dices.size(); i++) {
//todo get a random number 1-6. change the first button that has a 0 icon. try again until you have no more.

            JButton button = (JButton) frame.getContentPane().getComponent(i+2);
            if (button.getIcon() != null)
            button.setIcon(new javax.swing.ImageIcon("images//dice_" + dices.get(i).getValue() + ".png"));




        }

    }

    public void sortDice() {
        Collections.sort(dices);
    }

}
