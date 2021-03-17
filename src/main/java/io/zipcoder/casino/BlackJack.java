package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {
    Player currentPlayer;
    public List<Card> playerHand = new ArrayList<>();
    public List<Card> dealerHand = new ArrayList<>();
    public List<Card> playerSplitHand = new ArrayList<>();
    public List currentHand = playerHand;
    public int playerTotal;
    public int playerSplitTotal;
    public int dealerTotal;
    public int sizeOfPot;

    public BlackJack(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void addToPot(int chipsAdded) {
        currentPlayer.bet(chipsAdded);
        sizeOfPot += chipsAdded;
    }

    public void playerWinPot() {
        currentPlayer.winChips(sizeOfPot*2);
        sizeOfPot = 0;
    }

    public void playerWinByBlackJack() {
        currentPlayer.winChips(sizeOfPot*3);
        sizeOfPot = 0;
    }

    public void hitMe() {
        dealCards(currentHand);
        if(currentHand == playerHand) {
            playerTotal += playerHand.get(playerHand.size() - 1).getValue();
        } else if(currentHand == playerSplitHand) {
            playerSplitTotal += playerSplitHand.get(playerSplitHand.size() - 1).getValue();
        } else if(currentHand == dealerHand) {
            dealerTotal += dealerHand.get(dealerHand.size() - 1).getValue();
        }
    }

    public void hold() {
        currentHand = dealerHand;
    }

    public void splitHold() {
        currentHand = playerSplitHand;
    }

    @Override
    public void playGame() {
        makeDeck();
        shuffleDeck();
        for(int i = 0; i < 2; i++) {
            dealCards(playerHand);
            playerTotal += playerHand.get(i).getValue();
            dealCards(dealerHand);
            dealerTotal += dealerHand.get(i).getValue();
        }
    }

    @Override
    public Boolean checkWinner() {
        if(playerTotal == 21 && dealerTotal != 21) return true;
        else if(playerTotal > dealerTotal && playerTotal <= 21) return true;
        else if(dealerTotal > 21) return true;
        else if(playerTotal > 21) return false;
        else if(dealerTotal == 21 && playerTotal != 21) return false;
        else if (dealerTotal > playerTotal) return false;
        else return null;
    }

    public Boolean checkSplitWinner() {
        if(playerSplitTotal == 21 && dealerTotal != 21) return true;
        else if(playerSplitTotal > dealerTotal && playerSplitTotal <= 21) return true;
        else if(dealerTotal > 21) return true;
        else if(playerSplitTotal > 21) return false;
        else if(dealerTotal == 21 && playerSplitTotal != 21) return false;
        else if (dealerTotal > playerSplitTotal) return false;
        else return null;
    }

    public void playerHandSplit() {
        addToPot(sizeOfPot);

        playerTotal -= playerHand.get(0).getValue();
        playerSplitHand.add(playerHand.remove(0));
        playerSplitTotal += playerSplitHand.get(0).getValue();

        dealCards(playerHand);
        playerTotal += playerHand.get(1).getValue();
        dealCards(playerSplitHand);
        playerSplitTotal += playerSplitHand.get(1).getValue();
    }

    public boolean playerHaveBlackJack() {
        return playerTotal == 21;
    }

    public boolean playerSplitHandHaveBlackJack() {
        return playerSplitTotal == 21;
    }

    public boolean dealerHaveBlackJack() {
        return dealerTotal == 21;
    }

    public boolean playerBust() {
        return playerTotal > 21;
    }

    public boolean playerSplitHandBust() {
        return playerSplitTotal > 21;
    }

    public boolean dealerBust() {
        return dealerTotal > 21;
    }

}
