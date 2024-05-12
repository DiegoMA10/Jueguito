package com.example.entity;

import java.awt.image.BufferedImage;

import com.example.GamePanel;

public class Character extends Entity {

    public Character(GamePanel gp) {
        super(gp);
        
    }
    public BufferedImage portrait= null;
    public String name;
    public int level;
    public int MaxHp;
    public int hp;
    public int MaxMp;
    public int mp;
    public int strength;
    public int magic;
    public int defense;
    public int magicDefense;
    public int exp;
    public int nextLevelExp;
    public Entity currentWeapon;
    public boolean posicion;

}
