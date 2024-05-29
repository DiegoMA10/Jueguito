package com.example.spells;

import java.awt.image.BufferedImage;

import com.example.GamePanel;

public class Spell {
    GamePanel gp ;
    protected BufferedImage[] animation;
    protected int value;
    protected String name;
    protected int cost;
    
    public Spell(GamePanel gp) {
        this.gp = gp;
    }
}
