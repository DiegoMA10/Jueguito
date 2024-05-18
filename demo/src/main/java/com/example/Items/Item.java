package com.example.object;

import java.awt.image.BufferedImage;

import com.example.BattlePanel;
import com.example.GamePanel;
import com.example.entity.Character;


public abstract class Object {
    GamePanel gp;
    protected BufferedImage[] animation;
    public int idObject;
    protected String name;
    protected String description;
    protected int amount=1;
    protected int price;
    protected int value;

    public Object(GamePanel gp){
       this.gp= gp;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract boolean useObject(Character e);
   
    

    

}
