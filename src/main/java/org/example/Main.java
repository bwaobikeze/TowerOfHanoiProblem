package org.example;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        towerOfHonoi startgame=new towerOfHonoi();
       startgame.creatingAdjList(startgame.CreatePegs("Strt"));
        startgame.creatingAdjList(startgame.CreatePegs("Dst"));
        startgame.creatingAdjList(startgame.CreatePegs("A1"));
        startgame.creatingAdjList(startgame.CreatePegs("A2"));
        startgame.creatingAdjList(startgame.CreatePegs("A3"));
        startgame.creatingAdjList(startgame.CreatePegs("A4"));

        startgame.creatEdgeForList(0,1);
        startgame.creatEdgeForList(2,1);
        startgame.creatEdgeForList(1,2);
        startgame.creatEdgeForList(2,3);
        startgame.creatEdgeForList(3,4);
        startgame.creatEdgeForList(4,5);
        startgame.creatEdgeForList(5,2);

        startgame.createDisk(10);
        startgame.printList();
    }
}