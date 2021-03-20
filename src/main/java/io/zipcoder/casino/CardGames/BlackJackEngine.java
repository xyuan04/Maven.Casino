package io.zipcoder.casino.CardGames;

import io.zipcoder.casino.Player.Player;
import io.zipcoder.casino.Screens.BlackJackScreens;
import io.zipcoder.casino.utilities.Console;

public class BlackJackEngine {
    Player player;

    public BlackJackEngine(Player player) {
        this.player = player;
    }

    public void blackJack() {
        Console console = new Console(System.in, System.out);
        BlackJack blackJack = new BlackJack(player);
        BlackJackScreens screens = new BlackJackScreens(blackJack, player);

        System.out.println(String.format("Hello %s", player.getPlayerName()));
        System.out.println("Welcome To BlackJack!");

        boolean validInput = true;
        while(validInput) {
            Integer input = console.getIntegerInput("Play? 1 - Yes or 2 - No");
            switch (input) {
                case 1:
                    startGame(blackJack, player, console, screens);
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

    public void startGame(BlackJack blackJack, Player player, Console console, BlackJackScreens screens) {
        screens.currentChipCount(player);
        boolean decision = true;
        while (decision) {
            Integer bet = console.getIntegerInput("What is your bet?");
            if (bet > player.getChipBalance()) {
                System.out.println("Balance not enough, please lower your bet");
                continue;
            } else if (bet < 0) {
                System.out.println("Please enter a positive amount");
                continue;
            }
            blackJack.addToPot(bet);
            blackJack.playGame();
            roundStart(blackJack, player, console, screens);
            decision = false;
        }
    }

    public void roundStart(BlackJack blackJack, Player player, Console console, BlackJackScreens screens) {
        System.out.println(String.format("Hello %s", player.getPlayerName()));

        boolean round = true;
        while(round) {
            roundStartOperations(blackJack, player, screens);
            if(blackJack.playerTotal == 21 && blackJack.playerHand.size() == 2 || blackJack.playerSplitTotal == 21 &&  blackJack.playerSplitHand.size() == 2 || blackJack.dealerTotal == 21 && blackJack.dealerHand.size() == 2) {
                screens.currentHands(blackJack);
                blackJackWinner(blackJack, blackJack.blackJackCheck());
                break;
            }
            Integer decision = console.getIntegerInput("What would you like to do?\n1 - Hit\t\t2 - Hold\t\t3 - Double Down\t\t4 - Split");
            switch (decision) {
                case 1:
                    blackJack.hitMe();
                    playerChangeAce(blackJack);
                    round = bustCheck(blackJack, screens);
                    break;
                case 2:
                    blackJack.hold();
                    while (blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal < 16) {
                        dealerTurn(blackJack, screens);
                    }
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        String winner = blackJack.checkWinner();
                        checkWinner(blackJack, screens, winner);
                        round = false;
                    }
                    break;
                case 3:
                    if(blackJack.currentHand == blackJack.playerHand && blackJack.playerHand.size() == 2 && (blackJack.playerTotal == 9 || blackJack.playerTotal == 10 || blackJack.playerTotal == 11)
                            || blackJack.currentHand == blackJack.playerSplitHand && blackJack.playerSplitHand.size() == 2 && blackJack.playerSplitTotal == 9 || blackJack.playerSplitTotal == 10 || blackJack.playerSplitTotal == 11) {
                        blackJack.addToPot(blackJack.sizeOfPot);
                        blackJack.hitMe();
                        playerChangeAce(blackJack);
                        round = bustCheck(blackJack, screens);
                        blackJack.hold();
                    } else System.out.println("Can only double down if starting hand equals 9, 10, or 11.");
                    while (blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal < 16) {
                        dealerTurn(blackJack, screens);
                    }
                    if(blackJack.currentHand == blackJack.dealerHand && blackJack.dealerTotal >= 16) {
                        String winner = blackJack.checkWinner();
                        checkWinner(blackJack, screens, winner);
                        round = false;
                    }
                    break;
                case 4:
                    if(blackJack.playerHand.get(0).getCardName().equals(blackJack.playerHand.get(1).getCardName()) && blackJack.playerSplitHand.isEmpty()) {
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

    public void dealerTurn(BlackJack blackJack, BlackJackScreens screens) {
        blackJack.hitMe();
        dealerChangeAce(blackJack);
        System.out.println(String.format("Dealer hits and gets a %s", blackJack.dealerHand.get(blackJack.dealerHand.size() - 1)));
        screens.currentHands(blackJack);
    }

    public void blackJackWinner(BlackJack blackJack, String winner) {
        if (winner.equals("Player")) {
            System.out.println(String.format("BLACKJACK! You won %s chips.", (blackJack.sizeOfPot*3)));
            getPot(blackJack, 3);
        } else if (winner.equals("Tie")) {
            System.out.println(String.format("Both BLACKJACK! You get %s chips back.", (blackJack.sizeOfPot)));
            getPot(blackJack, 1);
        } else if (winner.equals("Dealer")) {
            System.out.println("Dealer BLACKJACK! Sorry, better luck next time!");
            getPot(blackJack, 0);
        }
    }

    public boolean bustCheck(BlackJack blackJack, BlackJackScreens screens) {
        if(blackJack.playerBust() && blackJack.playerSplitHand.isEmpty()) {
            screens.currentHands(blackJack);
            System.out.println("BUST! Sorry, better luck next time!");
            getPot(blackJack, 0);
            return false;
        } else if (blackJack.playerBust() && !blackJack.playerSplitHand.isEmpty()) {
            System.out.println("BUST! On to the next hand.");
            blackJack.hold();
            return true;
        } else if (blackJack.playerSplitHandBust()) {
            String winner = blackJack.checkWinner();
            checkWinner(blackJack, screens, winner);
            return false;
        } return true;
    }

    public void checkWinner(BlackJack blackJack, BlackJackScreens screens, String winner) {
        switch (winner) {
            case "Player":
                screens.finalHandWin(blackJack);
                getPot(blackJack, 2);
                break;
            case "Dealer":
                screens.finalHandLose(blackJack);
                getPot(blackJack, 0);
                break;
            case "Tie":
                screens.finalHandTie(blackJack);
                getPot(blackJack, 1);
                break;
        }
    }

    public void getPot(BlackJack blackJack, int multiplier) {
        blackJack.passPot(multiplier);
        resetValues(blackJack);
    }

    public void resetValues(BlackJack blackJack) {
        resetHandAndValues(blackJack);
        blackJack.clearDiscardAndDeck();
    }

    public void playerChangeAce(BlackJack blackJack) {
        int currentValue = 0;
        int splitCurrentValue = 0;

        if (blackJack.playerBust()) {
            for (int i = 0; i < blackJack.playerHand.size(); i++) {
                if (blackJack.playerHand.get(i).getCardName().equals("A")) {
                    blackJack.playerHand.get(i).setValue(1);
                }
            }
            for (int i = 0; i < blackJack.playerHand.size(); i++) {
                currentValue += blackJack.playerHand.get(i).getValue();
            }
            blackJack.playerTotal = currentValue;
        }

        if (blackJack.playerSplitHandBust()) {
            for (int i = 0; i < blackJack.playerSplitHand.size(); i++) {
                if (blackJack.playerSplitHand.get(i).getCardName().equals("A")) {
                    blackJack.playerSplitHand.get(i).setValue(1);
                }
            }
            for (int i = 0; i < blackJack.playerSplitHand.size(); i++) {
                splitCurrentValue += blackJack.playerSplitHand.get(i).getValue();
            }
            blackJack.playerSplitTotal = splitCurrentValue;
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

    public void resetHandAndValues(BlackJack blackJack) {
        blackJack.discardHand(blackJack.playerHand);
        blackJack.discardHand(blackJack.playerSplitHand);
        blackJack.discardHand(blackJack.dealerHand);
        blackJack.playerTotal = 0;
        blackJack.playerSplitTotal = 0;
        blackJack.dealerTotal = 0;
        blackJack.AceOfClubs.setValue(11);
        blackJack.AceOfDiamonds.setValue(11);
        blackJack.AceOfHearts.setValue(11);
        blackJack.AceOfSpades.setValue(11);
    }

    public void roundStartOperations(BlackJack blackJack, Player player, BlackJackScreens screens) {
        screens.playerTurnHands(blackJack);
        screens.currentChipCount(player);
        screens.currentPot(blackJack);
        screens.currentTurnIndicator(blackJack);
    }
}
