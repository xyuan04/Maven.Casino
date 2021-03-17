package io.zipcoder.casino;

public class CrapsScreens extends Displays {
    //WELCOME
    public void crapsWelcomeScreen() {
        println("                            ");
        println("                     HHHHH                                       ");
        println("                    H((((HHH                                     ");
        println("                  (H((((((((HHX                                  ");
        println("                  HH((((((HHHHXXX                                ");
        println("                 XHH((HHHHHHXXXXX                                ");
        println("              HH((HHHHHHHXXXXXXXX                                ");
        println("            XHHHHHHHHXXXXXXXXXXHH((HHX                           ");
        println("           ,XHXXXXXXXXXXXXXHH((((HHHHHX                          ");
        println("            X,......,HHHH((((H,.... .,X                          ");
        println("         HHH.  @@@@  .(((HHHH.  @@@@  .HHH                       ");
        println("       HH(((  @@@@@@  .HHHXX.  @@@@@@  *HHHX                     ");
        println("      XHHHHH.  @@@@&  HXXXXXX  *@@@@  .HHHXXX                    ");
        println("      XXXXXXX.      .XHHHH((((.      .XXXXXXX                    ");
        println("      XHHHHHHHHHH(((((((((HHHHHHHHXXXXXXXXXHHH                   ");
        println("  HH((((((((((((///////////////////XXXXXHHH(HHHXX                ");
        println(" HHHHHHHHHHHHHHHHH.             ,XXXXHH(((HHHHXXXXX              ");
        println("XXHHHHHHHHHHHHXXXXXXXXX,..,*XXXXHHH((((HHHHXXXXXXXX              ");
        println(" XXXXXXXXXXXXXXXXXXXXXXXXXHHHH(((((HHHHXXXXXXXXXXX               ");
        println("   XXXXXXXXXXXXXXXXHHHHHHHHHHHHHHHXXXXXXXXXXXXX&                 ");
        println("                           ");
        println("       ARE YOU READY TO PLAY SOME CRAPS?   ");
        println("                    Y or N            ");
        println("                           ");
    }

    public void crapsInvalidWelcomeScreen() {
        println("                           ");
        println("            Invalid Entry      ");
        println("            -------------               ");
        println("   ARE YOU READY TO PLAY SOME CRAPS?   ");
        println("               Y or N            ");
        println("                           ");
    }

    //ROUND ONE PREP
    public void passOrNotPassRoundOneScreen(int roundOfGame) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("  How are you betting?     ");
        println("  --------------------     ");
        println("     1: Pass               ");
        println("     2: Not Pass           ");
        println("                           ");
    }

    public void betAmountRoundOneScreen(int roundOfGame) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println(" How much are you betting? ");
        println(" ------------------------- ");
        println("                           ");
    }

    public void rollTheDice() {
        println("                           ");
        println("   Enter 1 to roll dice    ");
        println("                           ");
    }


    //ROUND TWO SCREENS
    public void passOrNotPassRoundTwoScreen(int roundOfGame, int pot, int sumOfDice, String betStatus, int pointer) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("    Bet Status: %s         ", betStatus);
        println("                           ");
        println("   Chips in pot: %s        ", pot);
        println("                           ");
        println("      Pointer: %s          ", pointer);
        println("                           ");
        println("     Your roll: %s         ", sumOfDice);
        println("                           ");
        println("  How are you betting?     ");
        println("  --------------------     ");
        println("     1: Pass               ");
        println("     2: Not Pass           ");
        println("                           ");
    }

    public void betAmountRoundTwoScreen(int roundOfGame, String betStatus, int pot, int pointer, int sumOfDice) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("    Bet Status: %s         ", betStatus);
        println("                           ");
        println("   Chips in pot: %s        ", pot);
        println("                           ");
        println("      Pointer: %s          ", pointer);
        println("                           ");
        println("     Your roll: %s         ", sumOfDice);
        println("                           ");
        println(" How much are you betting? ");
        println(" ------------------------- ");
        println("                           ");
    }


    //OUTCOME OPTIONS
    public void winRollScreen(int roundOfGame, int pot, int sumOfDice, String betStatus) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("     Bet Status: %s        ", betStatus);
        println("                           ");
        println("    Chips in Pot: %s       ", pot);
        println("                           ");
        println("    You rolled a %s        ", sumOfDice);
        println("    ---------------        ");
        println("       You Win!            ");
        println("                           ");
    }

    public void loseRollScreen(int roundOfGame, int pot, int sumOfDice, String betStatus) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("     Bet Status: %s        ", betStatus);
        println("                           ");
        println("    Chips in Pot: %s       ", pot);
        println("                           ");
        println("    You rolled a %s        ", sumOfDice);
        println("    ---------------        ");
        println("      You Lose...          ");
        println("                           ");
    }

    public void rollAgainScreen(int roundOfGame, int pot, int sumOfDice, String betStatus, int pointer) {
        println("                           ");
        println("       ROUND: %s           ", roundOfGame);
        println("                           ");
        println("     Bet Status: %s        ", betStatus);
        println("                           ");
        println("    Chips in Pot: %s       ", pot);
        println("                           ");
        println("    You rolled a %s        ", sumOfDice);
        println("                           ");
        println("      Pointer: %s          ", pointer);
        println("    ---------------        ");
        println("      Roll again!          ");
        println("        Enter 1            ");
        println("                           ");
    }


    //ENDING SCREENS
    public void playAgainScreen() {
        println("                           ");
        println("     Play Again?           ");
        println("     -----------           ");
        println("       Y or N              ");
        println("                           ");
    }

    public void leaveCrapsScreen() {
        println("                           ");
        println("     Good Luck!            ");
        println("                           ");
    }
}


