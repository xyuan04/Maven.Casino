package io.zipcoder.casino;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GoFishTest {


    @Test
    public void constructorGameTest(){
        GoFish friendlyGame = new GoFish();
        Assert.assertTrue(friendlyGame instanceof GoFish);
    }

    @Test
    public void playerHandTest(){
        GoFish friendlyGame=new GoFish();
        friendlyGame.makeDeck();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        Integer actual=friendlyGame.cardsInHand(oswald);
        Integer expected = 4;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void twoHandsTest(){
        GoFish friendlyGame=new GoFish();
        friendlyGame.makeDeck();
        ArrayList<Card> oswald= new ArrayList<>();
        ArrayList<Card> dealer= new ArrayList<>();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        Integer actual=friendlyGame.cardsInHand(dealer);
        Integer expected = 2;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testShowCard(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        String actual=friendlyGame.showCard(oswald, 3);
        String expected="4 of Spades";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testSeeHand(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        String actual=friendlyGame.seeHand(oswald);
        String expected="Your hand is:\nA of Spades\n2 of Spades\n3 of Spades\n4 of Spades\n";
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testGotAnyKings1(){
        GoFish friendlyGame=new GoFish();
        friendlyGame.makeDeck();
        ArrayList<Card> oswald= new ArrayList<>();
        ArrayList<Card> dealer= new ArrayList<>();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(dealer);
        Assert.assertTrue(friendlyGame.gotAnyKings(oswald, "A"));

    }

    @Test
    public void testTakeCards(){
        GoFish friendlyGame=new GoFish();
        friendlyGame.makeDeck();
        ArrayList<Card> oswald= new ArrayList<>();
        ArrayList<Card> dealer= new ArrayList<>();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);

        friendlyGame.takeCards(oswald,dealer,"2");

        Integer actual=friendlyGame.cardsInHand(dealer);
        Integer expected = 9;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testTakeCards2(){
        GoFish friendlyGame=new GoFish();
        friendlyGame.makeDeck();
        ArrayList<Card> oswald= new ArrayList<>();
        ArrayList<Card> dealer= new ArrayList<>();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);
        friendlyGame.dealCards(dealer);

        friendlyGame.takeCards(oswald,dealer,"4");

        Integer actual=friendlyGame.cardsInHand(oswald);
        Integer expected = 11;
        Assert.assertEquals(actual,expected);
    }

    @Test
    public void testDrawAnyKings(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        Assert.assertTrue(friendlyGame.drawAnyKings(oswald, "A"));
    }

    @Test
    public void testDrawAnyKings2(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        Assert.assertFalse(friendlyGame.drawAnyKings(oswald, "A"));
    }

    @Test
    public void testDrawAnyKings3(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        Assert.assertTrue(friendlyGame.drawAnyKings(oswald, "2"));
    }

    @Test
    public void testBookClose(){
        GoFish friendlyGame=new GoFish();
        ArrayList<Card> oswald= new ArrayList<>();
        friendlyGame.makeDeck();
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //A
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //2
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //3
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //4
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //5
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //6
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //7
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //8
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //9
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //10
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //J
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //Q
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        friendlyGame.dealCards(oswald);
        //K

        Assert.assertTrue(friendlyGame.completeSet(oswald, "K"));
    }
}
