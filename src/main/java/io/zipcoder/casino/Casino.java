package io.zipcoder.casino;


import io.zipcoder.casino.utilities.Console;

public class Casino {
    /*
    Want to double check how to call the engines
    Clarify which fields we need for this class

     */


    DisplayMainCasinoScreens casinoScreens = new DisplayMainCasinoScreens();
    Console console = new Console(System.in, System.out);




    public static void main(String[] args) {
        Casino casino = new Casino();
        PlayerWarehouse.initializePlayers();
        while (true) {
            casino.run();
        }
    }

    public void run() {
        welcome();
    }

    public void welcome() {
        casinoScreens.welcomeScreen();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                userLoginScreen();
                getOut = false;
            } else if (input == 99) {
                goodbyeCasinoScreen();
                getOut = false;
            } else {
                invalidEntryWelcomeScreen();
            }
        }
    }

    public void userLoginScreen() {
        casinoScreens.loginOrCreateNew();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                enterUserID();
                getOut = false;
            } else if (input == 2) {
                createNewAccount();
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void enterUserID() {
        casinoScreens.enterUserID();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (PlayerWarehouse.currentPlayers.containsKey(input)) {
                Player currentPlayer = PlayerWarehouse.currentPlayers.get(input);
                casinoLobbyScreen(currentPlayer);
                getOut = false;
            } else if (!PlayerWarehouse.currentPlayers.containsKey(input)) {
                System.out.println("Invalid ID");
                userLoginScreen();
                getOut = false;
            }
        }
    }

    public void createNewAccount() {
        String input = console.getStringInput("Enter Name");
        int wallet = console.getIntegerInput("Enter wallet size");
        Player newPlayer = PlayerFactory.createPlayer(input, wallet);
        PlayerWarehouse.addPlayer(newPlayer.getPlayerID(), newPlayer);
        casinoScreens.accountMade(newPlayer.getPlayerID());
        boolean getOut = true;
        while (getOut) {
            Integer newInput = console.getIntegerInput("");
            if (newInput == 00) {
                casinoLobbyScreen(newPlayer);
                getOut = false;
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
        boolean playing = true;
        while (playing) {
            casinoScreens.casinoLobbyScreen();
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
                playing = false;
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
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerMoneyToChipsScreen(currentPlayer);
                getOut = false;
            } else if (input == 2) {
                cashOutChips(currentPlayer.getChipBalance(), currentPlayer);
                currentPlayer.setChipBalance(0);
                getOut = false;
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
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow(currentPlayer);
                getOut = false;
            } else if (input == 2) {
                casinoLobbyScreen(currentPlayer);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void tellerMoneyToChipsScreen(Player currentPlayer) {
        ChipMoneyExchange exchange = new ChipMoneyExchange();
        casinoScreens.tellerMoneyToChipsScreen();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input <= currentPlayer.getWallet()) {
                int chips = exchange.moneyToChips(input);
                currentPlayer.getMoreChips(chips);
                chipBalanceScreen(currentPlayer.getChipBalance(), currentPlayer);
                getOut = false;
            } else {
                System.out.println("Not enough cash");
            }
        }
    }

    public void chipBalanceScreen(int chips, Player currentPlayer) {
        casinoScreens.howManyChipsScreen(chips);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                casinoLobbyScreen(currentPlayer);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
