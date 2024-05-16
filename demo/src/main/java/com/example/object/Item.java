package com.example.object;

import java.awt.image.BufferedImage;

import com.example.BattlePanel;

public class Item {
    protected BufferedImage[] animation;
    BattlePanel bp;
    public int idItem;
    protected String name;
    protected String description;
    protected int amount=1;
    protected int price;

    public Item(BattlePanel bp){
        this.bp = bp;
    }
}
