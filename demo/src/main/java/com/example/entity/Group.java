package com.example.entity;

import java.util.ArrayList;

import com.example.Items.Eter;
import com.example.Items.Item;
import com.example.Items.Potion;


public class Group {
    ArrayList<Character> group = new ArrayList<>();
    ArrayList<Item>inventory = new ArrayList<>();
    public int gil = 1234;
    
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
            character.setHp(character.getMaxHp());
            character.setMp(character.getMaxMp());
        }
        
    }

    public void buyItem(Item item, int num){
        gil-=item.getPrice()*num;
        if (inventory.contains(item)) {
           inventory.get(inventory.indexOf(item)).setAmount(item.getAmount()+num);
        }else{
            inventory.add(item);
            inventory.get(inventory.indexOf(item)).setAmount(item.getAmount()+num);
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getGil() {
        return gil;
    }

    
}