package io.zipcoder.casino;

import java.util.Scanner;

public class Displays {

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public static void print(String output, Object... args) {
        System.out.printf(output, args);
    }

    public static void println(String output, Object... args) {
        print(output + "\n", args);
    }

    public void notEnoughMoneyScreen() {
        println("                           ");
        println("You don't have enough chips");
        println("                           ");
        println(" 00: Return and try again  ");
        println("                           ");

    }

}
