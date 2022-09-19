package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class towerOfHonoi {
HashMap<String,node>pegs=new HashMap<>();
HashMap<String,ArrayList<String>>Conncetions=new HashMap<>();
   void BeginGame(int disk){
       CreatePegs(disk);
   }
int createDisk(int numberOfDisk){

    if(numberOfDisk==0){
        return 0;
    }
    else
    loadStartPeg(new disk(numberOfDisk));

    return createDisk(numberOfDisk-1);
}

void loadStartPeg(disk obj){
node current=pegs.get("strt");
current.diskOnpeg.push(obj);

}
void CreatePegs(int numbOfDisk){
    pegs.put("strt",new node("Strt"));
    pegs.put("des",new node("dest"));
    pegs.put("A1",new node("A1"));
    pegs.put("A2",new node("A2"));
    pegs.put("A3",new node("A3"));
    pegs.put("A4",new node("A4"));
    Conncetions.put("strt",new ArrayList<String>(Arrays.asList("dest")));
    Conncetions.put("dest",new ArrayList<String>(Arrays.asList("A1")));
    Conncetions.put("A1",new ArrayList<String>(Arrays.asList("dest","A2")));
    Conncetions.put("A2",new ArrayList<String>(Arrays.asList("A3")));
    Conncetions.put("A3",new ArrayList<String>(Arrays.asList("A4")));
    Conncetions.put("A4",new ArrayList<String>(Arrays.asList("A1")));
    createDisk(numbOfDisk);

}

Boolean canMove(node avaliablePeg){
    if (avaliablePeg.diskOnpeg.isEmpty()){
        return true;
    }
    else
        return false;
}



//void PlayHonaoiGame(int disks){

//
// }
void printList( ){

    node current=pegs.get("strt");
    current.diskOnpeg.pop();
    System.out.print(current.pegName+"("+current.diskOnpeg.peek().getValue()+")");
}


}
