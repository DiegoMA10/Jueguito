package com.example.entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.example.GamePanel;
import com.example.UtilityTool;

public class Cloud extends Character {

    public Cloud(GamePanel gp) {
        super(gp);
        RawStats(10);
        defaultX = (gp.screenWidth - gp.tileSize * 6) + (24 * indexGroup);
        defaultY = (gp.tileSize * 5 - 24) + ((gp.tileSize + 12) * indexGroup);
        x = defaultX;
        y = defaultY;
        characterID = 3;
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        getPlayerImagen();
     

    }

    public Cloud(GamePanel gp, int level, int exp, int partyIndex, int hp, int mp) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID = 3;
        setLevel(level);
        RawStats(level);
        setHp(hp);
        setIndexGroup(partyIndex);
        setExp(exp);
        setMp(mp);
        getPlayerImagen();
    }

    public void RawStats(int level) {
        setName("Cloud");
        setIndexGroup(2);
        setLevel(1);
        setExp(0);
        setNextLevelExp(32);
        setMaxHp(50);
        setHp(50);
        setMaxMp(14);
        setMp(14);
        setStrength(35 + level - 1);
        setDexterity(35 + level - 1);
        setMagic(30 + level - 1);
        setAttack(20 + level - 1);
        setDefense(80);
        setMagicDefense(80);

    }

    public void getPlayerImagen() {
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta + "cloud_left");
        left1 = setUp(carpeta + "cloud_left1");

        right = setUp(carpeta + "cloud_right");
        right1 = setUp(carpeta + "cloud_right1");

        up = setUp(carpeta + "cloud_up");
        up1 = setUp(carpeta + "cloud_up1");
        up2 = setUp(carpeta + "cloud_up2");

        down = setUp(carpeta + "cloud_down");
        down1 = setUp(carpeta + "cloud_down1");
        down2 = setUp(carpeta + "cloud_down2");
        portrait = setUp(carpeta + "cloudPortrait");
        cast = setUp(carpeta+"cloud_cast");
        takeDamage = setUp(carpeta+"cloud_damage");
        imageAttack = setUp(carpeta+"cloud_attack");
        dead = setUp(carpeta+"cloud_dead");
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
