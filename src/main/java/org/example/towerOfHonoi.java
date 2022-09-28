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
                if (vistednodesStack.isEmpty()||checkIfDesthasLargest(proccesingNode,disks)) {
                        searchForAvailable.addAll(changefrontOfQueue(proccesingNode));
                        searchForAvailable.poll();
                        proccesingNode=searchForAvailable.peek();
                        vistednodesStack=proccesingNode.diskOnpeg;


                }
                else if(canMove(proccesingNode)){
                    moveDisk(proccesingNode.pegName);
                    //System.out.println("Starting stack is empty");
                }
                else{
                    clearPath(proccesingNode);
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
                 System.out.println(tempDisk.getValue()+" moved from "+startPeg.pegName+" to "+destPointer.pegName);
                 destPointer.diskOnpeg.push(tempDisk);

            }
        }

    }
    boolean checkIfDesthasLargest(node currentNodeProc,int NumOfDisk){
       if(currentNodeProc.pegName=="dest"){
           if(currentNodeProc.diskOnpeg.lastElement().getValue()==NumOfDisk-1){
               return true;
           }
       }
       return false;
    }


        ArrayList changefrontOfQueue(node addItsconnection){
               ArrayList<node>currentProcesdsingNodeConnect=new ArrayList<>();
               ArrayList<String>currentConnectList=new ArrayList<>();
            currentConnectList=Conncetions.get(addItsconnection.pegName);
            for(String node:currentConnectList)
               currentProcesdsingNodeConnect.add(pegs.get(node));

            return currentProcesdsingNodeConnect;
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
