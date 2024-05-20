package com.example.entity;

import java.util.ArrayList;

import com.example.Items.Ether;
import com.example.Items.Item;
import com.example.Items.Potion;

public class Party {
    ArrayList<Character> party = new ArrayList<>();
    ArrayList<Item> inventory = new ArrayList<>();
    public int gil = 12343;

    public Party() {

    }

    public void agregarPersonaje(Character c) {
        party.add(c);
    }

    public ArrayList<Character> getParty() {
        return this.party;
    }

    public void breakGroup() {
        for (Character character : party) {
            character.setHp(character.getMaxHp());
            character.setMp(character.getMaxMp());
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

}