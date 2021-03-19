package io.zipcoder.casino;


import io.zipcoder.casino.utilities.Console;

public class Casino {
    /*
    Want to double check how to call the engines
    Clarify which fields we need for this class

     */


    DisplayMainCasinoScreens casinoScreens = new DisplayMainCasinoScreens();
    Console console = new Console(System.in, System.out);
//    GoFish goFish = new GoFish();
//    BlackJackEngine blackJack = new BlackJackEngine();
//    CrapsEngine crapsEngine = new CrapsEngine(Player player);
//    MostOfAKindEngine moak = new MostOfAKindEngine();
    ChipMoneyExchange exchange = new ChipMoneyExchange();



    public static void main(String[] args) {
        Casino casino = new Casino();
        PlayerWarehouse.initializePlayers();
        while (true) {
            casino.run();
        }
    }

    public void run() {
        welcome();
//        casinoScreens.welcomeScreen();
//        while (true) {
//            Integer input = console.getIntegerInput("");
//            if (input == 00) {
//                userLoginScreen();
//            } else if (input == 99) {
//                goodbyeCasinoScreen();
//            } else {
//                invalidEntryWelcomeScreen();
//            }
//        }
    }

    public void welcome() {
        casinoScreens.welcomeScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                userLoginScreen();
            } else if (input == 99) {
                goodbyeCasinoScreen();
            } else {
                invalidEntryWelcomeScreen();
            }
        }
    }

    public void userLoginScreen() {
        casinoScreens.loginOrCreateNew();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                enterUserID();
            } else if (input == 2) {
                createNewAccount();
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void enterUserID() {
        casinoScreens.enterUserID();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (PlayerWarehouse.currentPlayers.containsKey(input)) {
                Player currentPlayer = PlayerWarehouse.currentPlayers.get(input);
                casinoLobbyScreen(currentPlayer);
            } else if (!PlayerWarehouse.currentPlayers.containsKey(input)) {
                System.out.println("Invalid ID");
                userLoginScreen();
            }
        }
    }

    public void createNewAccount() {
        String input = console.getStringInput("Enter Name");
        int wallet = console.getIntegerInput("Enter wallet size");
        Player newPlayer = PlayerFactory.createPlayer(input, wallet);
        PlayerWarehouse.addPlayer(newPlayer.getPlayerID(), newPlayer);
        casinoScreens.accountMade(newPlayer.getPlayerID());
        while (true) {
            Integer newInput = console.getIntegerInput("");
            if (newInput == 00) {
                casinoLobbyScreen(newPlayer);
            } else {
                System.out.println("Invalid entry");
            }
        }
    }

    public void invalidEntryWelcomeScreen() {
        casinoScreens.invalidEntryWelcomeScreen();
    }

    public void goodbyeCasinoScreen() {
        casinoScreens.goodbyeCasinoScreen();
        System.exit(0);
    }

    public void casinoLobbyScreen(Player currentPlayer) {
        casinoScreens.casinoLobbyScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow(currentPlayer);
            } else if (input == 2) {
                GoFish goFish = new GoFish(currentPlayer);
                goFish.playGame();
            } else if (input == 3) {
                BlackJackEngine blackJack = new BlackJackEngine(currentPlayer);
                blackJack.blackJack();
            } else if (input == 4) {
                CrapsEngine crapsEngine = new CrapsEngine(currentPlayer);
                crapsEngine.craps();
            } else if (input == 5) {
                MostOfAKindEngine moak = new MostOfAKindEngine(currentPlayer);
                moak.moak();
            } else if (input == 00) {
                run();
            } else {
                casinoLobbyInvalidScreen();
            }
        }
    }

    public void casinoLobbyInvalidScreen() {
        casinoScreens.casinoLobbyInvalidScreen();
    }

    public void tellerWindow(Player currentPlayer) {
        casinoScreens.tellerMainScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerMoneyToChipsScreen(currentPlayer);
            } else if (input == 2) {
                cashOutChips(currentPlayer.getChipBalance(), currentPlayer);
                currentPlayer.setChipBalance(0);
            } else {
                tellerInvalidMainScreen();
            }
        }
    }

    public void tellerInvalidMainScreen() {
        casinoScreens.tellerInvalidMainScreen();
    }

    public void cashOutChips(int chipsCashOut, Player currentPlayer) {
        casinoScreens.tellerChipsToMoneyScreen(chipsCashOut);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow(currentPlayer);
            } else if (input == 2) {
                casinoLobbyScreen(currentPlayer);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void tellerMoneyToChipsScreen(Player currentPlayer) {
        casinoScreens.tellerMoneyToChipsScreen();
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input <= currentPlayer.getWallet()) {
                int chips = exchange.moneyToChips(input);
                currentPlayer.getMoreChips(chips);
                chipBalanceScreen(currentPlayer.getChipBalance(), currentPlayer);
            } else {
                System.out.println("Not enough cash");
            }
        }
    }

    public void chipBalanceScreen(int chips, Player currentPlayer) {
        casinoScreens.howManyChipsScreen(chips);
        while (true) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                casinoLobbyScreen(currentPlayer);
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
