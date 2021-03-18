package io.zipcoder.casino;


import io.zipcoder.casino.utilities.Console;

public class Casino {
    /*
    Want to double check how to call the engines
    Clarify which fields we need for this class

     */


    DisplayMainCasinoScreens casinoScreens = new DisplayMainCasinoScreens();
    Console console = new Console(System.in, System.out);
    GoFish goFish = new GoFish();
    BlackJackEngine blackJack = new BlackJackEngine();
    CrapsEngine crapsEngine = new CrapsEngine();
    MostOfAKindEngine moak = new MostOfAKindEngine();
    ChipMoneyExchange exchange = new ChipMoneyExchange();
    Player currentPlayer = new Player("gerg", 500);

    public static void main(String[] args) {
        Casino casino = new Casino();
        casino.run();
    }

    public void run() {

        casinoScreens.welcomeScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                casinoLobbyScreen();
            } else if (input == 99) {
                goodbyeCasinoScreen();
            } else {
                invalidEntryWelcomeScreen();
            }
        }
    }

    private void invalidEntryWelcomeScreen() {
        casinoScreens.invalidEntryWelcomeScreen();
    }

    private void goodbyeCasinoScreen() {
        casinoScreens.goodbyeCasinoScreen();
        System.exit(0);
    }

    private void casinoLobbyScreen() {
        casinoScreens.casinoLobbyScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow();
            } else if (input == 2) {
                //I think this is what I need to call...
                goFish.playGame();
            } else if (input == 3) {
                //Double check this is right
                blackJack.blackJack();
            } else if (input == 4) {
                //craps
            } else if (input == 5) {
                //most of a kind
            } else if (input == 00) {
                goodbyeCasinoScreen();
            } else {
                casinoLobbyInvalidScreen();
            }
        }
    }

    private void casinoLobbyInvalidScreen() {
        casinoScreens.casinoLobbyInvalidScreen();
    }

    private void tellerWindow() {
        casinoScreens.tellerMainScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerMoneyToChipsScreen();
            } else if (input == 2) {
                cashOutChips(currentPlayer.getChipBalance());
                currentPlayer.setChipBalance(0);
            } else {
                tellerInvalidMainScreen();
            }
        }
    }

    private void tellerInvalidMainScreen() {
        casinoScreens.tellerInvalidMainScreen();
    }

    private void cashOutChips(int chipsCashOut) {
        casinoScreens.tellerChipsToMoneyScreen(chipsCashOut);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow();
            } else if (input == 2) {
                System.exit(0);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void tellerMoneyToChipsScreen() {
        casinoScreens.tellerMoneyToChipsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= currentPlayer.getWallet()) {
                int chips = exchange.moneyToChips(input);
                currentPlayer.setChipBalance(chips);
                chipBalanceScreen(currentPlayer.getChipBalance());
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    private void chipBalanceScreen(int chips) {
        casinoScreens.howManyChipsScreen(chips);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                casinoLobbyScreen();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
