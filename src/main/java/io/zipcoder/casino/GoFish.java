package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoFish extends CardGame {
    private List<Card> oswald= new ArrayList<>();
    private List<Card> dealer= new ArrayList<>();
    Player currentplayer;
    private List<String> books=new ArrayList();
    int playerScore=0;
    int dealerScore=0;


    public GoFish(Player current){
        currentplayer=current;
    }

    //blank constructor, mostly to make tests easier.
    public GoFish(){

    }

    public void playGame() {
        System.out.println("Welcome to Go Fish, "+currentplayer.getPlayerName());
        System.out.println("This is a nice, friendly game so don't worry about " +
                "the chips, okay?");
        System.out.println("Are you ready to begin? (enter 'yes' or 'y')");
        String input = Displays.getStringInput();
        Boolean stillPlaying;
        //The game setup for if you agre to play starts here
        while(input.equalsIgnoreCase("yes")||input.equalsIgnoreCase("y")){
            newGameState();
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
           stillPlaying=true;
            //The actual GAME starts here.
            while(stillPlaying==true) {
                //player takes their turn
                if(stillPlaying==true){
                    System.out.println("You have "+playerScore+" books.");
                    turnStructure();}
                //end step for player, checks for winner
                if(dealer.size()==0||oswald.size()==0||books.size()==12){
                    stillPlaying=false;}
                if(stillPlaying==true){turnStructureAI();
                    System.out.println("The dealer has "+dealerScore+" books.");}
                //end step for dealer, checks for winner
                if(dealer.size()==0||oswald.size()==0||books.size()==12){
                    stillPlaying=false;}
            }
            System.out.println(checkWinner());
            System.out.println("Did you want to play again? (enter 'yes' or 'y')");
            input = Displays.getStringInput();
        }
        System.out.println("Okay then, take care and thanks for playing!");
    }

    public String checkWinner() {
        if(dealerScore>playerScore){return "Dealer wins! Sorry....";}
        else return "You win! Congrats!";
    }

    public void newGameState(){
        clearDiscardAndDeck();
        oswald.clear();
        dealer.clear();
        dealerScore=0;
        playerScore=0;
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



    public void turnStructure(){
        Boolean legalchoice=false;
        Boolean takeAnotherTurn=true;
        String input="";
        while (takeAnotherTurn) {
            System.out.print(seeHand(oswald));
            if(books.size()>0){System.out.println(showBooks());}
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
                System.out.println("You drew: "+showCard(oswald, oswald.size()-1));
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

    public void turnStructureAI(){
        Boolean takeAnotherTurn=true;
        String input="";
        while (takeAnotherTurn) {
            System.out.println("Okay, now it's the dealer's turn!");
            takeAnotherTurn=false;
            Random r=new Random();
            Integer rand=r.nextInt(dealer.size());
            input=dealer.get(rand).getCardName();
            System.out.println("Dealer asks: 'got any "+input+"'s?");

            if (gotAnyKings(oswald, input)) {
                takeCards(dealer,oswald,input);
                System.out.println("Dealer caught a fish! They get another turn!");
                takeAnotherTurn=true;
                if(completeSet(dealer, input)){
                    dealerScore++;
                    System.out.println("Dealer closes a book!");
                }
            } else {
                dealCards(dealer);
                System.out.println("You didn't! Dealer draws a "+showCard(dealer, dealer.size()-1)+"!");
                if(completeSet(dealer, dealer.get(dealer.size()-1).getCardName())){
                    dealerScore++;
                    System.out.println("Dealer closes a book!");
                }
                if(drawAnyKings(dealer,input)){
                    takeAnotherTurn=true;
                    System.out.println("Dealer drew a fish! They get another turn!");
                }
            }
        }
        System.out.println("Dealer's turn over!");
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
            if(name.equalsIgnoreCase(i.getCardName())){
                return true;
            }
        }
        return false;
    }

    public void takeCards(List<Card> taker, List<Card> taken, String name) {
        for(int i=0;i<taken.size();i++){
            if(name.equalsIgnoreCase(taken.get(i).getCardName())){
                taker.add(taken.get(i));
                taken.remove(i);
                i=0;
            }
        }
    }

    public Boolean drawAnyKings(List<Card> player, String name) {
        if(name.equalsIgnoreCase(player.get(player.size()-1).getCardName())){
            return true;
        }
        return false;
    }

    public String showBooks(){
        String temp="Completed books so far: ";
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
