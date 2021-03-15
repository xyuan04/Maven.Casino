package io.zipcoder.casino;

import java.util.HashMap;
import java.util.Map;

public class PlayerWarehouse {
    private static Map<Integer, Player> currentPlayers = new HashMap<>();

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
