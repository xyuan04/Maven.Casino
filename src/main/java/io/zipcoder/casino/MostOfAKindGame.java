package io.zipcoder.casino;

import io.zipcoder.casino.DiceGame;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public class MostOfAKindGame extends DiceGame {
    ArrayList<Integer> dealerHand = new ArrayList<Integer>();
    int playerMatchingNum;
    int dealerMatchingNum;
    int dealerCount;
    int playerCount;
    Player currentPlayer;

    public MostOfAKindGame(Player player) {
        this.currentPlayer = player;
    }

    public int getPot() {
        return pot;
    }

    public int getDealerCount() {
        return dealerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public boolean checkWinner() {
        boolean doIWin = false;
        if (getPlayerCount() > getDealerCount()) {
            doIWin = true;
        } else if (getDealerCount() > getPlayerCount()) {
            doIWin = false;
        } else if (getDealerCount() == getPlayerCount()) {
            if (playerMatchingNum > dealerMatchingNum) {
                doIWin = true;
            } else if (dealerMatchingNum > playerMatchingNum) {
                //catches when dealerMatchingNum bigger
                doIWin = false;
            }
        }
        return doIWin;
    }
    

    public void addToPlayerCount(int numOfSameNum) {
        playerCount += numOfSameNum;
    }

    public void addToDealerCount(int numOfSameNum) {
        dealerCount += numOfSameNum;
    }

    public void makeDealerHand(int numOfDice) {
        int i = 0;
        while (i < numOfDice) {
            dealerHand.add(rollDice());
            i++;
        }
    }

    public void makePlayerHand(int numOfDice) {
        int i = 0;
        while (i < numOfDice) {
            playerDiceHand.add(rollDice());
            i++;
        }
    }

    public ArrayList<Integer> getDealerHand() {
        return this.dealerHand;
    }

    public ArrayList<Integer> getPlayerHand() {
        return playerDiceHand;
    }

    public void exchangePlayerDice(int numToKeep) {
        playerMatchingNum = numToKeep;
        ArrayList<Integer> newHand = new ArrayList<Integer>();
        int numOfDiceKeeping = 0;
        for (int i = 0; i < getPlayerHand().size(); i++) {
            if (getPlayerHand().get(i) == numToKeep) {
                newHand.add(getPlayerHand().get(i));
                numOfDiceKeeping++;
            }
        }
        int numOfDiceRollAgain = 5 - numOfDiceKeeping;
        playerDiceHand = newHand;
        makePlayerHand(numOfDiceRollAgain);
    }

    public void exchangeDealerDice(int numToKeep) {
        dealerMatchingNum = numToKeep;
        ArrayList<Integer> newDealerHand = new ArrayList<Integer>();
        int numOfDiceKeeping = 0;
        for (int i = 0; i < getDealerHand().size(); i++) {
            if (getDealerHand().get(i) == numToKeep) {
                newDealerHand.add(getDealerHand().get(i));
                numOfDiceKeeping++;
            }
        }
        int numOfDiceRollAgain = 5 - numOfDiceKeeping;
        dealerHand = newDealerHand;
        makeDealerHand(numOfDiceRollAgain);
    }


    public void setPlayerMatchingNum(int matchingNum) {
        this.playerMatchingNum = matchingNum;
    }

    public int getPlayerMatchingNum() {
        return playerMatchingNum;
    }

    public void setDealerMatchingNum(int matchingNum) {
        this.dealerMatchingNum = matchingNum;
    }

    public int getDealerMatchingNum() {
        return dealerMatchingNum;
    }

    public void playerNumOfMatches(ArrayList<Integer> playerSecondHand) {
        playerCount = 1;
        for (int i = 1; i < playerSecondHand.size(); i++) {
            if (playerSecondHand.get(i) == playerSecondHand.get(0)) {
                playerCount++;
            }
        }
    }

    public void dealerNumOfMatches(ArrayList<Integer> dealerSecondHand) {
        dealerCount = 1;
        for (int i = 1; i < dealerSecondHand.size(); i++) {
            if (dealerSecondHand.get(i) == dealerSecondHand.get(0)) {
                dealerCount++;
            }
        }
    }

    public void dealerAINumbersToKeep(ArrayList<Integer> dealerHand) {
        int matchOne;
        int counterOne = 1;
        int matchTwo = 0;
        int counterTwo = 1;

        for (int i = 0; i < dealerHand.size()-1; i++) {
            matchOne = dealerHand.get(i);
            for (int j = i+1; j < dealerHand.size(); j++) {
                if (dealerHand.get(j) == dealerHand.get(i)) {
                    counterOne++;
                }
            }
            if (counterOne > counterTwo) {
                matchTwo = matchOne;
                counterTwo = counterOne;
            } else if (counterOne == counterTwo) {
                if (matchOne > matchTwo) {
                    matchTwo = matchOne;
                    counterTwo = counterOne;
                }
            }
            counterOne = 1;
        }
        dealerMatchingNum = matchTwo;
        dealerCount = counterTwo;
    }

    public void anteUp() {
        currentPlayer.bet(5);
        pot += 5;
    }

    public void addToPot(int numOfChips) {
        pot += numOfChips;
        currentPlayer.wageMoney(numOfChips);
    }

    public void clearPlayerHand() {
        playerDiceHand.clear();
    }

    public void clearDealerHand() {
        dealerHand.clear();
    }

    public void playerWinsPot(int pot) {
        currentPlayer.winChips(pot * 2);
    }
}
