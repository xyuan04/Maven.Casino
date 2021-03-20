package io.zipcoder.casino.Screens;

import io.zipcoder.casino.CardGames.BlackJack;
import io.zipcoder.casino.Player.Player;

public class BlackJackScreens {
    Player player;
    BlackJack blackJack;

    public BlackJackScreens(BlackJack blackJack, Player player) {
        this.blackJack = blackJack;
        this.player = player;
    }

    public void currentChipCount(Player player) {
        System.out.println(String.format("%s : Your current chip count is %7d\n", player.getPlayerName(), player.getChipBalance()));
    }

    public void currentPot(BlackJack blackJack) {
        System.out.println(String.format("The current bet is %s\n", blackJack.sizeOfPot));
    }

    public void finalHandTie(BlackJack blackJack) {
        System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
        currentHands(blackJack);
        System.out.println(String.format("TIED! You get %s chips back.", blackJack.sizeOfPot));
    }

    public void finalHandLose(BlackJack blackJack) {
        System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
        currentHands(blackJack);
        System.out.println("Sorry, better luck next time!");
    }

    public void finalHandWin(BlackJack blackJack) {
        System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
        currentHands(blackJack);
        System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot*2)));
    }

    public void playerTurnHands(BlackJack blackJack) {
        System.out.println("Dealer current hand:");
        System.out.println(String.format("\t\t\t\t\t=== %s + HIDDEN CARD ===", blackJack.dealerHand.get(0)));
        if(!blackJack.playerSplitHand.isEmpty()) {
            System.out.println(String.format("\nPlayer current split hand: %s\n", blackJack.playerSplitTotal));
            System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.playerSplitHand));
        }
        System.out.println(String.format("\nPlayer current hand: %s\n", blackJack.playerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===\n", blackJack.playerHand));
    }

    public void currentHands(BlackJack blackJack) {
        System.out.println(String.format("Dealer current hand: %s\n",blackJack.dealerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.dealerHand));
        if(!blackJack.playerSplitHand.isEmpty()) {
            System.out.println(String.format("\nPlayer current split hand: %s\n", blackJack.playerSplitTotal));
            System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.playerSplitHand));
        }
        System.out.println(String.format("\nPlayer current hand: %s\n", blackJack.playerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===\n", blackJack.playerHand));
    }

    public void currentTurnIndicator(BlackJack blackJack) {
        if(blackJack.currentHand == blackJack.playerHand) {
            System.out.println(String.format("Turn to act : *** %s ***", blackJack.currentPlayer.getPlayerName()));
        } else if(blackJack.currentHand == blackJack.playerSplitHand) {
            System.out.println(String.format("Turn to act : *** %s Split Hand***", blackJack.currentPlayer.getPlayerName()));
        }else if(blackJack.currentHand == blackJack.dealerHand) {
            System.out.println("Turn to act : *** Dealer ***");
        }
    }
}
