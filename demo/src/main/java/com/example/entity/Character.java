package com.example.entity;

import java.awt.image.BufferedImage;

import com.example.GamePanel;

public class Character extends Entity  {

    public BufferedImage portrait= null;
    public int indexGroup;
    public String name;
    public int level;
    public int MaxHp;
    public int hp;
    public int MaxMp;
    public int mp;
    public int strength;
    public int speed;
    public int magic;
    public int defense;
    public int magicDefense;
    public int attack;
    public int exp;
    public int nextLevelExp;
    public Entity currentWeapon;
    public boolean posicion;

    public Character(GamePanel gp) {
        super(gp);
        
    }
    
    /* 
    formula 
    attack2 = attack + strengh2
    Damage = Attack +((Level*Level*Attack2)(256))*3/2

    Defensa Formula 

    DamageFinal = (Damage*(255-Defense)/256)+1

    (Magia) = SpellPower*4+(Level*Magic*SpellPower/32)

    (Monsters) = Level*Level*(Attack*4+Strenght)/256


 */


}
 


