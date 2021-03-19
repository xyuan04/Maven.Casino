package io.zipcoder.casino;

public interface GamblingPlayer {

    void wageMoney(int wageAmount);
    void cashOut();
    void getMoreChips(int buyAmount);
    void winChips(int chipsWon);
}
