package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    String name = "Gerg";
    int stash = 5000;

    @Test
    public void getPlayerNameTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        String expected = "Gerg";
        String actual = gamblingAddict.getPlayerName();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPlayerNameTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        String expected = "Don";
        gamblingAddict.setPlayerName(expected);
        String actual = gamblingAddict.getPlayerName();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPlayerIDTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int actual = gamblingAddict.getPlayerID();

        //Then:
        Assert.assertTrue(actual <= 9999 && actual >= 1000);
    }

    @Test
    public void getWalletTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int expected = 5000;
        int actual = gamblingAddict.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setWalletTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int expected = 69420;
        gamblingAddict.setWallet(expected);
        int actual = gamblingAddict.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getChipBalanceTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int expected = 0;
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setChipBalanceTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int expected = 2570;
        gamblingAddict.setChipBalance(expected);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addToWalletTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int amountToAdd = 1500;
        int expected = 6500;
        gamblingAddict.addToWallet(amountToAdd);
        int actual = gamblingAddict.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void wageMoneyTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(2500);
        int betAmount = 500;

        //When:
        int expected = 2000;
        gamblingAddict.wageMoney(betAmount);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void wageMoneyNegativeTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(2500);
        int betAmount = -500;

        //When:
        int expected = 2500;
        gamblingAddict.wageMoney(betAmount);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void cashOutTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(2500);

        //When:
        int expected = 7500;
        gamblingAddict.cashOut();
        int actual = gamblingAddict.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);

        //When:
        int expected = 4000;
        gamblingAddict.getMoreChips(expected);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsWithBalanceTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(5000);
        int chipsWanted = 3000;

        //When:
        int expected = 8000;
        gamblingAddict.getMoreChips(chipsWanted);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsRemainingWalletTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(5000);
        int chipsWanted = 3000;

        //When:
        int expected = 2000;
        gamblingAddict.getMoreChips(chipsWanted);
        int actual = gamblingAddict.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void winChipsTest() {
        //Given:
        Player gamblingAddict = new Player(name, stash);
        gamblingAddict.setChipBalance(5000);
        int chipsWon = 167;

        //When:
        int expected = 5167;
        gamblingAddict.winChips(chipsWon);
        int actual = gamblingAddict.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

}