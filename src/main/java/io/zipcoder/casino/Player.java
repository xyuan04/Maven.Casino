package io.zipcoder.casino;

public class Player implements GamblingPlayer{
    private String playerName;
    private final int playerID;
    private int wallet;
    private int chipBalance;

    public Player(String playerName, int wallet) {
        this.playerName = playerName;
        this.playerID = (int)(Math.random() * ((9999 - 1000) + 1) + 1000);
        this.wallet = wallet;
        this.chipBalance = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getChipBalance() {
        return chipBalance;
    }

    public void setChipBalance(int chipBalance) {
        this.chipBalance = chipBalance;
    }

    public void addToWallet(int newMoney) {
        if(newMoney >= 0) {
            wallet += newMoney;
        }
    }

    public void wageMoney(int wageAmount) {
        if(wageAmount >= 0) {
            chipBalance -= wageAmount;
        }
    }

    public void cashOut() {
        wallet += chipBalance;
        chipBalance = 0;
    }

    public void getMoreChips(int buyAmount) {
        if(buyAmount >= 0) {
            wallet -= buyAmount;
            chipBalance += buyAmount;
        }
    }

    public void winChips(int chipsWon) {
        chipBalance += chipsWon;
    }

    public void bet(int amount) {
        chipBalance -= amount;
    }
}
