package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Group {
    List<Character> group = new ArrayList<>();
    public int gil = 500;
    public Group() {
        
    } 

    public void agregarPersonaje(Character c){
        group.add(c);
    }

    public List<Character> getGroup() {
        return this.group;
    }
    
    
    public void breakGroup(){
        for (Character character : group) {
            character.hp=character.MaxHp;
            character.mp=character.MaxMp;
        }
      
    }
}