package io.zipcoder.casino;

public class PlayerFactory {

    public static Player createPlayer(String name, int wallet) {
        return new Player(name, wallet);
    }
}
