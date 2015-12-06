package myPackage;

import java.util.*;

public class Dice {

    private ArrayList<Die> dices = new ArrayList<>();
    private final int numberOfDices;

    public Dice() {
        numberOfDices = 5;
    }

    public ArrayList<Die> roll() {

        int number;
        Die one;

        for (int i = 0; i < numberOfDices; i++) {
            number = (int) Math.floor(Math.random() * 6) + 1;
            one = new Die(number);
            dices.add(one);
        }

        return dices;
    }


}
