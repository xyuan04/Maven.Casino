package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerWarehouseTest {

    @Test
    public void addPlayer() {
        //Given:
        Player gerg = PlayerFactory.createPlayer("gerg", 500);
        int playerID = gerg.getPlayerID();

        //When:
        PlayerWarehouse.removeAllPlayers();
        PlayerWarehouse.addPlayer(playerID, gerg);

        //Then:
        Assert.assertEquals(1, (int) PlayerWarehouse.getNumberOfCurrentPlayers());
    }

    @Test
    public void removePlayer() {
        //Given:
        Player gerg = PlayerFactory.createPlayer("gerg", 500);
        Player eab = PlayerFactory.createPlayer("eab", 500);
        Player nixog = PlayerFactory.createPlayer("nixog", 500);
        int gergPlayerID = gerg.getPlayerID();
        int eabPlayerID = eab.getPlayerID();
        int nixogPlayerID = nixog.getPlayerID();
        PlayerWarehouse.removeAllPlayers();
        PlayerWarehouse.addPlayer(gergPlayerID, gerg);
        PlayerWarehouse.addPlayer(eabPlayerID, eab);
        PlayerWarehouse.addPlayer(nixogPlayerID, nixog);

        //When:
        PlayerWarehouse.removePlayer(gergPlayerID);

        //Then:
        Assert.assertEquals(2, (int) PlayerWarehouse.getNumberOfCurrentPlayers());
    }

    @Test
    public void getNumberOfCurrentPlayers() {
        //Given:
        Player gerg = PlayerFactory.createPlayer("gerg", 500);
        Player eab = PlayerFactory.createPlayer("eab", 500);
        Player nixog = PlayerFactory.createPlayer("nixog", 500);
        int gergPlayerID = gerg.getPlayerID();
        int eabPlayerID = eab.getPlayerID();
        int nixogPlayerID = nixog.getPlayerID();
        PlayerWarehouse.removeAllPlayers();
        PlayerWarehouse.addPlayer(gergPlayerID, gerg);
        PlayerWarehouse.addPlayer(eabPlayerID, eab);
        PlayerWarehouse.addPlayer(nixogPlayerID, nixog);

        //When:
        PlayerWarehouse.removePlayer(gergPlayerID);

        //Then:
        Assert.assertEquals(2, (int) PlayerWarehouse.getNumberOfCurrentPlayers());
    }

    @Test
    public void removeAllPlayers() {
        //Given:
        Player gerg = PlayerFactory.createPlayer("gerg", 500);
        Player eab = PlayerFactory.createPlayer("eab", 500);
        Player nixog = PlayerFactory.createPlayer("nixog", 500);
        int gergPlayerID = gerg.getPlayerID();
        int eabPlayerID = eab.getPlayerID();
        int nixogPlayerID = nixog.getPlayerID();
        PlayerWarehouse.addPlayer(gergPlayerID, gerg);
        PlayerWarehouse.addPlayer(eabPlayerID, eab);
        PlayerWarehouse.addPlayer(nixogPlayerID, nixog);

        //When:
        PlayerWarehouse.removeAllPlayers();

        //Then:
        Assert.assertEquals(0, (int) PlayerWarehouse.getNumberOfCurrentPlayers());
    }
}