package io.zipcoder.casino;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {

    @Test
    public void constructorTest(){
        Card joker= new Card("3", "Hearts", 3);
        Assert.assertTrue(joker instanceof Card);
    }

    @Test
    public void getCardNameTest(){
        Card joker= new Card("3", "Hearts", 3);
        String expected = "3";
        String actual = joker.getCardName();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getCardSuitTest(){
        Card joker= new Card("3", "Hearts", 3);
        String expected = "Hearts";
        String actual = joker.getCardSuit();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getCardValueTest(){
        Card joker= new Card("3", "Hearts", 3);
        Integer expected = 3;
        Integer actual = joker.getValue();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setCardValueTest(){
        Card joker= new Card("3", "Hearts", 3);
        Integer expected = 7;
        joker.setValue(7);
        Integer actual = joker.getValue();
        Assert.assertEquals(expected,actual);
    }
}
