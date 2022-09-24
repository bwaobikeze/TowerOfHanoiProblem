package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class towerOfHonoi {
HashMap<String,node>pegs=new HashMap<>();
HashMap<String,ArrayList<String>>Conncetions=new HashMap<>();
   void BeginGame(int disk){
       CreatePegs(disk);
   }

    void CreatePegs(int numbOfDisk){
        pegs.put("strt",new node("strt"));
        pegs.put("dest",new node("dest"));
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
void createDisk(int numberOfDisk){
//
//    if(numberOfDisk==0){
//        return 0;
//    }
//    else
//    loadStartPeg(new disk(numberOfDisk));
//
//    return createDisk(numberOfDisk-1);
    for(int i=numberOfDisk-1; i>=0;i--){
        loadStartPeg(new disk(i));
    }
    PlayHonaoiGame(numberOfDisk);

}

void loadStartPeg(disk obj){
node current=pegs.get("strt");
current.diskOnpeg.push(obj);

}





    boolean canMove(node avaliablePeg) {
        ArrayList<String> current = Conncetions.get(avaliablePeg.pegName);
        //System.out.print(current);
        for (int i = 0; i < current.size(); i++) {
            if (pegs.get(current.get(i)).diskOnpeg.isEmpty() || avaliablePeg.diskOnpeg.peek().getValue() < pegs.get(current.get(i)).diskOnpeg.peek().getValue()) {
                return true;
            }

        }
        return false;
    }



void PlayHonaoiGame(int disks){

    ArrayList<String>current=new ArrayList<>();
    Iterator connectIt= current.iterator();
//       int originalNumOfDisks=disks;
       Stack<disk>vistednodesStack=new Stack<>();
       Queue<node>searchForAvailable=new ArrayDeque<>();
       searchForAvailable.add(pegs.get("strt"));
       node proccesingNode=searchForAvailable.peek();
       vistednodesStack=proccesingNode.diskOnpeg;
            //current=Conncetions.get("strt");


while (!searchForAvailable.isEmpty()) {

    if(pegs.get("dest").diskOnpeg.size()==disks){
        break;
    }
    //System.out.println("Disk: "+vistednodesStack.peek().getValue());
    if (vistednodesStack.isEmpty()) {
        //current = Conncetions.get(proccesingNode.pegName);
        while (connectIt.hasNext()) {
            searchForAvailable.add(pegs.get(connectIt.next()));
        }
        searchForAvailable.poll();
    }
    else if(canMove(proccesingNode)){
        moveDisk(proccesingNode.pegName);
    }

        }
System.out.println("Finish Game");

   }


//void clearPath()
    void moveDisk(String start){
        node destPointer;
       disk tempDisk;
       ArrayList<String> currentConnectionPointer=Conncetions.get(start);
       node startPeg=pegs.get(start);
        for(int i=0; i<currentConnectionPointer.size();i++){
             destPointer=pegs.get(currentConnectionPointer.get(i));
            if(startPeg.diskOnpeg.isEmpty()){
                return;
            }
            else{
                 tempDisk=startPeg.diskOnpeg.pop();
                 System.out.println(tempDisk.getValue()+" moved form "+startPeg.pegName+" to "+destPointer.pegName);
                 destPointer.diskOnpeg.push(tempDisk);

            }
        }

    }

    void clearPath(node nodetoClear){
       ArrayList<String>currentNode=new ArrayList<>();
       currentNode=Conncetions.get(nodetoClear.pegName);
       Stack<String>goBackto=new Stack<>();
       for(int i =0; i<currentNode.size();i++){
           goBackto.push(currentNode.get(i));
       }

       while (!goBackto.isEmpty()){
           currentNode=Conncetions.get(goBackto.peek());
           for(int j =0; j<currentNode.size();j++){
                    if(goBackto.contains(currentNode.get(j))){
                        continue;
                    }
                    else if(pegs.get(currentNode.get(j)).diskOnpeg.isEmpty()||pegs.get(goBackto.peek()).diskOnpeg.peek().getValue()<pegs.get(currentNode.get(j)).diskOnpeg.peek().getValue()){
                        goBackto.pop();
                        moveDisk(goBackto.pop());
                    }
                    else{
                        goBackto.push(currentNode.get(j));
                    }
           }
       }



    }
void printList( ){
    moveDisk("strt");
    moveDisk("strt");

//    node current=pegs.get("strt");
//    System.out.println(current.diskOnpeg.peek().getValue());
//    System.out.println(current.diskOnpeg.size());
//    System.out.println("==============================");
//    current.diskOnpeg.pop();
//    System.out.println("First Pop");
//    System.out.println(current.diskOnpeg.peek().getValue());
//    System.out.println(current.diskOnpeg.size());
//    current.diskOnpeg.pop();
    //System.out.println(current.diskOnpeg.peek().getValue());

//    current.diskOnpeg.pop();
//    if(current.diskOnpeg.isEmpty()){
//        return;
//    }
//    else {
//        current.diskOnpeg.pop();
//        System.out.print(current.pegName + "(" + current.diskOnpeg.peek().getValue() + ")");
//    }
}


}
