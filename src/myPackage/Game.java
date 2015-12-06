package myPackage;

import java.util.*;

public class Game {

    private ArrayList<Die> dices = new ArrayList<>();

    public Game() {
        Dice test = new Dice();
        dices = test.roll();

        for (Die show : dices) {
            System.out.print(show.getValue() + " ");
        }
        System.out.println();
        
        /* Sort statement*/
        Collections.sort(dices);

	   /* Sorted List*/
        System.out.println("After Sorting:");
        for (Die show : dices) {
            System.out.print(show.getValue() + " ");
        }




    }

    public void Throw(){

    }

    public void Score(){

    }

}
