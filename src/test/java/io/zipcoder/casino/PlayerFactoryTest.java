package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerFactoryTest {

    @Test
    public void createPlayerNameTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        String actual = gerg.getPlayerName();

        //Then
        Assert.assertEquals(name, actual);
    }

    @Test
    public void createPlayerWalletTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int actual = gerg.getWallet();

        //Then
        Assert.assertEquals(walletsize, actual);
    }

    @Test
    public void createPlayerIDTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int actual = gerg.getPlayerID();

        //Then:
        Assert.assertTrue(actual <= 9999 && actual >= 1000);
    }

    @Test
    public void setWalletTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int expected = 69420;
        gerg.setWallet(expected);
        int actual = gerg.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getChipBalanceTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int expected = 0;
        int actual = gerg.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setChipBalanceTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int expected = 2570;
        gerg.setChipBalance(expected);
        int actual = gerg.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addToWalletTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int amountToAdd = 1500;
        int expected = 1920;
        gerg.addToWallet(amountToAdd);
        int actual = gerg.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void wageMoneyTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;
        int betAmount = 500;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        gerg.setChipBalance(2500);
        int expected = 2000;
        gerg.wageMoney(betAmount);
        int actual = gerg.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void cashOutTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;


        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        gerg.setChipBalance(2500);
        int expected = 2920;
        gerg.cashOut();
        int actual = gerg.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        int expected = 420;
        gerg.getMoreChips(expected);
        int actual = gerg.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsWithBalanceTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;
        int chipsWanted = 320;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        gerg.setChipBalance(600);
        int expected = 920;
        gerg.getMoreChips(chipsWanted);
        int actual = gerg.getChipBalance();

        //Then:
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMoreChipsRemainingWalletTest() {
        //Given:
        String name = "GergDon";
        int walletsize = 420;
        int chipsWanted = 320;

        //When:
        Player gerg = PlayerFactory.createPlayer(name, walletsize);
        gerg.setChipBalance(600);
        int expected = 100;
        gerg.getMoreChips(chipsWanted);
        int actual = gerg.getWallet();

        //Then:
        Assert.assertEquals(expected, actual);
    }
}