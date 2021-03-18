package io.zipcoder.casino;

import com.sun.tools.javac.util.Assert;
import io.zipcoder.casino.utilities.Console;

public class CrapsEngine {
    Console console = new Console(System.in, System.out);
    CrapsScreens cScreens = new CrapsScreens();
    Player player = new Player("gerg", 500);
    Craps craps = new Craps(player);



    public static void main(String[] args) {
        CrapsEngine craps = new CrapsEngine();
        craps.startCrapsGame();
    }

    private void startCrapsGame() {
        cScreens.crapsWelcomeScreen();
        player.setChipBalance(100);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                passOrNotPassRoundOneScreen(craps.getGameRound());
            } else if (input.equals(2)) {
                casinoLobby();
            } else {
                crapsInvalidWelcomeScreen();
            }
        }
    }

    private void crapsInvalidWelcomeScreen() {
        cScreens.crapsInvalidWelcomeScreen();
    }

    //TEMPORARY
    private void casinoLobby() {
        System.exit(0);
    }

    private void passOrNotPassRoundOneScreen(int gameRound) {
        craps.setGameRound(1);
        craps.clearPot();
        cScreens.passOrNotPassRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setBetStatus("Pass");
                betAmountRoundOneScreen();
            } else if (input.equals(2)) {
                craps.setBetStatus("Not Pass");
                betAmountRoundOneScreen();
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    private void betAmountRoundOneScreen() {
        cScreens.betAmountRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                craps.addToPot(input);
                rollTheDice();
            } else {
                getMoreChips();
            }
        }
    }

    private void getMoreChips() {
        cScreens.getMoreChips();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby();
            } else if (input == 1) {
                betAmountRoundOneScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void rollTheDice() {
        cScreens.rollTheDice();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                craps.sumOfDice();
                if (craps.getBetStatus().equalsIgnoreCase("Pass")) {
                    if (craps.getCurrentSum() == 7 || craps.getCurrentSum() == 11) {
                        winRollScreen();
                    } else if (craps.getCurrentSum() == 2 || craps.getCurrentSum() == 3 || craps.getCurrentSum() == 12) {
                        loseRollScreen();
                    } else {
                        craps.setPointer(craps.getCurrentSum());
                        rollAgainScreen();
                    }
                } else if (craps.getBetStatus().equalsIgnoreCase("Not Pass")) {
                    if (craps.getCurrentSum() == 2 || craps.getCurrentSum() == 3 || craps.getCurrentSum() == 12) {
                        winRollScreen();
                    } else if (craps.getCurrentSum() == 7 || craps.getCurrentSum() == 11) {
                        loseRollScreen();
                    } else {
                        craps.setPointer(craps.getCurrentSum());
                        rollAgainScreen();
                    }
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void loseRollScreen() {
        cScreens.loseRollScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setGameRound(1);
                passOrNotPassRoundOneScreen(craps.getGameRound());
            } else if (input.equals(2)) {
                goodLuckScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void goodLuckScreen() {
        cScreens.leaveCrapsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void winRollScreen() {
        cScreens.winRollScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus());
        craps.playerWinsPot(craps.getPot());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setGameRound(1);
                passOrNotPassRoundOneScreen(craps.getGameRound());
            } else if (input.equals(2)) {
                goodLuckScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void rollAgainScreen() {
        cScreens.passOrNotPassRoundTwoScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setBetStatus("Pass");
                rollAgainBetScreen();
            } else if (input.equals(2)) {
                craps.setBetStatus("Not Pass");
                rollAgainBetScreen();
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    private void rollAgainBetScreen() {
        cScreens.rollAgainBetScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >=0) {
                craps.addToPot(input);
                rollTheDiceOnward();
            } else {
                System.out.println("Insufficient Chips");
            }
        }
    }

    private void rollTheDiceOnward() {
        craps.setGameRound(2);
        cScreens.rollAgainOnward(craps.getGameRound(),craps.getBetStatus(), craps.getPot(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                craps.sumOfDice();
                if (craps.getBetStatus().equalsIgnoreCase("Pass")) {
                    if (craps.getCurrentSum() == craps.getPointer()) {
                        winRollScreen();
                    } else if (craps.getCurrentSum() == 7) {
                        loseRollScreen();
                    } else {
                        rollAgainBetScreen();
                    }
                } else if (craps.getBetStatus().equalsIgnoreCase("Not Pass")) {
                    if (craps.getCurrentSum() == 7) {
                        winRollScreen();
                    } else if (craps.getCurrentSum() == craps.getPointer()) {
                        loseRollScreen();
                    } else {
                        rollAgainBetScreen();
                    }
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
