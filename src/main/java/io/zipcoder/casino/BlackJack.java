package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {
    Player currentPlayer;
    public List<Card> playerSplitHand = new ArrayList<>();
    public List<Card> dealerSplitHand = new ArrayList<>();
    public int playerTotal;
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

    public void playerBlackJack() {
        currentPlayer.winChips(sizeOfPot*3);
        sizeOfPot = 0;
    }

    @Override
    public void playGame() {
        makeDeck();
        shuffleDeck();
        for(int i = 0; i < 2; i++) {
            dealCards(1);
            playerTotal = playerHand.get(i).getValue();
            dealCards(2);
            dealerTotal = dealerHand.get(i).getValue();
        }
    }

    @Override
    public boolean checkWinner() {
        if(playerTotal == 21 && dealerTotal != 21) return true;
        else if(playerTotal > dealerTotal && playerTotal <= 21) return true;
        else if(dealerTotal > 21) return true;
        else if(dealerTotal == 21) return false;
        else if (dealerTotal > playerTotal) return false;
        else return false;
    }

    public void playerHandSplit() {
        playerSplitHand.add(playerHand.remove(0));
    }
    
    public void dealerHandSplit() {
        dealerSplitHand.add(dealerHand.remove(0));
    }

    public boolean playerBust() {
        if (playerTotal > 21)  return true;
        else return false;
    }

    public boolean dealerBust() {
        if (dealerTotal > 21)  return true;
        else return false;
    }
}
