package io.zipcoder.casino;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlackJackTest {
    Player gerg = PlayerFactory.createPlayer("gerg", 5000);
    BlackJack bj = new BlackJack(gerg);


    @Test
    public void addToPotTest() {
        //Given:
        gerg.setChipBalance(3000);

        //When:
        int expected = 500;
        bj.addToPot(expected);
        int actual = bj.sizeOfPot;

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void addToPotPlayerBalanceTest() {
        //Given:
        gerg.setChipBalance(3000);

        //When:
        bj.addToPot(500);
        int expected = 2500;
        int actual = gerg.getChipBalance();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playerWinPotTest() {
        //Given:
        gerg.setChipBalance(3000);

        //When:
        bj.addToPot(500);
        bj.playerWinPot();
        int expected = 3500;
        int actual = gerg.getChipBalance();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playerWinPotBalanceTest() {
        //Given:
        gerg.setChipBalance(3000);

        //When:
        bj.addToPot(500);
        bj.playerWinPot();
        int expected = 0;
        int actual = bj.sizeOfPot;

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playerBlackJackTest() {
        //Given:
        gerg.setChipBalance(3000);

        //When:
        bj.addToPot(500);
        bj.playerBlackJack();
        int expected = 4000;
        int actual = gerg.getChipBalance();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playGamePlayerHandTest() {
        //Given:
        bj.playGame();

        //When:
        int expected = 2;
        int actual = bj.playerHand.size();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playGameDealerHandTest() {
        //Given:
        bj.playGame();

        //When:
        int expected = 2;
        int actual = bj.dealerHand.size();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void checkWinner() {
    }

    @Test
    public void playerHandSplit() {
    }

    @Test
    public void dealerHandSplit() {
    }

    @Test
    public void playerBust() {
    }

    @Test
    public void dealerBust() {
    }
}