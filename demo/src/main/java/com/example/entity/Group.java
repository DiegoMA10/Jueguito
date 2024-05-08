package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Group {
    List<Character> group = new ArrayList<>();

    public Group() {
        
    } 

    public void agregarPersonaje(Character c){
        group.add(c);
    }

    public List<Character> getGroup() {
        return this.group;
    }
    
    
    
}