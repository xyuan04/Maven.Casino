package io.zipcoder.casino;

public class DisplayMainCasinoScreens extends Displays{

    //MAIN SCREEN
    public void welcomeScreen() {
        println("                              ");
        println("        $$  $$                ");
        println("     $$$$$$$$$$$$$            ");
        println("    $$$$$$$$$$$$$$$           ");
        println("   $$$   $$  $$ $$$$          ");
        println("   $$$   $$  $$  $$$          ");
        println("   $$$   $$  $$               ");
        println("    $$$$ $$  $$               ");
        println("     $$$$$$$$$$$$$            ");
        println("      $$$$$$$$$$$$$           ");
        println("         $$  $$ $$$$          ");
        println("         $$  $$   $$$         ");
        println("    $$$  $$  $$   $$$         ");
        println("    $$$$ $$  $$ $$$$          ");
        println("     $$$$$$$$$$$$$$$          ");
        println("      $$$$$$$$$$$$            ");
        println("         $$  $$               ");
        println("                              ");
        println(" Welcome to The ZCW Casino!!! ");
        println("------------------------------");
        println("     00: Enter Casino         ");
        println("     99: Leave Casino         ");
        println("                              ");
    }

    public void invalidEntryWelcomeScreen() {
        println("                              ");
        println("   Invalid Entry. Try again   ");
        println("------------------------------");
        println("     00: Enter Casino         ");
        println("     99: Leave Casino         ");
        println("                              ");
    }

    public void goodbyeCasinoScreen() {
        println("                              ");
        println("   Have a wonderful day!      ");
        println("     Come again soon!         ");
        println("                              ");
    }

    //CASINO LOBBY
    public void casinoLobbyScreen() {
        println("                              ");
        println("     May the odds be          ");
        println("    ever in your favor!       ");
        println("------------------------------");
        println("     1: Teller Window         ");
        println("     2: *GoFish Room          ");
        println("     3: BlackJack Room        ");
        println("     4: Craps Room            ");
        println("     5: MOAK Room             ");
        println("     00: Leave                ");
        println("                              ");
        println(" *Don't need chips to play    ");
        println("                              ");
    }

    public void casinoLobbyInvalidScreen() {
        println("                              ");
        println("     Invalid input            ");
        println("                              ");
        println("------------------------------");
        println("    1: Teller Window          ");
        println("    2: *GoFish Room           ");
        println("    3: BlackJack Room         ");
        println("    4: Craps Room             ");
        println("    5: Poker Dice Room        ");
        println("    00: Leave                 ");
        println("                              ");
        println(" *Don't need chips to play    ");
        println("                              ");
    }

    //TELLER
    public void tellerMainScreen() {
        println("                              ");
        println("    How may I help you?       ");
        println("    -------------------       ");
        println("    1: Exchange money         ");
        println("    2: Cash Out               ");
        println("                              ");
    }

    public void tellerInvalidMainScreen() {
        println("                              ");
        println("    I don't understand...     ");
        println("    Please choose an option   ");
        println("    from the menu below       ");
        println("    -------------------       ");
        println("    1: Exchange money         ");
        println("    2: Cash Out               ");
        println("                              ");
    }

    public void tellerMoneyToChipsScreen(int currentChips, int currentWallet) {
        println("                              ");
        println("  Your wallet: %s             ", currentWallet);
        println("  Your chips: %s              ", currentChips);
        println("                              ");
        println("   How much money would       ");
        println("   you like to exchange?      ");
        println("   ---------------------      ");
        println("       $1 = 1 Chip            ");
        println("                              ");
    }

    public void tellerChipsToMoneyScreen(int numOfPlayerChips) {
        println("                              ");
        println("      You cashed out:         ");
        println("            $%s               ",numOfPlayerChips);
        println("   ---------------------      ");
        println("   1: Another transaction     ");
        println("   2: Exit                    ");
    }

    public void howManyChipsScreen (int cashIn) {
        println("                              ");
        println("  Your new chip balance:      ");
        println("          %s                  ", cashIn);
        println("                              ");
        println("   00: Casino Lobby           ");
        println("                              ");
    }

    public void loginOrCreateNew() {
        println("                              ");
        println("     1: Login                 ");
        println("                              ");
        println("     2: Create User           ");
        println("                              ");
    }

    public void enterUserID() {
        println("                              ");
        println("     Enter ID number          ");
        println("                              ");
    }

    public void accountMade(int playerId) {
        println("     Thanks for joining!      ");
        println("     Your ID number is:       ");
        println("                              ");
        println("       %s                     ", playerId);
        println("                              ");
        println("      00: Enter Casino        ");
    }
}
