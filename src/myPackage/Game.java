package myPackage;

import javax.swing.*;
import java.util.*;

public class Game {

    private ArrayList<Die> dices = new ArrayList<>();

    public Game() {

        Throw();
        sortDice();
        showDice();

        selectDice();
        Throw();
        showDice();

    }

    public void Throw(){
        Dice test = new Dice();
        dices = test.roll(dices);


    }

    public void Score(){

    }

    public void selectDice(){

        dices.get(3).setValue(0);
        dices.get(4).setValue(0);

    }

    public void showDice(){

        for (Die show : dices) {
            System.out.print(show.getValue() + " ");
        }

        System.out.println("\n");
    }

    public void sortDice(){
        Collections.sort(dices);
    }

}
