package com.example.entity;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class Aerith extends Character {

    public Aerith(GamePanel gp) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        baseMaxHp = 40;
        baseMaxMp = 20;
        RawStats(1);
        setIndexGroup(0);
        characterID = 1;
        refreshPosition();
        getPlayerImagen();

    }

    public Aerith(GamePanel gp, int level, int exp, int partyIndex, int hp, int mp, boolean isAlive) {
        super(gp);
        baseMaxHp = 40;
        baseMaxMp = 20;
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID = 1;
        this.isAlive = isAlive;
        setExp(exp);
        setLevel(level);
        RawStats(level);
        setHp(hp);
        setMp(mp);
        setIndexGroup(partyIndex);
        refreshPosition();
        getPlayerImagen();
    }

    public void RawStats(int level) {
        setName("Aerith");
        
        setLevel(level);
        checkAbilities();
        setNextLevelExp(gp.dataBase.getExpForNextLevel(level));
        setMaxHp(gp.dataBase.getHpForLevel(level));
        setHp(30);
        setMaxMp(gp.dataBase.getMpForLevel(level));
        setMp(1);
        setStrength(25 + level - 1);
        setDexterity(30 + level - 1);
        setMagic(27 + ((level)) - 1);
        setAttack(15 + level - 1);
        setDefense(50 + level - 1);
        setMagicDefense(50 + level - 1);

    }

    public void getPlayerImagen() {
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta + "aerith_left");
        left1 = setUp(carpeta + "aerith_left1");

        right = setUp(carpeta + "aerith_right");
        right1 = setUp(carpeta + "aerith_right1");

        up = setUp(carpeta + "aerith_up");
        up1 = setUp(carpeta + "aerith_up1");
        up2 = setUp(carpeta + "aerith_up2");

        down = setUp(carpeta + "aerith_down");
        down1 = setUp(carpeta + "aerith_down1");
        down2 = setUp(carpeta + "aerith_down2");
        portrait = setUp(carpeta + "aerithPortrait");
        cast = setUp(carpeta + "aerith_cast");
        takeDamage = setUp(carpeta + "aerith_damage");
        imageAttack = setUp(carpeta + "aerith_attack");
        dead = setUp(carpeta + "aerith_dead");
    }

    @Override
    public void draw(Graphics2D g2) {

        super.draw(g2);
    }

    @Override
    public void update() {

        super.update();
    }

}
