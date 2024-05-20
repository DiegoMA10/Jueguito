package com.example.entity;

import java.awt.image.BufferedImage;

import com.example.GamePanel;
import com.example.Items.Item;

public class Character extends Entity {

    public BufferedImage portrait;
    protected int indexGroup;
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
    protected int nextLevelExp;
    protected boolean state;
    protected Entity currentWeapon;

    public Character(GamePanel gp) {
        super(gp);

    }

    /*
     * formula
     * attack2 = attack + strengh2
     * Damage = Attack +((Level*Level*Attack2)(256))*3/2
     * 
     * Defensa Formula
     * 
     * DamageFinal = (Damage*(255-Defense)/256)+1
     * 
     * (Magia) = SpellPower*4+(Level*Magic*SpellPower/32)
     * 
     * (Monsters) = Level*Level*(Attack*4+Strenght)/256
     * 
     * 
     */

    public boolean useObject(Item o) {

        if (o.useObject(this)) {
            return true;
        } else {
            return false;
        }

    }
    
    public int getIndexGroup() {
        return indexGroup;

    }

    public void setIndexGroup(int indexGroup) {
        this.indexGroup = indexGroup;

    }


    public static void changeInexGroup(Character c1, Character c2) {
        int aux = c1.getIndexGroup();
        c1.setIndexGroup(c2.getIndexGroup());
        c2.setIndexGroup(aux);
    }

    public int getCharacterID() {
        return characterID;
    }

    public int getExp() {
        return exp;

    }

    public void setExp(int exp) {
        this.exp = exp;

    }

    public int getNextLevelExp() {
        return nextLevelExp;

    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;

    }

    public int getStrength() {
        return strength;

    }

    public void setStrength(int strength) {
        this.strength = strength;

    }

    public int getAttack() {
        return attack;

    }

    public void setAttack(int attack) {
        this.attack = attack;

    }

    public int getDexterity() {
        return dexterity;

    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;

    }

    public int getDefense() {
        return defense;

    }

    public void setDefense(int defense) {
        this.defense = defense;

    }

    public int getMagic() {
        return magic;

    }

    public void setMagic(int magic) {
        this.magic = magic;

    }

    public int getMagicDefense() {
        return magicDefense;

    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;

    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;

    }

    public void setLevel(int level) {
        this.level = level;

    }

    public int getMaxHp() {
        return MaxHp;

    }

    public void setMaxHp(int maxHp) {
        MaxHp = maxHp;

    }

    public int getHp() {
        return hp;

    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > this.MaxHp) {
            this.hp = this.MaxHp;
        }

    }

    public int getMaxMp() {
        return MaxMp;

    }

    public void setMaxMp(int maxMp) {
        MaxMp = maxMp;

    }

    public int getMp() {
        return mp;

    }

    public void setMp(int mp) {
        this.mp = mp;
        if (this.mp > this.MaxMp) {
            this.mp = this.MaxMp;
        }

    }

   
}
