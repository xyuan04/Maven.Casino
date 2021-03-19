package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;

public class MostOfAKindEngine {
    Player player;
    Casino casino = new Casino();
    MostOfAKindScreens mScreens = new MostOfAKindScreens();

    public MostOfAKindEngine(Player player) {
        this.player = player;
    }

    public void moak() {
        MostOfAKindGame moak = new MostOfAKindGame(player);
        Console console = new Console(System.in, System.out);
//        System.out.println(String.format("Hello %s", player.getPlayerName()));
//        System.out.println("Welcome To Most Of A Kind!");
        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To Most Of A Kind!");

        startMoakGame(moak, player, console);
    }

    private void startMoakGame(MostOfAKindGame moak, Player player, Console console) {
        mScreens.welcomeMoakScreen();
        System.out.println(player.getPlayerName());
        System.out.println(player.getChipBalance());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
            } else if (input == 2) {
                casinoLobby(moak, player, console);
            } else {
                welcomeInvalidScreen(moak, player, console);
            }
        }
    }

    //PLACEHOLDER
    private void casinoLobby(MostOfAKindGame moak, Player player, Console console) {
        casino.casinoLobbyScreen(player);
    }

    private void welcomeInvalidScreen(MostOfAKindGame moak, Player player, Console console) {
        mScreens.welcomeInvalidMoakScreen();
    }

    //Takes 5 chips, makes dealer and player hands
    private void anteUpScreen(MostOfAKindGame moak, Player player, Console console) {
        moak.clearPot();
        moak.clearPlayerHand();
        moak.clearDealerHand();
        mScreens.anteUpScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                if (player.getChipBalance() < 10) {
                    needMoreChipsScreen(moak, player, console);
                } else {
                    moak.anteUp();
                    moak.makePlayerHand(5);
                    moak.makeDealerHand(5);
                    firstRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void needMoreChipsScreen(MostOfAKindGame moak, Player player, Console console) {
        mScreens.needMoreChipsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby(moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void firstRollScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.firstRollScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                numberToKeepScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
            } else {
                getMoreChips(moak, player, console);
            }
        }
    }

    private void getMoreChips(MostOfAKindGame moak, Player player, Console console) {
        mScreens.getMoreChips();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby(moak, player, console);
            } else if (input == 1) {
                firstRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void numberToKeepScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.numberToKeepScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (moak.getPlayerHand().contains(input)) {
                moak.exchangePlayerDice(input);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                moak.exchangeDealerDice(moak.dealerMatchingNum);
                secondRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
            } else if (input == 0) {
                moak.clearPlayerHand();
                moak.makePlayerHand(5);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                secondRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void secondRollScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.secondRollScreen(pot, playerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                moak.playerNumOfMatches(playerHand);
                if (moak.checkWinner() == true) {
                    revealHandsPlayerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand(), moak, player, console);
                } else {
                    //catches when dealer wins
                    revealHandsDealerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand(), moak, player, console);
                }
            }
        }
    }

    private void revealHandsDealerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.revealHandsDealerWins(pot, playerHand, dealerHand);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
            } else if (input == 2) {
                goodbyeScreen(moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void revealHandsPlayerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.revealHandsPlayerWins(pot, playerHand, dealerHand);
        moak.playerWinsPot(moak.getPot());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
            } else if (input == 2) {
                goodbyeScreen(moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void goodbyeScreen(MostOfAKindGame moak, Player player, Console console) {
        mScreens.goodbyeScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby(moak, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
