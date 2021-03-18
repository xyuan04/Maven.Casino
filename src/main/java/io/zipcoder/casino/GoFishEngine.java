package io.zipcoder.casino;

public class GoFishEngine {
    public static void main(String[] args) {
        Player dummy=new Player("Arg",1000);
        GoFish demo=new GoFish(dummy);
        demo.playGame();
    }
}
