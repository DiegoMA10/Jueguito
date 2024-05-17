package com.example.entity;

import java.awt.image.BufferedImage;

import com.example.GamePanel;
import com.example.UtilityTool;

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
    public int dexterity;
    public int magic;
    public int defense;
    public int magicDefense;
    public int attack;
    public int exp;
    public int nextLevelExp;
    public Entity currentWeapon;

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

    public static void changeInexGroup(Character c1 , Character c2){
        int aux= c1.indexGroup;
        c1.indexGroup=c2.indexGroup;
        c2.indexGroup=aux;
    }
}
 


