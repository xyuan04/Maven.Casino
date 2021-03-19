package io.zipcoder.casino;

import io.zipcoder.casino.utilities.Console;

public class BlackJackEngine {
    Player player;
    Casino casino = new Casino();

    public BlackJackEngine(Player player) {
        this.player = player;
    }

    public void blackJack() {
        Console console = new Console(System.in, System.out);
        BlackJack bj = new BlackJack(player);

        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To BlackJack!");

        boolean validInput = true;
        while(validInput) {
            Integer input = console.getIntegerInput("Play? 1 - Yes or 2 - No");
            switch (input) {
                case 1:
                    currentChipCount(player);
                    Integer bet = console.getIntegerInput("What is your bet?");
                    if(bet > player.getChipBalance()) {
                        System.out.println("Balance not enough, please lower your bet");
                        continue;
                    } else if (bet < 0) {
                        System.out.println("Please enter a positive amount");
                        continue;
                    }
                    bj.addToPot(bet);
                    bj.playGame();
                    roundStart(bj, player, console);
                    break;
                case 2:
                    System.out.println("Good bye!");
                    casino.casinoLobbyScreen(player);
                    validInput = false;
                    break;
                default:
                    System.out.println("Not a valid input. Please choose 1 or 2");
                    break;
            }
        }
    }

    public void roundStart(BlackJack blackJack, Player player, Console console) {
        System.out.println(String.format("Hello %s", player.getPlayerName()));

        boolean round = true;
        while(round) {
            playerTurnHands(blackJack);
            currentChipCount(player);
            currentPot(blackJack);
            currentTurnIndicator(blackJack);
            if(blackJack.playerHaveBlackJack() && blackJack.dealerHaveBlackJack() || blackJack.dealerHaveBlackJack() && blackJack.playerHaveBlackJack()) {
                currentHands(blackJack);
                System.out.println(String.format("Both BLACKJACK! You won %s chips.", (blackJack.sizeOfPot)));
                blackJack.tiedPot();
                resetAceValue(blackJack);
                resetHandAndValues(blackJack);
                blackJack.clearDiscardAndDeck();
                break;
            }
            if(blackJack.playerHaveBlackJack()) {
                currentHands(blackJack);
                System.out.println(String.format("BLACKJACK! You won %s chips.", (blackJack.sizeOfPot*3)));
                blackJack.playerWinByBlackJack();
                resetAceValue(blackJack);
                resetHandAndValues(blackJack);
                blackJack.clearDiscardAndDeck();
                break;
            }
            if(blackJack.dealerHaveBlackJack()) {
                currentHands(blackJack);
                System.out.println("Dealer BLACKJACK! Sorry, better luck next time!");
                blackJack.playerLosePot();
                resetAceValue(blackJack);
                resetHandAndValues(blackJack);
                blackJack.clearDiscardAndDeck();
                break;
            }
            Integer decision = console.getIntegerInput("What would you like to do?\n1 - Hit\t\t2 - Hold\t\t3 - Double Down\t\t4 - Split");
            switch (decision) {
                case 1:
                    blackJack.hitMe();
                    playerChangeAce(blackJack);
                    if(blackJack.playerBust()) {
                        currentHands(blackJack);
                        System.out.println("BUST! Sorry, better luck next time!");
                        blackJack.playerLosePot();
                        resetAceValue(blackJack);
                        resetHandAndValues(blackJack);
                        blackJack.clearDiscardAndDeck();
                        round = false;
                    }
                    if(blackJack.dealerBust()) {
                        currentHands(blackJack);
                        System.out.println(String.format("Congrats! Dealer BUST! You won %s chips.", (blackJack.sizeOfPot*2)));
                        blackJack.playerWinPot();
                        resetAceValue(blackJack);
                        resetHandAndValues(blackJack);
                        blackJack.clearDiscardAndDeck();
                        round = false;
                    }
                    break;
                case 2:
                    blackJack.hold();
                    while (blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal < 16) {
                        blackJack.hitMe();
                        dealerChangeAce(blackJack);
                        System.out.println(String.format("Dealer hits and gets a %s", blackJack.dealerHand.get(blackJack.dealerHand.size() - 1)));
                        currentHands(blackJack);
                    }
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        if(blackJack.checkWinner().equals("Player")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot*2)));
                            blackJack.playerWinPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Dealer")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println("Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Tie")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("TIED! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.tiedPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        }
                    }
                    break;
                case 3:
                    if(blackJack.playerHand.size() == 2 && (blackJack.playerTotal == 9 || blackJack.playerTotal == 10 || blackJack.playerTotal == 11)) {
                        blackJack.addToPot(blackJack.sizeOfPot);
                        blackJack.hitMe();
                        playerChangeAce(blackJack);
                        if (blackJack.playerBust()) {
                            currentHands(blackJack);
                            System.out.println("BUST! Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        }
                        blackJack.hold();
                    } else System.out.println("Can only double down if starting hand equals 9, 10, or 11.");
                    while (blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal < 16) {
                        blackJack.hitMe();
                        dealerChangeAce(blackJack);
                        System.out.println(String.format("Dealer hits and gets a %s", blackJack.dealerHand.get(blackJack.dealerHand.size() - 1)));
                        currentHands(blackJack);
                    }
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        if(blackJack.checkWinner().equals("Player")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot*2)));
                            blackJack.playerWinPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Dealer")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println("Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Tie")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("TIED! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.tiedPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        }
                    }
                    break;
                case 4:
                    if(blackJack.playerHand.get(0).getCardName().equals(blackJack.playerHand.get(1).getCardName())) {
                        splitHandStart(blackJack, player, console);
                        round = false;
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

    public void splitHandStart(BlackJack blackJack, Player player, Console console) {
        blackJack.playerHandSplit();

        boolean round = true;
        while(round) {
            playerTurnHands(blackJack);
            currentChipCount(player);
            currentTurnIndicator(blackJack);
            if(blackJack.playerHaveBlackJack()) {
                System.out.println(String.format("BLACKJACK! You won %s chips.", (blackJack.sizeOfPot*3)));
                blackJack.playerWinByBlackJack();
                resetAceValue(blackJack);
                resetHandAndValues(blackJack);
                blackJack.clearDiscardAndDeck();
                break;
            }
            if(blackJack.playerSplitHandHaveBlackJack()) {
                System.out.println(String.format("BLACKJACK! You won %s chips.", (blackJack.sizeOfPot*3)));
                blackJack.playerWinByBlackJack();
                resetAceValue(blackJack);
                resetHandAndValues(blackJack);
                blackJack.clearDiscardAndDeck();
                break;
            }
            Integer decision = console.getIntegerInput("What would you like to do?\n1 - Hit\t\t2 - Hold\t\t3 - Double Down");
            switch (decision) {
                case 1:
                    blackJack.hitMe();
                    playerChangeAce(blackJack);
                    if(blackJack.currentHand == blackJack.playerSplitHand) {
                        playerSplitHandChangeAce(blackJack);
                    }
                    if(blackJack.playerBust()) {
                        currentHands(blackJack);
                        System.out.println("BUST! Next hand.");
                        blackJack.splitHold();
                    }
                    if(blackJack.playerSplitHandBust()) {
                        currentHands(blackJack);
                        System.out.println("BUST! Sorry, better luck next time!");
                        if(blackJack.checkWinner().equals("Player") || blackJack.checkSplitWinner().equals("Player")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot * 2)));
                            blackJack.playerWinPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                        } else if (blackJack.checkWinner().equals("Dealer") && blackJack.checkSplitWinner().equals("Dealer")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println("Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                        } else if (blackJack.checkWinner().equals("Tie") || blackJack.checkSplitWinner().equals("Tie")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("TIED! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.tiedPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                        }
                        round = false;
                    }
                    if(blackJack.dealerBust()) {
                        currentHands(blackJack);
                        System.out.println(String.format("Congrats! Dealer BUST! You won %s chips.", (blackJack.sizeOfPot*2)));
                        blackJack.playerWinPot();
                        resetAceValue(blackJack);
                        resetHandAndValues(blackJack);
                        blackJack.clearDiscardAndDeck();
                        round = false;
                    }
                    break;
                case 2:
                    if(blackJack.currentHand == blackJack.playerHand) {
                        blackJack.splitHold();
                        break;
                    }
                    if(blackJack.currentHand == blackJack.playerSplitHand) {
                        blackJack.hold();
                    }
                    blackJack.hold();
                    while (blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal < 16) {
                        blackJack.hitMe();
                        dealerChangeAce(blackJack);
                        System.out.println(String.format("Dealer hits and gets a %s", blackJack.dealerHand.get(blackJack.dealerHand.size() - 1)));
                        currentHands(blackJack);
                    }
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        if(blackJack.checkWinner().equals("Player") || blackJack.checkSplitWinner().equals("Player")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot*2)));
                            blackJack.playerWinPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Dealer") && blackJack.checkSplitWinner().equals("Dealer")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println("Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Tie") || blackJack.checkSplitWinner().equals("Tie")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("TIED! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.tiedPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        }
                    }
                    break;
                case 3:
                    blackJack.addToPot(blackJack.sizeOfPot);
                    blackJack.hitMe();
                    playerChangeAce(blackJack);
                    if(blackJack.currentHand == blackJack.playerSplitHand) {
                        playerSplitHandChangeAce(blackJack);
                    }
                    if(blackJack.playerBust()) {
                        currentHands(blackJack);
                        System.out.println("BUST! Next hand.");
                        blackJack.splitHold();
                    }
                    if(blackJack.playerSplitHandBust()) {
                        currentHands(blackJack);
                        System.out.println("BUST! Sorry, better luck next time!");
                        blackJack.playerLosePot();
                        resetAceValue(blackJack);
                        resetHandAndValues(blackJack);
                        blackJack.clearDiscardAndDeck();
                        round = false;
                    }
                    if(blackJack.dealerBust()) {
                        currentHands(blackJack);
                        System.out.println(String.format("Congrats! Dealer BUST! You won %s chips.", (blackJack.sizeOfPot*2)));
                        blackJack.playerWinPot();
                        resetAceValue(blackJack);
                        resetHandAndValues(blackJack);
                        blackJack.clearDiscardAndDeck();
                        round = false;
                    }
                    blackJack.hold();
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        if(blackJack.checkWinner().equals("Player")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("Congrats! You won %s chips.", (blackJack.sizeOfPot*2)));
                            blackJack.playerWinPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Dealer")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println("Sorry, better luck next time!");
                            blackJack.playerLosePot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        } else if (blackJack.checkWinner().equals("Tie")) {
                            System.out.println("\n\t\t\t\t\t**********FINAL HANDS**********\t\t\t\t\t\n");
                            currentHands(blackJack);
                            System.out.println(String.format("TIED! You won %s chips.", blackJack.sizeOfPot));
                            blackJack.tiedPot();
                            resetAceValue(blackJack);
                            resetHandAndValues(blackJack);
                            blackJack.clearDiscardAndDeck();
                            round = false;
                        }
                    }
                default:
                    System.out.println("Not a valid input");
                    break;
            }
        }
    }

    public void playerTurnHands(BlackJack blackJack) {
        System.out.println("Dealer current hand:");
        System.out.println(String.format("\t\t\t\t\t=== %s + HIDDEN CARD ===", blackJack.dealerHand.get(0)));
        if(!blackJack.playerSplitHand.isEmpty()) {
            System.out.println(String.format("\nPlayer current split hand: %s\n", blackJack.playerSplitTotal));
            System.out.println(String.format("\t\t\t\t\t=== %s ===", blackJack.playerSplitHand));
        }
        System.out.println(String.format("\nPlayer current hand: %s\n", blackJack.playerTotal));
        System.out.println(String.format("\t\t\t\t\t=== %s ===\n", blackJack.playerHand));
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

    public void playerChangeAce(BlackJack blackJack) {
        int currentValue = 0;
        if (blackJack.playerBust()) {
            for (int i = 0; i < blackJack.playerHand.size(); i++) {
                if (blackJack.playerHand.get(i).getCardName().equals("A")) {
                    blackJack.playerHand.get(i).setValue(1);
                }
            }
            for(int i = 0; i < blackJack.playerHand.size(); i++) {
                currentValue += blackJack.playerHand.get(i).getValue();
            }
            blackJack.playerTotal = currentValue;
        }
    }

    public void playerSplitHandChangeAce(BlackJack blackJack) {
        int currentValue = 0;
        if (blackJack.playerBust()) {
            for (int i = 0; i < blackJack.playerSplitHand.size(); i++) {
                if (blackJack.playerSplitHand.get(i).getCardName().equals("A")) {
                    blackJack.playerSplitHand.get(i).setValue(1);
                }
            }
            for(int i = 0; i < blackJack.playerSplitHand.size(); i++) {
                currentValue += blackJack.playerSplitHand.get(i).getValue();
            }
            blackJack.playerSplitTotal = currentValue;
        }
    }

    public void dealerChangeAce(BlackJack blackJack) {
        int currentValue = 0;
        if (blackJack.dealerBust()) {
            for (int i = 0; i < blackJack.dealerHand.size(); i++) {
                if (blackJack.dealerHand.get(i).getCardName().equals("A")) {
                    blackJack.dealerHand.get(i).setValue(1);
                }
            }
            for(int i = 0; i < blackJack.dealerHand.size(); i++) {
                currentValue += blackJack.dealerHand.get(i).getValue();
            }
            blackJack.dealerTotal = currentValue;
        }
    }

    public void resetAceValue(BlackJack blackJack) {
        blackJack.AceOfClubs.setValue(11);
        blackJack.AceOfDiamonds.setValue(11);
        blackJack.AceOfHearts.setValue(11);
        blackJack.AceOfSpades.setValue(11);
    }

    public void currentChipCount(Player player) {
        System.out.println(String.format("%s : Your current chip count is %7d\n", player.getPlayerName(), player.getChipBalance()));
    }

    public void currentPot(BlackJack blackJack) {
        System.out.println(String.format("The current bet is %s\n", blackJack.sizeOfPot));
    }

    public void resetHandAndValues(BlackJack blackJack) {
        blackJack.discardHand(blackJack.playerHand);
        blackJack.discardHand(blackJack.playerSplitHand);
        blackJack.discardHand(blackJack.dealerHand);
        blackJack.playerTotal = 0;
        blackJack.playerSplitTotal = 0;
        blackJack.dealerTotal = 0;
    }

    public void currentTurnIndicator(BlackJack blackJack) {
        if(blackJack.currentHand == blackJack.playerHand) {
            System.out.println(String.format("Turn to act : *** %s ***", blackJack.currentPlayer.getPlayerName()));
        } else if(blackJack.currentHand == blackJack.playerSplitHand) {
            System.out.println(String.format("Turn to act : *** %s Split Hand***", blackJack.currentPlayer.getPlayerName()));
        }else if(blackJack.currentHand == blackJack.dealerHand) {
            System.out.println("Turn to act : *** Dealer ***");
        }
    }
}

