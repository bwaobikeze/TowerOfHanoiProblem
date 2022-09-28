package org.example;

import java.util.ArrayList;
import java.util.Stack;


/*
 Node class
 * creates a node object constructor
 with a String value that holds the pegName
 and a stack so the disks can travers the graph
 * */
public class node {
    String pegName;
    ArrayList<String> children=new ArrayList<>();
    Stack<disk> diskOnpeg = new Stack<>();
   node(String pegName){
      this.pegName=pegName;
      this.diskOnpeg= new Stack<>();
  }
}
