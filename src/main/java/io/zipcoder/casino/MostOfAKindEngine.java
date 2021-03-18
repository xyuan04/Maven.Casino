package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;

public class MostOfAKindEngine {
    Console console = new Console(System.in, System.out);
    MostOfAKindScreens mScreens = new MostOfAKindScreens();
    Player player = new Player("gerg", 500);
    MostOfAKindGame moak = new MostOfAKindGame(player);

    public static void main(String[] args) {
        MostOfAKindEngine moak = new MostOfAKindEngine();
        moak.startMoakGame();
    }

    private void startMoakGame() {
        mScreens.welcomeMoakScreen();
        player.setChipBalance(100);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen();
            } else if (input == 2) {
                casinoLobby();
            } else {
                welcomeInvalidScreen();
            }
        }
    }

    //PLACEHOLDER
    private void casinoLobby() {
        System.exit(0);
    }

    private void welcomeInvalidScreen() {
        mScreens.welcomeInvalidMoakScreen();
    }

    //Takes 5 chips, makes dealer and player hands
    private void anteUpScreen() {
        moak.clearPot();
        moak.clearPlayerHand();
        moak.clearDealerHand();
        mScreens.anteUpScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                if (player.getChipBalance() < 10) {
                    needMoreChipsScreen();
                } else {
                    moak.anteUp();
                    moak.makePlayerHand(5);
                    moak.makeDealerHand(5);
                    firstRollScreen(moak.getPot(), moak.getPlayerHand());
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void needMoreChipsScreen() {
        mScreens.needMoreChipsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void firstRollScreen(int pot, ArrayList<Integer> playerHand) {
        mScreens.firstRollScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                numberToKeepScreen(moak.getPot(), moak.getPlayerHand());
            } else {
                getMoreChips();
            }
        }
    }

    private void getMoreChips() {
        mScreens.getMoreChips();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby();
            } else if (input == 1) {
                firstRollScreen(moak.getPot(), moak.getPlayerHand());
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void numberToKeepScreen(int pot, ArrayList<Integer> playerHand) {
        mScreens.numberToKeepScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (moak.getPlayerHand().contains(input)) {
                moak.exchangePlayerDice(input);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                moak.exchangeDealerDice(moak.dealerMatchingNum);
                secondRollScreen(moak.getPot(), moak.getPlayerHand());
            } else if (input == 0) {
                moak.clearPlayerHand();
                moak.makePlayerHand(5);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                secondRollScreen(moak.getPot(), moak.getPlayerHand());
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void secondRollScreen(int pot, ArrayList<Integer> playerHand) {
        mScreens.secondRollScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                moak.playerNumOfMatches(playerHand);
                if (moak.checkWinner() == true) {
                    revealHandsPlayerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand());
                } else {
                    //catches when dealer wins
                    revealHandsDealerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand());
                }
            }
        }
    }

    private void revealHandsDealerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand) {
        mScreens.revealHandsDealerWins(pot, playerHand, dealerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen();
            } else if (input == 2) {
                goodbyeScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void revealHandsPlayerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand) {
        mScreens.revealHandsPlayerWins(pot, playerHand, dealerHand);
        moak.playerWinsPot(moak.getPot());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen();
            } else if (input == 2) {
                goodbyeScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void goodbyeScreen() {
        mScreens.goodbyeScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
