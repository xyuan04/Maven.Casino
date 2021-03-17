package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {
    Player currentPlayer;
    public List<Card> playerHand = new ArrayList<>();
    public List<Card> dealerHand = new ArrayList<>();
    public List<Card> playerSplitHand = new ArrayList<>();
    public List<Card> currentHand = playerHand;
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
        currentHand = playerHand;
    }

    public void tiedPot() {
        currentPlayer.winChips(sizeOfPot);
        sizeOfPot = 0;
        currentHand = playerHand;
    }

    public void playerWinByBlackJack() {
        currentPlayer.winChips(sizeOfPot*3);
        sizeOfPot = 0;
        currentHand = playerHand;
    }

    public void playerLosePot() {
        sizeOfPot = 0;
        currentHand = playerHand;
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
    public String checkWinner() {
        if(playerTotal == 21 && dealerTotal != 21) return "Player";
        else if(playerTotal > dealerTotal && playerTotal <= 21) return "Player";
        else if(dealerTotal > 21) return "Player";
        else if(playerTotal > 21) return "Dealer";
        else if(dealerTotal == 21 && playerTotal != 21) return "Dealer";
        else if (dealerTotal > playerTotal) return "Dealer";
        else return "Tie";
    }

    public String checkSplitWinner() {
        if(playerSplitTotal == 21 && dealerTotal != 21) return "Player";
        else if(playerSplitTotal > dealerTotal && playerSplitTotal <= 21) return "Player";
        else if(dealerTotal > 21) return "Player";
        else if(playerSplitTotal > 21) return "Dealer";
        else if(dealerTotal == 21 && playerSplitTotal != 21) return "Dealer";
        else if (dealerTotal > playerSplitTotal) return "Dealer";
        else return "Tie";
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
        if (playerTotal == 21 && (playerHand.get(0).getCardName().equals("A") || playerHand.get(1).getCardName().equals("A"))) {
            return true;
        }
        return false;
    }

    public boolean playerSplitHandHaveBlackJack() {
        if (playerSplitTotal == 21 && (playerSplitHand.get(0).getCardName().equals("A") || playerSplitHand.get(1).getCardName().equals("A"))) {
            return true;
        }
        return false;
    }

    public boolean dealerHaveBlackJack() {
        if (dealerTotal == 21 && (dealerHand.get(0).getCardName().equals("A") || dealerHand.get(1).getCardName().equals("A"))) {
            return true;
        }
        return false;
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
