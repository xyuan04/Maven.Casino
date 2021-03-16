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


        System.out.println("Welcome To BlackJack!");

        while(gameOn) {
            Integer input = console.getIntegerInput("Up for a game? 1 - Yes or 2 - No");

            boolean validInput = true;
            while(validInput) {
                switch (input) {
                    case 1:
                        Integer bet = console.getIntegerInput("What is your bet?");
                        boolean invalidBet = bet > player.getChipBalance();
                        //check for Player chip balance
                        if(invalidBet) {
                            System.out.println("Balance not enough, please lower your bet or enter 0 to cancel");
                            break;
                        } else if (bet == 0) {
                            blackJack();
                            validInput = false;
                            break;
                        }
                        bj.addToPot(bet);
                        validInput = false;
                        break;
                    case 2:
                        System.out.println("Good bye!");
                        gameOn = false;
                        break;
                    default:
                        input = console.getIntegerInput("Not a valid input. Please choose 1 or 2");
                        break;
                }
            }

            bj.playGame();


            currentHands(bj);
            boolean round = true;
            while(round)
                input = console.getIntegerInput("\nWhat would you like to do?\n1 - Hit\t2 - Hold\t3 - Split");
                switch (input) {
                    case 1:
                        bj.hitMe();
                        currentHands(bj);
                        break;
                    case 2:
                        bj.hold();
                        currentHands(bj);
                        break;
                    case 3:
                        bj.playerHandSplit();
                        currentHands(bj);
                        round = false;
//                        if(bj.playerHand.get(0).getCardName().equals(bj.playerHand.get(1))) {
//                            bj.playerHandSplit();
//                        } else input = console.getIntegerInput("Not a pair, cannot split. Please select a valid action");
                        break;
                    default:
                        System.out.println("Not a valid input");
                        break;
                }



        }

    }

    public void currentHands(BlackJack blackJack) {
        System.out.println(String.format("Dealer current hand: %s\n",blackJack.dealerTotal));
        System.out.println(String.format("=== %s ===", blackJack.dealerHand));
        if(!blackJack.playerSplitHand.isEmpty()) {
            System.out.println(String.format("\nPlayer current split hand: %s\n", blackJack.playerSplitTotal));
            System.out.println(String.format("=== %s ===", blackJack.playerSplitHand));
        }
        System.out.println(String.format("\nPlayer current hand: %s\n", blackJack.playerTotal));
        System.out.println(String.format("=== %s ===", blackJack.playerHand));
    }
}

