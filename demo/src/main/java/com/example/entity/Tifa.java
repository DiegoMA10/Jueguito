package com.example.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Tifa extends Character {

    public Tifa(GamePanel gp) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID=2;
        getPlayerImagen();
        RawStats(1);

    }

    public Tifa(GamePanel gp, int level, int exp, int partyIndex, int hp, int mp) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID=2;
        setLevel(level);
        RawStats(level);
        setHp(hp);
        setIndexGroup(partyIndex);
        setExp(exp);
        setMp(mp);
        getPlayerImagen();
    }

    public void RawStats(int level) {
        setName("Tifa");
        setLevel(1);
        setIndexGroup(1);
        setExp(0);
        setNextLevelExp(32);
        setMaxHp(60);
        setHp(20);
        setMaxMp(9);
        setMp(2);
        setStrength(40+level-1);
        setDexterity(40+level-1);
        setMagic(25+level-1);
        setAttack(25+level-1);
        setDefense(80);
        setMagicDefense(80);

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
