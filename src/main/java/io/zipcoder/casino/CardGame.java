package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class CardGame implements Game {
    private List<Card> deck=new ArrayList<Card>();
    private List<Card>discardPile=new ArrayList<Card>();


    public Card AceOfSpades=new Card("A", "Spades", 11);
    private Card TwoOfSpades=new Card("2", "Spades", 2);
    private Card ThreeOfSpades=new Card("3", "Spades", 3);
    private Card FourOfSpades=new Card("4", "Spades", 4);
    private Card FiveOfSpades=new Card("5", "Spades", 5);
    private Card SixOfSpades=new Card("6", "Spades", 6);
    private Card SevenOfSpades=new Card("7", "Spades", 7);
    private Card EightOfSpades=new Card("8", "Spades", 8);
    private Card NineOfSpades=new Card("9", "Spades", 9);
    private Card TenOfSpades=new Card("10", "Spades", 10);
    private Card JackOfSpades=new Card("J", "Spades", 10);
    private Card QueenOfSpades=new Card("Q", "Spades", 10);
    private Card KingOfSpades=new Card("K", "Spades", 10);

    public Card AceOfClubs=new Card("A", "Clubs", 11);
    private Card TwoOfClubs=new Card("2", "Clubs", 2);
    private Card ThreeOfClubs=new Card("3", "Clubs", 3);
    private Card FourOfClubs=new Card("4", "Clubs", 4);
    private Card FiveOfClubs=new Card("5", "Clubs", 5);
    private Card SixOfClubs=new Card("6", "Clubs", 6);
    private Card SevenOfClubs=new Card("7", "Clubs", 7);
    private Card EightOfClubs=new Card("8", "Clubs", 8);
    private Card NineOfClubs=new Card("9", "Clubs", 9);
    private Card TenOfClubs=new Card("10", "Clubs", 10);
    private Card JackOfClubs=new Card("J", "Clubs", 10);
    private Card QueenOfClubs=new Card("Q", "Clubs", 10);
    private Card KingOfClubs=new Card("K", "Clubs", 10);

    public Card AceOfDiamonds=new Card("A", "Diamonds", 11);
    private Card TwoOfDiamonds=new Card("2", "Diamonds", 2);
    private Card ThreeOfDiamonds=new Card("3", "Diamonds", 3);
    private Card FourOfDiamonds=new Card("4", "Diamonds", 4);
    private Card FiveOfDiamonds=new Card("5", "Diamonds", 5);
    private Card SixOfDiamonds=new Card("6", "Diamonds", 6);
    private Card SevenOfDiamonds=new Card("7", "Diamonds", 7);
    private Card EightOfDiamonds=new Card("8", "Diamonds", 8);
    private Card NineOfDiamonds=new Card("9", "Diamonds", 9);
    private Card TenOfDiamonds=new Card("10", "Diamonds", 10);
    private Card JackOfDiamonds=new Card("J", "Diamonds", 10);
    private Card QueenOfDiamonds=new Card("Q", "Diamonds", 10);
    private Card KingOfDiamonds=new Card("K", "Diamonds", 10);

    public Card AceOfHearts=new Card("A", "Hearts", 11);
    private Card TwoOfHearts=new Card("2", "Hearts", 2);
    private Card ThreeOfHearts=new Card("3", "Hearts", 3);
    private Card FourOfHearts=new Card("4", "Hearts", 4);
    private Card FiveOfHearts=new Card("5", "Hearts", 5);
    private Card SixOfHearts=new Card("6", "Hearts", 6);
    private Card SevenOfHearts=new Card("7", "Hearts", 7);
    private Card EightOfHearts=new Card("8", "Hearts", 8);
    private Card NineOfHearts=new Card("9", "Hearts", 9);
    private Card TenOfHearts=new Card("10", "Hearts", 10);
    private Card JackOfHearts=new Card("J", "Hearts", 10);
    private Card QueenOfHearts=new Card("Q", "Hearts", 10);
    private Card KingOfHearts=new Card("K", "Hearts", 10);





    //method to set deck to new deck.
    public void makeDeck(){
        deck.add(AceOfSpades);
        deck.add(TwoOfSpades);
        deck.add(ThreeOfSpades);
        deck.add(FourOfSpades);
        deck.add(FiveOfSpades);
        deck.add(SixOfSpades);
        deck.add(SevenOfSpades);
        deck.add(EightOfSpades);
        deck.add(NineOfSpades);
        deck.add(TenOfSpades);
        deck.add(JackOfSpades);
        deck.add(QueenOfSpades);
        deck.add(KingOfSpades);
        
        deck.add(AceOfClubs);
        deck.add(TwoOfClubs);
        deck.add(ThreeOfClubs);
        deck.add(FourOfClubs);
        deck.add(FiveOfClubs);
        deck.add(SixOfClubs);
        deck.add(SevenOfClubs);
        deck.add(EightOfClubs);
        deck.add(NineOfClubs);
        deck.add(TenOfClubs);
        deck.add(JackOfClubs);
        deck.add(QueenOfClubs);
        deck.add(KingOfClubs);
        
        deck.add(AceOfDiamonds);
        deck.add(TwoOfDiamonds);
        deck.add(ThreeOfDiamonds);
        deck.add(FourOfDiamonds);
        deck.add(FiveOfDiamonds);
        deck.add(SixOfDiamonds);
        deck.add(SevenOfDiamonds);
        deck.add(EightOfDiamonds);
        deck.add(NineOfDiamonds);
        deck.add(TenOfDiamonds);
        deck.add(JackOfDiamonds);
        deck.add(QueenOfDiamonds);
        deck.add(KingOfDiamonds);
        
        deck.add(AceOfHearts);
        deck.add(TwoOfHearts);
        deck.add(ThreeOfHearts);
        deck.add(FourOfHearts);
        deck.add(FiveOfHearts);
        deck.add(SixOfHearts);
        deck.add(SevenOfHearts);
        deck.add(EightOfHearts);
        deck.add(NineOfHearts);
        deck.add(TenOfHearts);
        deck.add(JackOfHearts);
        deck.add(QueenOfHearts);
        deck.add(KingOfHearts);

    };

    //shuffles the deck. That's it. Needed since the deck is private.
    public void shuffleDeck(){
        Collections.shuffle(deck);
    };

    //I want this method to pick a random card from the deck and deal it to another arrayList.
    public void dealCards(List<Card> player){
            player.add(deck.get(0));
            deck.remove(0);
    }

    //Lets player or dealer discard into discard pile
    public void discardCards(int cardIndex, List<Card> player){
            discardPile.add(player.get(cardIndex));
            player.remove(cardIndex);
    }

    //Lets player or dealer discard whole hand into discard pile
    public void discardHand(List<Card> player){
            discardPile.addAll(player);
            player.clear();
    }

    //Lets you reshuffle the discard into the deck.
    public void discardPileReshuffle(){
        deck.addAll(discardPile);
        discardPile.clear();
        shuffleDeck();
    };

    //Empties deck and discard pile. Helps to make new games
    public void clearDiscardAndDeck(){
        deck.clear();
        discardPile.clear();
    }

    public Integer getCardsLeftInDeck(){
        return deck.size();
    }

}
