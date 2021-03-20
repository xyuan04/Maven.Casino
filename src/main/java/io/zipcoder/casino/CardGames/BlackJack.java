package io.zipcoder.casino.CardGames;

import io.zipcoder.casino.GamblingGame;
import io.zipcoder.casino.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame implements GamblingGame {
    public Player currentPlayer;
    public List<Card> playerHand = new ArrayList<>();
    public List<Card> dealerHand = new ArrayList<>();
    public List<Card> playerSplitHand = new ArrayList<>();
    public List<Card> currentHand = playerHand;
    public int playerTotal;
    public int playerSplitTotal;
    public int dealerTotal;
    public int sizeOfPot;

    public BlackJack(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    public void addToPot(int chipsAdded) {
        currentPlayer.wageMoney(chipsAdded);
        sizeOfPot += chipsAdded;
    }

    public void passPot(int multiplier) {
        currentPlayer.winChips(getPot()*multiplier);
        sizeOfPot = 0;
        currentHand = playerHand;
    }

    public void hitMe() {
        dealCards(currentHand);
        int currentValue = 0;
        if(currentHand == playerHand) {
            for(int i = 0; i < playerHand.size(); i++) {
                currentValue += playerHand.get(i).getValue();
            }
            playerTotal = currentValue;
        } else if(currentHand == playerSplitHand) {
            for(int i = 0; i < playerSplitHand.size(); i++) {
                currentValue += playerSplitHand.get(i).getValue();
            }
            playerSplitTotal = currentValue;
        } else if(currentHand == dealerHand) {
            for(int i = 0; i < dealerHand.size(); i++) {
                currentValue += dealerHand.get(i).getValue();
            }
            dealerTotal = currentValue;
        }
    }

    public void hold() {
        if(currentHand == playerHand && playerSplitHand.size() == 2) {
            currentHand = playerSplitHand;
        } else if (currentHand == playerSplitHand) {
            currentHand = dealerHand;
        } else currentHand = dealerHand;
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
        if(playerTotal > dealerTotal && playerTotal <= 21 || playerSplitTotal > dealerTotal && playerSplitTotal <= 21) return "Player";
        else if(dealerTotal > 21) return "Player";
        else if(playerTotal > 21 && playerSplitTotal > 21) return "Dealer";
        else if(playerTotal > 21 && dealerTotal > playerSplitTotal) return "Dealer";
        else if(dealerTotal == 21 && playerTotal != 21 && playerSplitTotal != 21) return "Dealer";
        else if ((dealerTotal > playerTotal || playerTotal > 21)  && (dealerTotal > playerSplitTotal || playerSplitTotal > 21)) return "Dealer";
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

    public String blackJackCheck() {
        if (dealerTotal == 21 && dealerHand.size() == 2 && (playerTotal == 21 && playerHand.size() == 2 || playerSplitTotal == 21 &&  playerSplitHand.size() == 2)) {
            return "Tie";
        } else if (playerTotal == 21 && playerHand.size() == 2 || playerSplitTotal == 21 &&  playerSplitHand.size() == 2) {
            return "Player";
        } else if (dealerTotal == 21 && dealerHand.size() == 2)
            return "Dealer";
        else return "None";
    }

    public boolean dealerHaveBlackJack() {
        if (dealerTotal == 21 && (dealerHand.get(0).getCardName().equals("A") || dealerHand.get(1).getCardName().equals("A"))) {
            return true;
        } return false;
    }

    public boolean playerBust() { return playerTotal > 21; }

    public boolean playerSplitHandBust() { return playerSplitTotal > 21; }

    public boolean dealerBust() { return dealerTotal > 21; }

    @Override
    public int getPot() { return sizeOfPot; }
}
