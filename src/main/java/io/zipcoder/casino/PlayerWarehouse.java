package io.zipcoder.casino;

import java.util.HashMap;
import java.util.Map;

public class PlayerWarehouse {
    public static Map<Integer, Player> currentPlayers = new HashMap<>();

    public static void initializePlayers() {
        Player gerg = PlayerFactory.createPlayer("Greg", 5000);
        Player nixog = PlayerFactory.createPlayer("Xiong", 5000);
        Player eab = PlayerFactory.createPlayer("Abe", 5000);

        currentPlayers.put(100, nixog);
        currentPlayers.put(200, gerg);
        currentPlayers.put(300, eab);
    }

    public static void addPlayer(int playerID, Player player) {
        currentPlayers.put(playerID, player);
    }

    public static void removePlayer(int playerID) {
        currentPlayers.remove(playerID);
    }

    public static Integer getNumberOfCurrentPlayers() {
        return currentPlayers.size();
    }

    public static void removeAllPlayers() {
        currentPlayers.clear();
    }
}
