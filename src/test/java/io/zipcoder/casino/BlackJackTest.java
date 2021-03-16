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
    public void playGamePlayerHandValueTest() {
        //Given:
        bj.playGame();

        //When:
        int actual = bj.playerTotal;

        //Then:
        assertTrue(actual > 0);
    }

    @Test
    public void playGameDealerHandValueTest() {
        //Given:
        bj.playGame();

        //When:
        int actual = bj.dealerTotal;

        //Then:
        assertTrue(actual > 0);
    }

    @Test
    public void checkWinnerPlayerWinTest() {
        //Given:
        bj.playerTotal = 18;
        bj.dealerTotal = 17;

        //When:
        boolean expected = true;
        boolean actual = bj.checkWinner();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void checkWinnerPlayerBustTest() {
        //Given:
        bj.playerTotal = 25;
        bj.dealerTotal = 17;

        //When:
        boolean expected = false;
        boolean actual = bj.checkWinner();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void checkWinnerDealerWinsTest() {
        //Given:
        bj.playerTotal = 19;
        bj.dealerTotal = 20;

        //When:
        boolean expected = false;
        boolean actual = bj.checkWinner();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void checkWinnerDealerBustTest() {
        //Given:
        bj.playerTotal = 16;
        bj.dealerTotal = 23;

        //When:
        boolean expected = true;
        boolean actual = bj.checkWinner();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void checkWinnerPlayerBlackJackTest() {
        //Given:
        bj.playerTotal = 21;
        bj.dealerTotal = 21;

        //When:
        Boolean expected = null;
        Boolean actual = bj.checkWinner();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void hitMePlayerTest() {
        //Given:
        bj.playGame();

        //When:
        bj.hitMe();
        bj.hitMe();
        int expected = 4;
        int actual = bj.playerHand.size();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void hitMeDealerTest() {
        //Given:
        bj.playGame();

        //When:
        bj.hitMe();
        bj.hold();
        bj.hitMe();
        int expected = 3;
        int actual = bj.dealerHand.size();

        //Then:
        assertEquals(expected, actual);
    }

    @Test
    public void playerHandSplit() {
    }

    @Test
    public void dealerHandSplit() {
    }

}