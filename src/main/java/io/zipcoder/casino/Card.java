package io.zipcoder.casino;

public class Card {
    //value from ace to king
    private String cardName;
    //Spade, Diamond, Heart, Clubs
    private String cardSuit;
    //value = name from 2-10. Jack queen and king are also ten, with ace being 1 or 11 in blackjack.
    private Integer cardValue;

    public Card(String name, String suit, Integer value){
        cardName=name;
        cardSuit=suit;
        cardValue=value;
    }

    public String getCardName(){
        return cardName;
    }

    public Integer getValue(){
        return cardValue;
    }

    public void setValue(int newValue) {
        cardValue = newValue;
    }

    public String getCardSuit(){
        return cardSuit;
    }


    @Override
    public String toString() {
        return String.format("%s of %s", cardName, cardSuit);
    }
}
