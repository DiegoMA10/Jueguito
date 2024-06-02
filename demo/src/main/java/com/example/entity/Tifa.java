package com.example.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Tifa extends Character {

    public Tifa(GamePanel gp) {
        super(gp);
        baseMaxHp = 60;
        baseMaxMp = 9;
        RawStats(1);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID = 2;
        setIndexGroup(1);
        refreshPosition();
        getPlayerImagen();

    }

    public Tifa(GamePanel gp, int level, int exp, int partyIndex, int hp, int mp, boolean isAlive) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID = 2;
        baseMaxHp = 60;
        baseMaxMp = 9;
        this.isAlive = isAlive;
        setExp(exp);
        RawStats(level);
        setHp(hp);
        setMp(mp);
        setIndexGroup(partyIndex);
        refreshPosition();
        getPlayerImagen();

    }

    public void RawStats(int level) {
        setName("Tifa");
        setLevel(level);
        checkAbilities();
        setNextLevelExp(gp.dataBase.getExpForNextLevel(level));
        setMaxHp(gp.dataBase.getHpForLevel(level));
        setHp(30);
        setMaxMp(gp.dataBase.getMpForLevel(level));
        setMp(1);
        setStrength(45 + level - 1);
        setDexterity(43 + level - 1);
        setMagic(15 + level - 1);
        setAttack(32 + level - 1);
        setDefense(50 + level - 1);
        setMagicDefense(50 + level - 1);

    }

    public void getPlayerImagen() {
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta + "tifa_left");
        left1 = setUp(carpeta + "tifa_left1");

        right = setUp(carpeta + "tifa_right");
        right1 = setUp(carpeta + "tifa_right1");

        up = setUp(carpeta + "tifa_up");
        up1 = setUp(carpeta + "tifa_up1");
        up2 = setUp(carpeta + "tifa_up2");

        down = setUp(carpeta + "tifa_down");
        down1 = setUp(carpeta + "tifa_down1");
        down2 = setUp(carpeta + "tifa_down2");
        portrait = setUp(carpeta + "tifaPortrait");
        cast = setUp(carpeta + "tifa_cast");
        takeDamage = setUp(carpeta + "tifa_damage");
        imageAttack = setUp(carpeta + "tifa_attack");
        dead = setUp(carpeta + "tifa_dead");
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
