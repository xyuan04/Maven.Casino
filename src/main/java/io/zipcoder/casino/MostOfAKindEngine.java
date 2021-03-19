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
        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To Most Of A Kind!");

        startMoakGame(moak, player, console);
    }

    private void startMoakGame(MostOfAKindGame moak, Player player, Console console) {
        mScreens.welcomeMoakScreen();
        System.out.println(player.getPlayerName());
        System.out.println(player.getChipBalance());
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
                getOut = false;
            } else if (input == 2) {
                getOut = false;
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
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                if (player.getChipBalance() < 10) {
                    //needMoreChipsScreen(moak, player, console);
                    System.out.println("Insufficient Chips");
                } else {
                    moak.anteUp();
                    moak.makePlayerHand(5);
                    moak.makeDealerHand(5);
                    firstRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
                }
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

//    private void needMoreChipsScreen(MostOfAKindGame moak, Player player, Console console) {
//        mScreens.needMoreChipsScreen();
//        boolean getOut = true;
//        while (getOut) {
//            Integer input = console.getIntegerInput("");
//            if (input == 0) {
//                getOut = false;
//            } else {
//                System.out.println("Invalid Entry");
//            }
//        }
//    }

    private void firstRollScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.firstRollScreen(pot, playerHand);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                numberToKeepScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
                getOut = false;
            } else {
                System.out.println("Insufficient Chips");;
            }
        }
    }

//    private void getMoreChips(MostOfAKindGame moak, Player player, Console console) {
//        mScreens.getMoreChips();
//        boolean getOut = true;
//        while (getOut) {
//            Integer input = console.getIntegerInput("");
//            if (input == 0) {
//                getOut = false;
//            } else if (input == 1) {
//                firstRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
//            } else {
//                System.out.println("Invalid Entry");
//            }
//        }
//    }

    private void numberToKeepScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.numberToKeepScreen(pot, playerHand);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (moak.getPlayerHand().contains(input)) {
                moak.exchangePlayerDice(input);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                moak.exchangeDealerDice(moak.dealerMatchingNum);
                secondRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
                getOut = false;
            } else if (input == 0) {
                moak.clearPlayerHand();
                moak.makePlayerHand(5);
                moak.dealerAINumbersToKeep(moak.getDealerHand());
                secondRollScreen(moak.getPot(), moak.getPlayerHand(), moak, player, console);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void secondRollScreen(int pot, ArrayList<Integer> playerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.secondRollScreen(pot, playerHand);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                moak.addToPot(input);
                moak.playerNumOfMatches(playerHand);
                moak.dealerNumOfMatches(moak.dealerHand);
                if (moak.checkWinner() == true) {
                    revealHandsPlayerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand(), moak, player, console);
                    getOut = false;
                } else if (moak.checkWinner() == false) {
                    //catches when dealer wins
                    revealHandsDealerWins(moak.getPot(), moak.getPlayerHand(), moak.getDealerHand(), moak, player, console);
                    getOut = false;
                }
            } else {
                System.out.println("Insufficient Chips");
            }
        }
    }

    private void revealHandsDealerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.revealHandsDealerWins(pot, playerHand, dealerHand);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
                getOut = false;
            } else if (input == 2) {
                System.out.println("Goodbye and good luck!");
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void revealHandsPlayerWins(int pot, ArrayList<Integer> playerHand, ArrayList<Integer> dealerHand, MostOfAKindGame moak, Player player, Console console) {
        mScreens.revealHandsPlayerWins(pot, playerHand, dealerHand);
        moak.playerWinsPot(moak.getPot());
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                anteUpScreen(moak, player, console);
                getOut = false;
            } else if (input == 2) {
                System.out.println("Goodbye and good luck!");
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

//    private void goodbyeScreen(MostOfAKindGame moak, Player player, Console console) {
//        mScreens.goodbyeScreen();
//        while (getOut) {
//            Integer input = console.getIntegerInput("");
//            if (input == 0) {
//                getOut = false;
//            } else {
//                System.out.println("Invalid Entry");
//            }
//        }
//    }
}
