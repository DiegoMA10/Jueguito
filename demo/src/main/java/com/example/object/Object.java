package com.example.object;

import java.awt.image.BufferedImage;

import com.example.BattlePanel;

public class Object {
    protected BufferedImage[] animation;
   
    public int idObject;
    protected String name;
    protected String description;
    protected int amount=1;
    protected int price;

    public Object(){
       
    }
}
