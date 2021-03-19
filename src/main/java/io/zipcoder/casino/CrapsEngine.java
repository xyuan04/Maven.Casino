package io.zipcoder.casino;

import com.sun.tools.javac.util.Assert;
import io.zipcoder.casino.utilities.Console;

public class CrapsEngine {
    CrapsScreens cScreens = new CrapsScreens();
//    DisplayMainCasinoScreens casinoScreens = new DisplayMainCasinoScreens();
    Player player;
    Casino casino = new Casino();


    public CrapsEngine(Player player) {
        this.player = player;
    }

    public void craps() {
        Craps craps = new Craps(player);
        Console console = new Console(System.in, System.out);

        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To Craps!");

        startCrapsGame(craps, player, console);
    }


    public void startCrapsGame(Craps craps, Player player, Console console) {
        cScreens.crapsWelcomeScreen();
        System.out.println(player.getPlayerName());
        System.out.println(player.getChipBalance());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                passOrNotPassRoundOneScreen(craps.getGameRound(), craps, player, console);
            } else if (input.equals(2)) {
                casinoLobby(player);
            } else {
                crapsInvalidWelcomeScreen(craps, player, console);
            }
        }
    }

    public void crapsInvalidWelcomeScreen(Craps craps, Player player, Console console) {
        cScreens.crapsInvalidWelcomeScreen();
    }


    public void casinoLobby(Player player) {
        casino.casinoLobbyScreen(player);
    }

    public void passOrNotPassRoundOneScreen(int gameRound, Craps craps, Player player, Console console) {
        craps.setGameRound(1);
        craps.clearPot();
        cScreens.passOrNotPassRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setBetStatus("Pass");
                betAmountRoundOneScreen(craps, player, console);
            } else if (input.equals(2)) {
                craps.setBetStatus("Not Pass");
                betAmountRoundOneScreen(craps, player, console);
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    public void betAmountRoundOneScreen(Craps craps, Player player, Console console) {
        cScreens.betAmountRoundOneScreen(craps.getGameRound());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >= 0) {
                craps.addToPot(input);
                rollTheDice(craps, player, console);
            } else {
                getMoreChips(craps, player, console);
            }
        }
    }

    public void getMoreChips(Craps craps, Player player, Console console) {
        cScreens.getMoreChips();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby(player);
            } else if (input == 1) {
                betAmountRoundOneScreen(craps, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void rollTheDice(Craps craps, Player player, Console console) {
        cScreens.rollTheDice();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                craps.sumOfDice();
                if (craps.getBetStatus().equalsIgnoreCase("Pass")) {
                    if (craps.getCurrentSum() == 7 || craps.getCurrentSum() == 11) {
                        winRollScreen(craps, player, console);
                    } else if (craps.getCurrentSum() == 2 || craps.getCurrentSum() == 3 || craps.getCurrentSum() == 12) {
                        loseRollScreen(craps, player, console);
                    } else {
                        craps.setPointer(craps.getCurrentSum());
                        rollAgainScreen(craps, player, console);
                    }
                } else if (craps.getBetStatus().equalsIgnoreCase("Not Pass")) {
                    if (craps.getCurrentSum() == 2 || craps.getCurrentSum() == 3 || craps.getCurrentSum() == 12) {
                        winRollScreen(craps, player, console);
                    } else if (craps.getCurrentSum() == 7 || craps.getCurrentSum() == 11) {
                        loseRollScreen(craps, player, console);
                    } else {
                        craps.setPointer(craps.getCurrentSum());
                        rollAgainScreen(craps, player, console);
                    }
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void loseRollScreen(Craps craps, Player player, Console console) {
        cScreens.loseRollScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setGameRound(1);
                passOrNotPassRoundOneScreen(craps.getGameRound(), craps, player, console);
            } else if (input.equals(2)) {
                goodLuckScreen(craps, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void goodLuckScreen(Craps craps, Player player, Console console) {
        cScreens.leaveCrapsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 0) {
                casinoLobby(player);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void winRollScreen(Craps craps, Player player, Console console) {
        cScreens.winRollScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus());
        craps.playerWinsPot(craps.getPot());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setGameRound(1);
                passOrNotPassRoundOneScreen(craps.getGameRound(), craps, player, console);
            } else if (input.equals(2)) {
                goodLuckScreen(craps, player, console);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void rollAgainScreen(Craps craps, Player player, Console console) {
        cScreens.passOrNotPassRoundTwoScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input.equals(1)) {
                craps.setBetStatus("Pass");
                rollAgainBetScreen(craps, player, console);
            } else if (input.equals(2)) {
                craps.setBetStatus("Not Pass");
                rollAgainBetScreen(craps, player, console);
            } else {
                System.out.println("Please enter 1 or 2");
            }
        }
    }

    public void rollAgainBetScreen(Craps craps, Player player, Console console) {
        cScreens.rollAgainBetScreen(craps.getGameRound(), craps.getPot(), craps.getCurrentSum(), craps.getBetStatus(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= player.getChipBalance() && input >=0) {
                craps.addToPot(input);
                rollTheDiceOnward(craps, player, console);
            } else {
                System.out.println("Insufficient Chips");
            }
        }
    }

    private void rollTheDiceOnward(Craps craps, Player player, Console console) {
        craps.setGameRound(2);
        cScreens.rollAgainOnward(craps.getGameRound(),craps.getBetStatus(), craps.getPot(), craps.getPointer());
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                craps.sumOfDice();
                if (craps.getBetStatus().equalsIgnoreCase("Pass")) {
                    if (craps.getCurrentSum() == craps.getPointer()) {
                        winRollScreen(craps, player, console);
                    } else if (craps.getCurrentSum() == 7) {
                        loseRollScreen(craps, player, console);
                    } else {
                        rollAgainBetScreen(craps, player, console);
                    }
                } else if (craps.getBetStatus().equalsIgnoreCase("Not Pass")) {
                    if (craps.getCurrentSum() == 7) {
                        winRollScreen(craps, player, console);
                    } else if (craps.getCurrentSum() == craps.getPointer()) {
                        loseRollScreen(craps, player, console);
                    } else {
                        rollAgainBetScreen(craps, player, console);
                    }
                }
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
