package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class GoFish extends CardGame {
    private List<Card> oswald= new ArrayList<>();
    private List<Card> dealer= new ArrayList<>();
    Player currentplayer;
    private List<String> books=new ArrayList();
    int playerScore=0;
    int dealerScore=0;


    public GoFish(Player current){
        currentplayer=current;
        makeDeck();
        shuffleDeck();
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
    }

    //blank constructor, mostly to make tests easier.
    public GoFish(){
        makeDeck();
        shuffleDeck();
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
        dealCards(oswald);
        dealCards(dealer);
    }

    public void playGame() {
        System.out.println("Welcome to Go Fish, "+currentplayer.getPlayerName());
        System.out.println("This is a nice, friendly game so don't worry about " +
                "the chips, okay?");
        for(int i=0; i<7;i++){
        if (completeSet(oswald, oswald.get(0).getCardName())){
            playerScore++;
        }
        if (completeSet(dealer, dealer.get(0).getCardName())){
            dealerScore++;
        }
        }
        if(playerScore>0||dealerScore>0){
            System.out.println("We already have some books! Crazy!");}

    }

    public String checkWinner() {
        if(dealerScore>playerScore){return "Dealer wins! Sorry....";}
        else return "You win! Congrats!";
    }




    public void turnStructure(){
        Boolean legalchoice=false;
        Boolean takeAnotherTurn=true;
        String input="";
        while (takeAnotherTurn) {
            System.out.println("Alright, Go fish!");
            takeAnotherTurn=false;
            legalchoice=false;
            input="";
            while (!legalchoice) {
                input = Displays.getStringInput();
                legalchoice = true;
                if (!gotAnyKings(oswald, input)) {
                    System.out.println("You can only pick card names you have in hand");
                    legalchoice = false;
                }
            }
            if (gotAnyKings(dealer, input)) {
                System.out.println("You caught fish!");
                takeCards(oswald, dealer, input);
                takeAnotherTurn=true;
                System.out.println("You caught a fish! You get another turn!");
                if(completeSet(oswald, input)){
                    playerScore++;
                    System.out.println("Congrats, you closed a book!");
                }
            } else {
                System.out.println("Whoops! No bites, better draw!");
                dealCards(oswald);
                if(completeSet(oswald, oswald.get(oswald.size()-1).getCardName())){
                    playerScore++;
                    System.out.println("Congrats, you closed a book!");
                }
                if(drawAnyKings(oswald,input)){
                    takeAnotherTurn=true;
                    System.out.println("You caught a fish! You get another turn!");
                }
            }
        }
        System.out.println("Turn over!");
    }

    public Integer cardsInHand(List<Card> player){
        return player.size();
    }

    public String showCard(List<Card> player, int indexOfCard) {
        String name=player.get(indexOfCard).getCardName();
        String suit=player.get(indexOfCard).getCardSuit();
        return name+" of "+suit;
    }

    public String seeHand(List<Card> player){
        String wholeHand="Your hand is:\n";
        for(int i=0; i<player.size();i++){
            wholeHand=wholeHand.concat(showCard(player, i)+"\n");
        }
        return wholeHand;
    }

    public Boolean gotAnyKings(List<Card> hand, String name) {
        for(Card i:hand){
            if(name.equals(i.getCardName())){
                return true;
            }
        }
        return false;
    }

    public void takeCards(List<Card> taker, List<Card> taken, String name) {
        for(int i=0;i<taken.size();i++){
            if(name.equals(taken.get(i).getCardName())){
                taker.add(taken.get(i));
                taken.remove(i);
            }
        }
    }

    public Boolean drawAnyKings(List<Card> player, String name) {
        if(name.equals(player.get(player.size()-1).getCardName())){
            return true;
        }
        return false;
    }

    public String showBooks(){
        String temp="";
               for(String i:books){
                            temp=temp.concat(i+", ");
               }
               return temp;
    }

    public Boolean completeSet(List<Card> player, String name) {
        int first=0;
        int second=0;
        int third=0;
        int fourth=0;
        int nameFound=0;
        for (int i=0;i<player.size();i++){
            if (name.equals(player.get(i).getCardName())){
                nameFound++;
                if(nameFound==1){first=i;}
                if(nameFound==2){second=i;}
                if(nameFound==3){third=i;}
                if(nameFound==4){fourth=i;}
            }
        }
        if (nameFound==4){
            discardCards(fourth,player);
            discardCards(third,player);
            discardCards(second,player);
            discardCards(first,player);
            books.add(name);
            return true;
        }
        return false;
    }
}
