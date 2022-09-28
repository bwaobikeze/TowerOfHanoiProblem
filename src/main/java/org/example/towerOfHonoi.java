package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class towerOfHonoi {
HashMap<String,node>pegs=new HashMap<>();
HashMap<String,ArrayList<String>>Conncetions=new HashMap<>();
    void BeginGame(int disk){
       CreatePegs(disk);
   }
    /*
    *CreatePegs(int numbOfDisk)
    * Takes in an int parameter of number of disks
    * this function just initializing my representation Of
    * */
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
    /*
createDisk(int numberOfDisk)
take in a numberOfDisk parameter which is passed in through the CreatingList function and iterates depending on the number of disks passed in and creates new disk objects and as it creates these new disk objects it will be passing it to
loadingDisks() function after  its done with the iteration it calls the Hanoi game
*
* */
        void createDisk(int numberOfDisk){

    for(int i=numberOfDisk-1; i>=0;i--){
        loadStartPeg(new disk(i));
    }
        PlayHonaoiGame(numberOfDisk);

}
    /*
loadStartPeg(disk obj)
this function takes in A total number of disks as a parameter,
this function just loads or push the disks into the starting nodes
Stack
*
* */
        void loadStartPeg(disk obj){
        node current=pegs.get("strt");
        current.diskOnpeg.push(obj);
        }


    /*
 canMove(node avaliablePeg)
this function takes in A total number of disks as a parameter,
this function checks to see if depending on the node we are on can the disk on the
top of the stack move
if it can then it returns true, if it cant returns false
*
* */

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

    /*
 PlayHonaoiGame()
this function takes in A total number of disks as a parameter,
this function is where the central logic loop lies  to perform a execute the right amount of moves
*
* */

        void PlayHonaoiGame(int disks){

    ArrayList<String>current=new ArrayList<>();
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

                if (vistednodesStack.isEmpty()||checkIfDesthasLargest(proccesingNode,disks)) {
                        searchForAvailable.addAll(changefrontOfQueue(proccesingNode));
                        searchForAvailable.poll();
                        proccesingNode=searchForAvailable.peek();
                        vistednodesStack=proccesingNode.diskOnpeg;


                }
                else if(canMove(proccesingNode)){
                    moveDisk(proccesingNode.pegName);
                }
                else{
                    clearPath(proccesingNode);
                }

        }
        System.out.println("Finish Game");

   }


    /*
 void moveDisk(String start)
this function takes in a processing node String name,
this function execute the actual moving of the disk fromm source to destination
and prints it to console
*
* */

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
                 System.out.println(tempDisk.getValue()+" moved from "+startPeg.pegName+" to "+destPointer.pegName);
                 destPointer.diskOnpeg.push(tempDisk);

            }
        }

    }
    /*
checkIfDesthasLargest(node currentNodeProc,int NumOfDisk)
this function takes in a processing node type, and an int number Of disks
this function check if the destination node's stack size is equal to the number of total disks
return true if it is return false if it isnt
*
* */
    boolean checkIfDesthasLargest(node currentNodeProc,int NumOfDisk){
       if(currentNodeProc.pegName=="dest"){
           if(currentNodeProc.diskOnpeg.lastElement().getValue()==NumOfDisk-1){
               return true;
           }
       }
       return false;
    }
/*
changefrontOfQueue(node addItsconnection)
this function takes in a processing node type
Then it grabs its connection from a arraylist of string types and then  gets their node object and adds them to arraylist of node types
after which it returns A new node type ArrayList
*
* */

        ArrayList changefrontOfQueue(node addItsconnection){
               ArrayList<node>currentProcesdsingNodeConnect=new ArrayList<>();
               ArrayList<String>currentConnectList=new ArrayList<>();
            currentConnectList=Conncetions.get(addItsconnection.pegName);
            for(String node:currentConnectList)
               currentProcesdsingNodeConnect.add(pegs.get(node));

            return currentProcesdsingNodeConnect;
        }
/*
clearPath(node nodetoClear)
this function takes in a processing node type and traverses the graph to check if
there is any available nodes stack to move the current nodes disk to an  availebale peg
*
* */

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
                        String tempStringNode=goBackto.pop();
                        moveDisk(tempStringNode);
                    }
                    else{
                        goBackto.push(currentNode.get(j));
                    }
           }
       }



    }



}
