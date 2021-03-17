package io.zipcoder.casino;

import java.util.ArrayList;

public class MostOfAKindScreens extends Displays{
    //WELCOME
    public void welcomeMoakScreen() {
        println("                             ");
        println("  Welcome to the MOAK Room!  ");
        println("  -------------------------  ");
        println("   Would you like to play?   ");
        println("                             ");
        println("         Y or N              ");
        println("                             ");
    }

    public void welcomeInvalidMoakScreen() {
        println("                             ");
        println("       Invalid Input         ");
        println("  -------------------------  ");
        println("   Would you like to play?   ");
        println("                             ");
        println("         Y or N              ");
        println("                             ");
    }

    public void anteUpScreen() {
        println("                             ");
        println("     Time to ante up!        ");
        println("                             ");
        println("    1: Pays and rolls        ");
        println("                             ");
    }

    //FIRST ROLL
    public void firstRollScreen(int pot, ArrayList<Integer> playerDice) {
        println("                             ");
        println("    Current pot: %s          ", pot);
        println("                             ");
        println("      Your Dice:             ");
        println("%s                            ", playerDice);
        println("                             ");
        println("How much do you want to bet? ");
        println("                             ");
    }

    public void numberToKeepScreen(int pot, ArrayList<Integer> playerDice) {
        println("                             ");
        println("    Current pot: %s          ", pot);
        println("                             ");
        println("      Your Dice:             ");
        println("%s                            ", playerDice);
        println("                             ");
        println("Which number do you want to keep?");
        println("                             ");
    }

    //SECOND ROLL
    public void secondRollScreen(int pot, ArrayList<Integer> playerDice) {
        println("                             ");
        println("    Current pot: %s          ", pot);
        println("                             ");
        println("      Your Dice:             ");
        println("%s                            ", playerDice);
        println("                             ");
        println("How much do you want to bet? ");
        println("                             ");
    }

    //FINAL STEP
    public void revealHandsPlayerWins(int pot, ArrayList<Integer> playerDice, ArrayList<Integer> dealerDice) {
        println("                             ");
        println("    Current pot: %s          ", pot);
        println("                             ");
        println("      Your Dice:             ");
        println("%s                            ", playerDice);
        println("                             ");
        println("    Dealer's Dice:           ");
        println("%s                           ", dealerDice);
        println("                             ");
        println("       YOU WIN!              ");
    }

    public void revealHandsDealerWins(int pot, ArrayList<Integer> playerDice, ArrayList<Integer> dealerDice) {
        println("                             ");
        println("    Current pot: %s          ", pot);
        println("                             ");
        println("      Your Dice:             ");
        println("%s                            ", playerDice);
        println("                             ");
        println("    Dealer's Dice:           ");
        println("%s                           ", dealerDice);
        println("                             ");
        println("      House wins             ");
        println("                             ");
    }


    //PLAY AGAIN
    public void playAgainScreen() {
        println("                             ");
        println(" Do you want to play again?  ");
        println("                             ");
        println("            Y or N           ");
        println("                             ");
    }

    public void goodbyeScreen() {
        println("                           ");
        println("     Good Luck!            ");
        println("                           ");
    }



}
