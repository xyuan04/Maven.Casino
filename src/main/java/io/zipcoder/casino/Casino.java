package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;

public class Casino {

    public void run() {
        welcome();
    }

    public void welcome() {
        DisplayMainCasinoScreens casinoScreens = new DisplayMainCasinoScreens();
        Console console = new Console(System.in, System.out);
        casinoScreens.welcomeScreen();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                userLoginScreen(casinoScreens, console);
                getOut = false;
            } else if (input == 99) {
                goodbyeCasinoScreen(casinoScreens);
                getOut = false;
            } else {
                invalidEntryWelcomeScreen(casinoScreens);
            }
        }
    }

    public void userLoginScreen(DisplayMainCasinoScreens casinoScreens, Console console) {
        casinoScreens.loginOrCreateNew();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                enterUserID(casinoScreens, console);
                getOut = false;
            } else if (input == 2) {
                createNewAccount(casinoScreens, console);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void enterUserID(DisplayMainCasinoScreens casinoScreens, Console console) {
        casinoScreens.enterUserID();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (PlayerWarehouse.currentPlayers.containsKey(input)) {
                Player currentPlayer = PlayerWarehouse.currentPlayers.get(input);
                casinoLobbyScreen(currentPlayer, casinoScreens, console);
                getOut = false;
            } else if (!PlayerWarehouse.currentPlayers.containsKey(input)) {
                System.out.println("Invalid ID");
                userLoginScreen(casinoScreens, console);
                getOut = false;
            }
        }
    }

    public void createNewAccount(DisplayMainCasinoScreens casinoScreens, Console console) {
        String input = console.getStringInput("Enter Name");
        int wallet = console.getIntegerInput("Enter wallet size");
        Player newPlayer = PlayerFactory.createPlayer(input, wallet);
        PlayerWarehouse.addPlayer(newPlayer.getPlayerID(), newPlayer);
        casinoScreens.accountMade(newPlayer.getPlayerID());
        boolean getOut = true;
        while (getOut) {
            Integer newInput = console.getIntegerInput("");
            if (newInput == 00) {
                casinoLobbyScreen(newPlayer, casinoScreens, console);
                getOut = false;
            } else {
                System.out.println("Invalid entry");
            }
        }
    }

    public void invalidEntryWelcomeScreen(DisplayMainCasinoScreens casinoScreens) {
        casinoScreens.invalidEntryWelcomeScreen();
    }

    public void goodbyeCasinoScreen(DisplayMainCasinoScreens casinoScreens) {
        casinoScreens.goodbyeCasinoScreen();
        System.exit(0);
    }

    public void casinoLobbyScreen(Player currentPlayer, DisplayMainCasinoScreens casinoScreens, Console console) {
        boolean playing = true;
        while (playing) {
            casinoScreens.casinoLobbyScreen();
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow(currentPlayer, casinoScreens, console);
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
                casinoLobbyInvalidScreen(casinoScreens);
            }
        }
    }

    public void casinoLobbyInvalidScreen(DisplayMainCasinoScreens casinoScreens) {
        casinoScreens.casinoLobbyInvalidScreen();
    }

    public void tellerWindow(Player currentPlayer, DisplayMainCasinoScreens casinoScreens, Console console) {
        casinoScreens.tellerMainScreen();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerMoneyToChipsScreen(currentPlayer, casinoScreens, console);
                getOut = false;
            } else if (input == 2) {
                cashOutChips(currentPlayer.getChipBalance(), currentPlayer, casinoScreens, console);
                currentPlayer.setChipBalance(0);
                getOut = false;
            } else {
                tellerInvalidMainScreen(casinoScreens);
            }
        }
    }

    public void tellerInvalidMainScreen(DisplayMainCasinoScreens casinoScreens) {
        casinoScreens.tellerInvalidMainScreen();
    }

    public void cashOutChips(int chipsCashOut, Player currentPlayer, DisplayMainCasinoScreens casinoScreens, Console console) {
        casinoScreens.tellerChipsToMoneyScreen(chipsCashOut);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 1) {
                tellerWindow(currentPlayer, casinoScreens, console);
                getOut = false;
            } else if (input == 2) {
                casinoLobbyScreen(currentPlayer, casinoScreens, console);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }

    public void tellerMoneyToChipsScreen(Player currentPlayer, DisplayMainCasinoScreens casinoScreens, Console console) {
        ChipMoneyExchange exchange = new ChipMoneyExchange();
        casinoScreens.tellerMoneyToChipsScreen();
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input <= currentPlayer.getWallet()) {
                int chips = exchange.moneyToChips(input);
                currentPlayer.getMoreChips(chips);
                chipBalanceScreen(currentPlayer.getChipBalance(), currentPlayer, casinoScreens, console);
                getOut = false;
            } else {
                System.out.println("Not enough cash");
            }
        }
    }

    public void chipBalanceScreen(int chips, Player currentPlayer, DisplayMainCasinoScreens casinoScreens, Console console) {
        casinoScreens.howManyChipsScreen(chips);
        boolean getOut = true;
        while (getOut) {
            Integer input = console.getIntegerInput("");
            if (input == 00) {
                casinoLobbyScreen(currentPlayer, casinoScreens, console);
                getOut = false;
            } else {
                System.out.println("Invalid Entry");
            }
        }
    }
}
