package com.example.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.example.GamePanel;
import com.example.Items.Item;

public class Party {
    GamePanel gp;
    private ArrayList<Character> party = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gil = 12343;

    public Party(GamePanel gp) {
        this.gp = gp;
    }
    
    public Party(GamePanel gp,ArrayList<Character> party,  int gil) {
        this.gp = gp;
        this.party = party;
    
        this.gil = gil;
    }

    public void agregarPersonaje(Character c) {
        party.add(c);
    }

    public ArrayList<Character> getParty() {
        return this.party;
    }

    public void setParty(ArrayList<Character> party) {
        this.party = party;
    }

    public void breakGroup() {
        for (Character character : party) {
            character.setHp(character.getMaxHp());
            character.setMp(character.getMaxMp());
            character.deadState=true;
        }

    }

    public void defaultPosition(){
        for (Character character : party) {
            character.x=character.defaultX;
        }
    }

    public void buyItem(Item item, int num) {
        gil -= item.getPrice() * num;
        if (inventory.contains(item)) {
            inventory.get(inventory.indexOf(item)).setAmount(item.getAmount() + num);
        } else {
            inventory.add(item);
            inventory.get(inventory.indexOf(item)).setAmount(item.getAmount() + num);
        }
    }

    public boolean sellItem(Item item, int num) {
        gil += (item.getPrice() * num) / 2;
        if (inventory.contains(item)) {
            inventory.get(inventory.indexOf(item)).setAmount(item.getAmount() - num);
            if (inventory.get(inventory.indexOf(item)).getAmount() == 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getGil() {
        return gil;
    }

    public void update(){
        party.get(0).update();
        party.get(1).update();
        party.get(2).update();
       
    }


    public void draw(Graphics2D g2){
        party.get(0).draw(g2);
        party.get(1).draw(g2);
        party.get(2).draw(g2);
    }
}