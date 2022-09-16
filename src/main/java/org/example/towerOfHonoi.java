package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class towerOfHonoi {
    ArrayList<LinkedList<node>> honaiGameboard=new ArrayList<>();

int createDisk(int numberOfDisk){

    if(numberOfDisk==0){
        return 0;
    }
    else
    loadStartPeg(new disk(numberOfDisk));

    return createDisk(numberOfDisk-1);
}

void loadStartPeg(disk obj){
    node pointer=honaiGameboard.get(0).get(0);
    pointer.diskOnpeg.add(obj);
}
node CreatePegs(String name){
    node pegs= new node(name);
    return pegs;
}
void creatingAdjList(node obj){
//    ArrayList<LinkedList<node>> honaiGameboard=new ArrayList<>();
    LinkedList<node> currentList= new LinkedList<>();
    currentList.add(obj);
    honaiGameboard.add(currentList);

}

void creatEdgeForList(int source, int dest){
    LinkedList<node> currentList= honaiGameboard.get(source);
    node destnode=honaiGameboard.get(dest).get(0);
    currentList.add(destnode);
}

Boolean canMove(node avaliablePeg){
    if (avaliablePeg.diskOnpeg.isEmpty()){
        return true;
    }
    else
        return false;
}



void PlayHonaoiGame(int disks){
    createDisk(disks);
    Queue<node> visitedPegs= new LinkedList<>();
    node startPeg=honaiGameboard.get(0).get(0);
    visitedPegs.add(startPeg);

    while(!visitedPegs.isEmpty()){

    }


}
void printList( ){
    for(  LinkedList<node> currentList: honaiGameboard){
        for(node peg:currentList){
            if(!peg.diskOnpeg.isEmpty()) {
                System.out.print(peg.pegName + "(" + peg.diskOnpeg.peek().getValue() + ")" + " -> ");
            }
            else
            System.out.print(peg.pegName + "("+")"+" -> ");
        }
        System.out.println();
    }
}


}
