package io.zipcoder.casino;

import java.util.ArrayList;

public abstract class DiceGame implements GamblingGame {
    private int totalNumOfDice;
    ArrayList<Integer> playerDiceHand = new ArrayList<Integer>();
    int pot = 0;
    
    public void addToPot() {
    }

    public void clearPot() {
        pot = 0;
    }

    public int getPot() {
        return pot;
    }

    public boolean checkWinner() {
        return false;
    }

    public int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
}
