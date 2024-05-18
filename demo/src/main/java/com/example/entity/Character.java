package com.example.entity;

import java.awt.image.BufferedImage;

import com.example.GamePanel;
import com.example.object.Object;

public class Character extends Entity {

    public BufferedImage portrait;
    private int indexGroup;
    private String name;
    private int level;
    private int MaxHp;
    private int hp;
    private int MaxMp;
    private int mp;
    private int strength;
    private int dexterity;
    private int magic;
    private int defense;
    private int magicDefense;
    private int attack;
    private int exp;
    private int nextLevelExp;
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

    public boolean useObject(Object o) {

        if (o.useObject(this)) {
            return true;
        } else {
            return false;
        }

    }

    public static void changeInexGroup(Character c1, Character c2) {
        int aux = c1.getIndexGroup();
        c1.setIndexGroup(c2.getIndexGroup());
        c2.setIndexGroup(aux);
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

    public int getIndexGroup() {
        return indexGroup;

    }

    public void setIndexGroup(int indexGroup) {
        this.indexGroup = indexGroup;

    }

}
