package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MostOfAKindTests {
    Player gerg = PlayerFactory.createPlayer("gerg", 500);
    MostOfAKindGame yahtzee = new MostOfAKindGame(gerg);

    @Test
    public void getPot() {
        //Given
        int expected = 0;

        //When
        int actual = yahtzee.getPot();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addToPot() {
        //Given
        int expected = 5;

        //When
        yahtzee.addToPot(5);
        int actual = yahtzee.getPot();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void clearPot() {
        //Given
        int expected = 0;
        yahtzee.addToPot(7);
        yahtzee.clearPot();

        //When
        int actual = yahtzee.getPot();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void rollDice() {
        //Given

        //When
        int result = yahtzee.rollDice();

        //Then
        Assert.assertTrue(result > 0 && result < 7);
    }

    @Test
    public void addToPlayerCount() {
        //Given
        yahtzee.addToPlayerCount(3);
        int expected = 3;

        //When
        int actual = yahtzee.getPlayerCount();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkWinnerDealer() {
        //Given
        yahtzee.addToPlayerCount(3);
        yahtzee.addToDealerCount(4);

        //When
        boolean actual = yahtzee.checkWinner();

        //Then
        Assert.assertFalse(actual);
    }

    @Test
    public void checkWinnerPlayer() {
        //Given
        yahtzee.addToPlayerCount(4);
        yahtzee.addToDealerCount(2);

        //When
        boolean actual = yahtzee.checkWinner();

        //Then
        Assert.assertTrue(actual);
    }

    @Test
    public void extensiveCheckWinner() {
        ArrayList<Integer> dealerHand = new ArrayList<>();
        dealerHand.add(5);
        dealerHand.add(5);
        dealerHand.add(5);
        dealerHand.add(5);
        dealerHand.add(5);

        ArrayList<Integer> playerHand = new ArrayList<>();
        playerHand.add(6);
        playerHand.add(6);
        playerHand.add(6);
        playerHand.add(6);
        playerHand.add(6);

        //When
        yahtzee.exchangePlayerDice(6);
        yahtzee.exchangeDealerDice(5);
        yahtzee.playerNumOfMatches(playerHand);
        yahtzee.dealerNumOfMatches(dealerHand);

        boolean expected = true;
        boolean actual = yahtzee.checkWinner();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void makeDealerHand() {
        //Given

        //When
        yahtzee.makeDealerHand(5);
        int actual = yahtzee.getDealerHand().size();

        //Then
        Assert.assertEquals(5, actual);
    }

    @Test
    public void makePlayerHand() {
        //Given

        //When
        yahtzee.makePlayerHand(5);
        int actual = yahtzee.getPlayerHand().size();

        //Then
        Assert.assertEquals(5, actual);
    }
    
    @Test
    public void playerSecondRoll() {
        //Given
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(4);
        yahtzee.playerDiceHand.add(3);
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(6);
        ArrayList<Integer> old = yahtzee.getPlayerHand();

        //When
        yahtzee.exchangePlayerDice(5);
        ArrayList<Integer> newHand = yahtzee.getPlayerHand();

        //Then
        Assert.assertNotEquals(old, newHand);
    }

    @Test
    public void dealerSecondRoll() {
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(1);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(5);
        ArrayList<Integer> oldHand = yahtzee.getDealerHand();

        //When
        yahtzee.exchangeDealerDice(2);
        ArrayList<Integer> newHand = yahtzee.getDealerHand();

        //Then
        Assert.assertNotEquals(oldHand, newHand);
    }

    @Test
    public void playerSecondRollIrrational() {
        //Given
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(4);
        yahtzee.playerDiceHand.add(3);
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(6);
        ArrayList<Integer> old = yahtzee.getPlayerHand();

        //When
        yahtzee.exchangePlayerDice(3);
        ArrayList<Integer> newHand = yahtzee.getPlayerHand();

        //Then
        Assert.assertNotEquals(old, newHand);
    }

    @Test
    public void dealerSecondRollIrrational() {
        //Given
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(1);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(5);
        ArrayList<Integer> oldHand = yahtzee.getDealerHand();

        //When
        yahtzee.exchangeDealerDice(5);
        ArrayList<Integer> newHand = yahtzee.getDealerHand();

        //Then
        Assert.assertNotEquals(oldHand, newHand);
    }

    @Test
    public void setGetPlayerMatchingNum() {
        //Given
        yahtzee.setPlayerMatchingNum(5);
        int expected = 5;

        //When
        int actual = yahtzee.getPlayerMatchingNum();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setGetDealerMatchingNum() {
        //Given
        yahtzee.setDealerMatchingNum(2);
        int expected = 2;

        //When
        int actual = yahtzee.getDealerMatchingNum();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPlayerNumOfMatches() {
        //Given
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(5);
        yahtzee.playerDiceHand.add(3);
        yahtzee.playerDiceHand.add(6);
        int expected = 3;

        //When
        yahtzee.playerNumOfMatches(yahtzee.playerDiceHand);
        int actual = yahtzee.getPlayerCount();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getDealerNumOfMatches() {
        //Given
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(2);
        yahtzee.dealerHand.add(5);
        int expected = 4;

        //When
        yahtzee.dealerNumOfMatches(yahtzee.dealerHand);
        int actual = yahtzee.getDealerCount();

        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dealerAI() {
        //Given
        ArrayList<Integer> dealerHand = new ArrayList<>();
        dealerHand.add(5);
        dealerHand.add(2);
        dealerHand.add(5);
        dealerHand.add(1);
        dealerHand.add(5);

        //When
        yahtzee.dealerAINumbersToKeep(dealerHand);
        int expectedCount = 3;
        int expectedMatch = 5;
        int actualCount = yahtzee.getDealerCount();
        int actualMatch = yahtzee.getDealerMatchingNum();

        //Then
        Assert.assertEquals(expectedCount, actualCount);
        Assert.assertEquals(expectedMatch, actualMatch);
    }

    @Test
    public void dealerAITwo() {
        //Given
        ArrayList<Integer> dealerHand = new ArrayList<>();
        dealerHand.add(5);
        dealerHand.add(5);
        dealerHand.add(5);
        dealerHand.add(1);
        dealerHand.add(5);

        //When
        yahtzee.dealerAINumbersToKeep(dealerHand);
        int expectedCount = 4;
        int expectedMatch = 5;
        int actualCount = yahtzee.getDealerCount();
        int actualMatch = yahtzee.getDealerMatchingNum();

        //Then
        Assert.assertEquals(expectedCount, actualCount);
        Assert.assertEquals(expectedMatch, actualMatch);
    }
}
