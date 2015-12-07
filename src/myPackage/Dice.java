package myPackage;

import java.util.*;

public class Dice {

    private final int numberOfDices;

    public Dice() {
        numberOfDices = 5;
    }

    public ArrayList<Die> roll(ArrayList<Die> dices) {

        int number;
        Die one;


        if (dices.size() == 0)
            for (int i = 0; i < numberOfDices; i++) {
                number = (int) Math.floor(Math.random() * 6) + 1;
                one = new Die(number);
                dices.add(one);
            }
        else {
            for (int i = 0; i < numberOfDices; i++) {
                if(dices.get(i).getValue()==0) dices.get(i).setValue(7);

//                number = (int) Math.floor(Math.random() * 6) + 1;
//                one = new Die(number);
//                dices.add(one);
            }
        }

        return dices;
    }

}
