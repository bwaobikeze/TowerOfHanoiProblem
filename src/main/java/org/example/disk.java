package org.example;

import java.util.ArrayList;

/*
 disk class
 * creates a disk object constructor
 with a integer value that holds the value of the disk
 and a stack so the disks can travers the graph
 * */
public class disk {
   private int value;
   ArrayList<String>moves=new ArrayList<>();
    disk(int value){
        this.value=value;

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
