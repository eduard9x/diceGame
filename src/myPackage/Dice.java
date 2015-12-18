package myPackage;

public class Dice {

    private final int numberOfDices = 5;
    private Die[] dices = new Die[numberOfDices];

    public Dice() {
        for (int i = 0; i < numberOfDices; i++)
            dices[i] = new Die(0);
    }

    //this is the first throw, when I have an empty array in the Game.java
    public Die[] roll() {
        int number;
        Die one;

        for (int i = 0; i < numberOfDices; i++) {
            number = (int) Math.floor(Math.random() * 6) + 1;
            one = new Die(number);
            dices[i] = one;
        }

        return dices;
    }

    //this is the normal throw, when I just want to change a couple of dices
    public Die[] roll(Die[] dices) {

        int number;

        for (int i = 0; i < numberOfDices; i++) {
            number = (int) Math.floor(Math.random() * 6) + 1;
            if (dices[i].getValue() == 0) dices[i].setValue(number);
        }

        return dices;
    }

}
