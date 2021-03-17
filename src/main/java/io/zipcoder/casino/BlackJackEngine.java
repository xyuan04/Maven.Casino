package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;

public class BlackJackEngine {

    public static void main(String[] args) {
        BlackJackEngine bj = new BlackJackEngine();

        bj.blackJack();
    }

    public void blackJack() {
        boolean gameOn = true;
        Console console = new Console(System.in, System.out);
        PlayerWarehouse.initializePlayers();
        Player player = PlayerWarehouse.currentPlayers.get(100);
        BlackJack bj = new BlackJack(player);
        player.setChipBalance(5000);

        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To BlackJack!");

        while (gameOn) {
            mainBlackJackMenu(bj, player, console);
//            String choice = console.getStringInput("Would you like to play again? Yes/No");
//            while(true) {
//                if(choice.equalsIgnoreCase("yes")) {
//                    continue;
//                } else if (choice.equalsIgnoreCase("no")) {
//                    System.out.println("Good bye!");
//                    gameOn = false;
//                } else choice = console.getStringInput("Please choose yes or no");
//                break;
//            }

        }
    }

    public void mainBlackJackMenu(BlackJack bj, Player player, Console console) {

        boolean validInput = true;
        while(validInput) {
            Integer input = console.getIntegerInput("Play? 1 - Yes or 2 - No");
            switch (input) {
                case 1:
                    currentChipCount(player);
                    Integer bet = console.getIntegerInput("What is your bet?");
                    boolean invalidBet = bet > player.getChipBalance();
                    if(invalidBet) {
                        System.out.println("Balance not enough, please lower your bet or enter 0 to cancel");
                        break;
                    } else if (bet == 0) {
                        mainBlackJackMenu(bj, player, console);
                        validInput = false;
                        break;
                    }
                    bj.addToPot(bet);
                    bj.playGame();
                    roundStart(bj, player, console);
                    validInput = false;
                    break;
                case 2:
                    System.out.println("Good bye!");
                    validInput = false;
                    break;
                default:
                    System.out.println("Not a valid input. Please choose 1 or 2");
                    break;
            }
        }
    }



    public void currentHands(BlackJack blackJack) {
        System.out.println(String.format("Dealer current hand: %s\n",blackJack.dealerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.dealerHand));
        if(!blackJack.playerSplitHand.isEmpty()) {
            System.out.println(String.format("\nPlayer current split hand: %s\n", blackJack.playerSplitTotal));
            System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.playerSplitHand));
        }
        System.out.println(String.format("\nPlayer current hand: %s\n", blackJack.playerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===\n", blackJack.playerHand));
    }

    public void currentChipCount(Player player) {
        System.out.println(String.format("%s : Your current chip count is %7d", player.getPlayerName(), player.getChipBalance()));
    }


    public void roundStart(BlackJack blackJack, Player player, Console console) {
        System.out.println(String.format("Hello %s", player.getPlayerName()));
        boolean round = true;
        while(round) {
            currentHands(blackJack);
            currentChipCount(player);
            Integer decision = console.getIntegerInput("\nWhat would you like to do?\n1 - Hit\t2 - Hold\t3 - Split");
            switch (decision) {
                case 1:
                    blackJack.hitMe();
                    break;
                case 2:
                    blackJack.hold();
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        if(blackJack.checkWinner() == true) {
                            System.out.println(String.format("Congrats! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.playerWinPot();
                            resetHandAndValues(blackJack);
                            round = false;
                        } else if (blackJack.checkWinner() == false) {
                            System.out.println("Sorry better luck next time!");
                            blackJack.sizeOfPot = 0;
                            resetHandAndValues(blackJack);
                            round = false;
                        }
                    }
                    break;
                case 3:
                    if(blackJack.playerHand.get(0).getCardName().equals(blackJack.playerHand.get(1))) {
                        blackJack.playerHandSplit();
                    } else if (!blackJack.playerSplitHand.isEmpty()) {
                        System.out.println("Already split! Cannot split again!");
                    } else System.out.println("Not a pair, cannot split. Please select a valid action");
                    break;
                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }
    }

    public void resetHandAndValues(BlackJack blackJack) {
        blackJack.discardHand(blackJack.playerHand);
        blackJack.discardHand(blackJack.playerSplitHand);
        blackJack.discardHand(blackJack.dealerHand);
        blackJack.playerTotal = 0;
        blackJack.playerSplitTotal = 0;
        blackJack.dealerTotal = 0;
    }


}

