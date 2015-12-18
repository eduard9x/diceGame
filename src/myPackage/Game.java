package myPackage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Game {

    private Die[] playerDices;
    private Die[] computerDices;

    private int playerPoints, computerPoints, playerOverallPoints, computerOverallPoints, playerTurns, computerTurns, playerPosition, computerPosition, playerAttempts, computerAttempts, playerWon, computerWon, xx, computerStrategy;
    private ArrayList<JButton> myButtons;
    private ArrayList<JLabel> myLabels;
    private final String DEFAULTICON, COMPUTERNAME, OVERALLLABEL, SCORELABEL, TURNS, POINTS, ATTEMPTS;
    private String playerName;
    private int numberToReach ;
    private boolean allowScorePlayer, allowScoreComputer, isTie;


    public Game(ArrayList<JButton> myButtons, ArrayList<JLabel> myLabels, String DEFAULTICON, String playerName, String COMPUTERNAME, int playerPosition, int computerPosition, String OVERALLLABEL, String SCORELABEL, String TURNS, String POINTS, String ATTEMPTS, int playerWon, int computerWon) {
        this.playerName = playerName;
        this.COMPUTERNAME = COMPUTERNAME;
        this.DEFAULTICON = DEFAULTICON;
        this.myButtons = myButtons;
        this.myLabels = myLabels;
        this.playerPosition = playerPosition;
        this.computerPosition = computerPosition;
        this.OVERALLLABEL = OVERALLLABEL;
        this.SCORELABEL = SCORELABEL;
        this.TURNS = TURNS;
        this.POINTS = POINTS;
        this.ATTEMPTS = ATTEMPTS;
        this.playerWon = playerWon;
        this.computerWon = computerWon;
        playerPoints = 0;
        computerPoints = 0;
        playerOverallPoints = 0;
        computerOverallPoints = 0;
        playerTurns = 0;
        computerTurns = 0;
        playerAttempts = 0;
        computerAttempts = 0;
        allowScorePlayer = false;
        allowScoreComputer = false;
        isTie = false;
        xx = 0;
        computerStrategy = 0;

        try {
            numberToReach = Integer.parseInt(JOptionPane.showInputDialog("Please input the target to reach for the winning player:"));
        } catch (NumberFormatException e) {
            numberToReach = 101;
        }
        System.out.println("Game target was set to: " + numberToReach);

        Dice test = new Dice();
        playerDices = test.roll();

        test = new Dice();
        computerDices = test.roll();

        resetDices();
        updateGUI();
    }

    public int checkGoal(int check) {

        if (isTie == true) return 0;

        if (check >= numberToReach) return 1;
        else return 0;
    }


    public int getPlayerWon() {
        return playerWon;
    }

    public int getComputerWon() {
        return computerWon;
    }

    public void resetDices() {

        //make 0 all player dices
        for (Die one : playerDices)
            one.setValue(0);
        //make 0 all computer dices
        for (Die one : computerDices)
            one.setValue(0);

        allowScoreComputer = false;
        allowScorePlayer = false;
    }


    public void updateGUI() {

        //update on player dices
        int i = 0;
        for (Die one : playerDices) {
            myButtons.get(playerPosition + i).setIcon(new javax.swing.ImageIcon("images//dice_" + one.getValue() + ".png"));
            i++;
        }
        //update on player turns
        myLabels.get(findLabel(TURNS) - 3).setText(TURNS + playerTurns);
        //update on player points
        myLabels.get(findLabel(POINTS) - 3).setText(POINTS + playerPoints);
        //update on player attempts
        myLabels.get(findLabel(ATTEMPTS) - 3).setText(ATTEMPTS + playerAttempts);


        //update on computer dices
        i = 0;
        for (Die one : computerDices) {
            myButtons.get(computerPosition + i).setIcon(new javax.swing.ImageIcon("images//dice_" + one.getValue() + ".png"));
            i++;
        }
        //update on computer turns
        myLabels.get(findLabel(TURNS)).setText(TURNS + computerTurns);
        //update on computer points
        myLabels.get(findLabel(POINTS)).setText(POINTS + computerPoints);
        //update on score
        myLabels.get(findLabel(SCORELABEL)).setText(SCORELABEL + playerOverallPoints + "-" + computerOverallPoints);
        //update on computer attempts
        myLabels.get(findLabel(ATTEMPTS)).setText(ATTEMPTS + computerAttempts);
        //update overall wins
        myLabels.get(findLabel(OVERALLLABEL)).setText(OVERALLLABEL + playerWon + " - " + computerWon);

    }


    public void makeWinner(String won) {

        xx++;
        if (xx == 1) {

            if (playerName.equals(won)) {
                playerWon++;
                JOptionPane.showMessageDialog(null, "You won!");
            } else {
                computerWon++;
                JOptionPane.showMessageDialog(null, "You lost!");
            }

            myLabels.get(findLabel(OVERALLLABEL)).setText(OVERALLLABEL + playerWon + " - " + computerWon);
        }

    }

    public void checkWin() {

        if (isTie == true) {

            if (playerPoints > computerPoints) makeWinner(playerName);
            else if (playerPoints < computerPoints) makeWinner(COMPUTERNAME);
            else {
                isTie = true;
                resetDices();
                updateGUI();
                System.out.println("TIE");
            }
        } else if ((checkGoal(playerOverallPoints) == 1) && (checkGoal(computerOverallPoints) == 1)) {

            if (playerAttempts < computerAttempts) makeWinner(playerName);
            else if (playerAttempts > computerAttempts) makeWinner(COMPUTERNAME);
            else if (playerOverallPoints > computerOverallPoints) makeWinner(playerName);
            else if (playerOverallPoints < computerOverallPoints) makeWinner(COMPUTERNAME);
            else  //this means everything is equal
            {
                isTie = true;
                resetDices();
                updateGUI();
                System.out.println("TIE");
            }
        }
    }

    public void doScore(int playerTurns) {

        if ((allowScorePlayer == true) && (isTie == false)) {
            playerOverallPoints = playerOverallPoints + playerPoints;
            this.playerTurns = playerTurns;
            playerPoints = 0;
            playerAttempts++;

            //make 0 all player dices
            for (Die one : playerDices)
                one.setValue(0);

            updateGUI();
            allowScorePlayer = false;
            doScore();
            checkWin();
        }
    }

    public void doScore() {

        if ((allowScoreComputer == true) && (isTie == false)) {
            computerOverallPoints = computerOverallPoints + computerPoints;
            computerTurns = 0;
            computerPoints = 0;
            computerAttempts++;

            for (Die one : computerDices)
                one.setValue(0);

            updateGUI();
            allowScoreComputer = false;
            checkWin();
        }
    }

    public void doDice(JButton clicked) {

        if (isTie == false) {
            boolean once = false;

            for (JButton toCompare : myButtons) {
                if (clicked.getParent().getComponentZOrder(clicked) - (playerPosition + 1) < playerDices.length)
                    if (toCompare == clicked) toCompare.setIcon(new javax.swing.ImageIcon(DEFAULTICON));
            }

            for (int i = 0; i < playerDices.length; i++) {
                if (once == false)
                    if (clicked.getParent().getComponentZOrder(clicked) - (playerPosition + 1) == i) {
                        playerDices[i].setValue(0);
                        once = true;
                    }
            }

            allowScorePlayer = false;
        }
    }

    public void doName(JButton clicked) {

        if (!clicked.getText().equals(COMPUTERNAME)) {//when you click COMPUTER -- go into else

            String myName = playerName;
            String message = "Please enter your name:";

            do {
                if (!myName.equals(playerName)) message = "Please enter a human name:";

                myName = JOptionPane.showInputDialog(message);

                if ((myName == null) || (!myName.matches(".*\\w.*"))) myName = playerName;
                //if the user inputs a name with space or cancels the dialog - safety measure
                //also I want to have in the name at least one (ASCII) alphanumeric character.
                //for example: name not allowed: §!@

            } while (myName.compareToIgnoreCase(COMPUTERNAME) == 0);
            //I don't want a player with the name: "Computer"

            playerName = myName;
            clicked.setText(playerName);
            //change the name to the one that user inputs
        } else {
            if (computerStrategy == 0) {
                computerStrategy = 1;
                JOptionPane.showMessageDialog(null, "Computer Strategy was set to difficult.");

            } else {
                computerStrategy = 0;
                JOptionPane.showMessageDialog(null, "Computer Strategy was set to random.");
            }
        }
    }

    public void doThrow() {

        if (isTie == false) {
            if (playerTurns == 3) {
                allowScorePlayer = true;
                doScore(0);
            }
        }

        if (checkGoal(playerOverallPoints) == 0) {
            allowScorePlayer = true;
            start(playerPosition, playerTurns);
        }

        if (checkGoal(computerOverallPoints) == 0) {
            allowScoreComputer = true;

            if (computerTurns != 0) doStrategy();

            start(computerPosition, computerTurns);
        }

    }

    boolean doComputerScore = false;
    boolean doAllRolls = false;

    public void doStrategy() {

        if (doComputerScore == false) {

            if (computerStrategy == 0) { //this is the random strategy


                int reroll = (int) Math.floor(Math.random() * 2);

                if (reroll == 0) doComputerScore = true;
                else doAllRolls = true;

                if (doAllRolls == true) {

                    int high = (int) Math.floor(Math.random() * 7);

                    for (Die one : computerDices) {
                        if (one.getValue() < high) one.setValue(0);
                    }
                }
            } else {
                doEfficient();
            }
        }

    }

    public void doEfficient() {

        int high, average, minRoundsToGo, dicePower = 0, averageRounds;

        if ((playerAttempts + computerAttempts) == 0) average = 18;
        else
            average = (computerOverallPoints + playerOverallPoints) / (playerAttempts + computerAttempts);

        for (Die one : computerDices) {
            for (int i = 0; i < computerDices.length; i++)
                if (one.compareTo(computerDices[i]) == 1) dicePower++;
        }

        if (computerOverallPoints < playerOverallPoints) {
            // strategy when the computer is behind
            minRoundsToGo = (numberToReach - playerOverallPoints) / 30 + 1; //30 being the maximum 5 dices with the value of 6
            averageRounds = (numberToReach - playerOverallPoints) / average + 1;
            high = average + (playerOverallPoints - computerOverallPoints) / minRoundsToGo + dicePower / averageRounds;
        } else if (computerOverallPoints > playerOverallPoints) {
            // strategy when the computer is ahead
            minRoundsToGo = (numberToReach - computerOverallPoints) / 30 + 1; //30 being the maximum 5 dices with the value of 6
            if (minRoundsToGo == 0) minRoundsToGo++;
            averageRounds = (numberToReach - computerOverallPoints) / average + 1;
            high = average + (computerOverallPoints - playerOverallPoints) / minRoundsToGo + dicePower / averageRounds;
        } else {
            //mostly when it's equal to zero
            averageRounds = (numberToReach - computerOverallPoints) / average;
            if (averageRounds == 0) averageRounds++;
            high = dicePower / averageRounds + 1;
        }

        if (computerDices.length != 0)
            high = high / computerDices.length;

        //just as a precaution. if somehow it will bounce boundaries.
        if (high > 6) high = 5;
        else if (high < 2) high = 4;

        for (Die one : computerDices) {
            if (one.getValue() < high) one.setValue(0);
        }

    }

    public int findLabel(String text) {
        int found = -1;

        for (int i = 0; i < myLabels.size(); i++) {
            if (myLabels.get(i).getText().contains(text)) found = i;
        }

        return found;
    }

    public int findButton(String text) {
        int found = -1;

        for (int i = 0; i < myButtons.size(); i++) {
            if (myButtons.get(i).getText().contains(text)) found = i;
        }

        return found;
    }

    int x = 0;

    private void start(int player, final int turns) {

        SwingWorker<Die[], Integer> worker = new SwingWorker<Die[], Integer>() {

            @Override
            protected Die[] doInBackground() throws Exception {

                Dice test = new Dice();

                if (player == playerPosition) {
                    playerDices = test.roll(playerDices);
                    publish(turns);
                    return playerDices;
                } else {
                    computerDices = test.roll(computerDices);
                    publish(turns);
                    return computerDices;
                }
            }

            @Override
            // This will be called because I call publish() from doInBackground()
            // Can safely update the GUI here.
            protected void process(List<Integer> chunks) {
                Integer value = chunks.get(chunks.size() - 1);

                //add an extra turn
                value++;


                if (player == computerPosition) {
                    myLabels.get(findLabel(TURNS)).setText(TURNS + value);
                    myLabels.get(findLabel(SCORELABEL)).setText(SCORELABEL + playerOverallPoints + "-" + computerOverallPoints);
                    computerTurns = value;
                } else {
                    if (player == playerPosition)
                        myLabels.get(findLabel(TURNS) - 3).setText(TURNS + value);
                    myLabels.get(findLabel(SCORELABEL)).setText(SCORELABEL + playerOverallPoints + "-" + computerOverallPoints);
                    playerTurns = value;
                }

            }

            @Override
            // This is called when the thread finishes.
            // Can safely update GUI here.
            protected void done() {

                try {
                    //dices after the thread is complete
                    Die[] dices = get();

                    int dice = player, sum = 0;

                    for (Die one : dices) {
                        myButtons.get(dice).setIcon(new javax.swing.ImageIcon("images//dice_" + one.getValue() + ".png"));

                        dice++;
                        sum = sum + one.getValue();
                    }

                    if (player == playerPosition) {
                        playerPoints = sum;
                    } else {
                        computerPoints = sum;
                    }

                    int found = findLabel(POINTS);

                    myLabels.get(found - 3).setText(POINTS + playerPoints);
                    myLabels.get(found).setText(POINTS + computerPoints);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        };

        worker.execute();

        x++;
        if (x == 1) {
            if (isTie == true) {
                if (playerPoints > computerPoints) JOptionPane.showMessageDialog(null, "You won!");
                else if (computerPoints > playerPoints) JOptionPane.showMessageDialog(null, "You lost!");
            }
            updateGUI();
        }

    }

}
