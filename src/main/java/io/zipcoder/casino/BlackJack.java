package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {
    Player currentPlayer;
    public List<Card> playerSplitHand = new ArrayList<>();
    public List<Card> dealerSplitHand = new ArrayList<>();
    public int currentTurn = 1;
    public int playerTotal;
    public int playerSplitTotal;
    public int dealerTotal;
    public int dealerSplitTotal;
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

    public void playerBlackJack() {
        currentPlayer.winChips(sizeOfPot*3);
        sizeOfPot = 0;
    }

    public void hitMe() {
        dealCards(currentTurn);
        playerTotal += playerHand.get(playerHand.size()-1).getValue();
    }

    public void hold() {
        currentTurn = 2;
    }

    @Override
    public void playGame() {
        makeDeck();
        shuffleDeck();
        for(int i = 0; i < 2; i++) {
            dealCards(1);
            playerTotal += playerHand.get(i).getValue();
            dealCards(2);
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

    public void playerHandSplit() {
        playerTotal -= playerHand.get(0).getValue();
        playerSplitHand.add(playerHand.remove(0));
        playerSplitTotal += playerSplitHand.get(0).getValue();
    }
    
    public void dealerHandSplit() {
        dealerTotal -= dealerHand.get(0).getValue();
        dealerSplitHand.add(dealerHand.remove(0));
        dealerSplitTotal += dealerSplitHand.get(0).getValue();
    }

}
