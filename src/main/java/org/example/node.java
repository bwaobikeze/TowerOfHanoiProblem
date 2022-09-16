package org.example;

import java.util.ArrayList;
import java.util.Stack;

public class node {
    String pegName;
    ArrayList<String> children=new ArrayList<>();
    Stack<disk> diskOnpeg = new Stack<>();
   node(String pegName){
      this.pegName=pegName;
      this.children= new ArrayList<>();
      this.diskOnpeg= new Stack<>();
  }
}
