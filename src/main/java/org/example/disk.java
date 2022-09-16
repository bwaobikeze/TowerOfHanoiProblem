package org.example;

import java.util.ArrayList;

public class disk {
   private int value;
   ArrayList<String>moves=new ArrayList<>();
    disk(int value){
        this.value=value;
        this.moves=new ArrayList<>();

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
