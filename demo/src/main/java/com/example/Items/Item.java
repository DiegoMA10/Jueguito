package com.example.Items;

import java.awt.image.BufferedImage;

import com.example.BattlePanel;
import com.example.GamePanel;
import com.example.entity.Character;

public abstract class Item {
    GamePanel gp;
    protected BufferedImage[] animation;
    public int idItem;
    protected String name;
    protected String description;
    protected int amount = 1;
    protected int price;
    protected int value;
    public static int maxAmount = 99;

    public Item(GamePanel gp) {
        this.gp = gp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public boolean setAmount(int amount) {

        this.amount = amount;

        return false;
    }

    public abstract boolean useObject(Character e);

}
