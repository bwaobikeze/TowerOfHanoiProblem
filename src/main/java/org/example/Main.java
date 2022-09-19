package org.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        towerOfHonoi startgame=new towerOfHonoi();

        startgame.BeginGame(2);
        startgame.printList();
    }
}