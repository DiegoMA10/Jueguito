package com.example.entity.enemy;

import com.example.GamePanel;
import com.example.entity.Entity;

public class Enemy extends Entity {
    GamePanel gp;
    protected String name;
    protected int characterID;

    protected int level;
    protected int MaxHp;
    protected int hp;
    protected int MaxMp;
    protected int mp;
    protected int strength;
    protected int dexterity;
    protected int magic;
    protected int defense;
    protected int magicDefense;
    protected int attack;
    protected int exp;
    protected Boolean deadState;

    public Enemy(GamePanel gp) {
        super(gp);

    }

}