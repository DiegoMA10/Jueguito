package com.example.entity;

import java.util.ArrayList;

import com.example.object.Item;


public class Group {
    ArrayList<Character> group = new ArrayList<>();
    ArrayList<Item>inventory = new ArrayList<>();
    public int gil = 500;
    
    public Group() {
        
    } 

    public void agregarPersonaje(Character c){
        group.add(c);
    }

    public ArrayList<Character> getGroup() {
        return this.group;
    }
    
    
    public void breakGroup(){
        for (Character character : group) {
            character.hp=character.MaxHp;
            character.mp=character.MaxMp;
        }
      
    }
}