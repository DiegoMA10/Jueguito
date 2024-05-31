package com.example.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.example.GamePanel;
import com.example.items.Item;

public class Party {
    GamePanel gp;
    private ArrayList<Character> party = new ArrayList<>();
    private ArrayList<Item> inventory = new ArrayList<>();
    private int gil = 500;

    public Party(GamePanel gp) {
        this.gp = gp;
    }

    public Party(GamePanel gp, ArrayList<Character> party, int gil) {
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

    public void addExp(int exp) {
        for (Character character : party) {
            character.setExp(character.getExp() + exp);
        }
    }

    public void addGil(int gil) {
        this.gil += gil;
    }

    public void breakGroup() {
        for (Character character : party) {
            character.setHp(character.getMaxHp());
            character.setMp(character.getMaxMp());
            character.isAlive = true;
        }

    }

    public Entity aliveRandom() {
        Random r = new Random();
        ArrayList<Character> aliveparty = new ArrayList<>();
        for (Character character : party) {
            if (character.getIsAlive()) {
                aliveparty.add(character);
            }

        }
        int index = r.nextInt(aliveparty.size());
        return aliveparty.get(index);

    }

    public boolean isAlive() {
        for (Character character : party) {
            if (character.getIsAlive()) {

                return true;
            }

        }

        return false;
    }

    public void resetATB() {
        for (Character character : party) {
            character.atb.resetATB();
        }
    }

    public void defaultPosition() {
        for (Character character : party) {
            character.x = character.defaultX;
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

    public void update() {
        for (Character character : party) {
            character.update();
        }

    }

    public void draw(Graphics2D g2) {
        for (Character character : party) {
            character.draw(g2);
        }

    }

}