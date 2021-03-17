package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;

public class GoFish extends CardGame {
    private List<Card> oswald= new ArrayList<>();
    private List<Card> dealer= new ArrayList<>();
    private List<String> books=new ArrayList();
    int playerScore=0;
    int dealerScore=0;

    public void playGame() {

    }

    public Boolean checkWinner() {
        return null;
    }

    public void turnStructure(){
        
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

    public Boolean gotAnyKings(ArrayList<Card> hand, String name) {
        for(Card i:hand){
            if(name.equals(i.getCardName())){
                return true;
            }
        }
        return false;
    }

    public void takeCards(ArrayList<Card> taker, ArrayList<Card> taken, String name) {
        for(int i=0;i<taken.size();i++){
            if(name.equals(taken.get(i).getCardName())){
                taker.add(taken.get(i));
                taken.remove(i);
            }
        }
    }

    public Boolean drawAnyKings(ArrayList<Card> player, String name) {
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

    public Boolean completeSet(ArrayList<Card> player, String name) {
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
