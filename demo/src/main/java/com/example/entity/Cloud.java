package com.example.entity;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import com.example.GamePanel;
import com.example.UtilityTool;

public class Cloud extends Character {

    public Cloud(GamePanel gp) {
        super(gp);
        baseMaxHp = 50;
        baseMaxMp = 14;
        RawStats(1);
        characterID = 3;
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        refreshPosition();
        getPlayerImagen();

    }

    public Cloud(GamePanel gp, int level, int exp, int partyIndex, int hp, int mp) {
        super(gp);
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        characterID = 3;
        baseMaxHp = 50;
        baseMaxMp = 14;
        setExp(exp);
        setLevel(level);
        RawStats(level);
        setIndexGroup(partyIndex);
        setHp(hp);
        setMp(mp);
        refreshPosition();
        getPlayerImagen();
    }

    public void RawStats(int level) {
        setName("Cloud");
        setIndexGroup(2);
        setLevel(level);
        setNextLevelExp(gp.dataBase.getExpForNextLevel(level));
        setMaxHp(gp.dataBase.getHpForLevel(level));
        setHp(getMaxHp());
        setMaxMp(gp.dataBase.getMpForLevel(level));
        setMp(getMaxMp());
        setStrength(35 + level - 1);
        setDexterity(35 + level - 1);
        setMagic(30 + level - 1);
        setAttack(20 + level - 1);
        setDefense(80 + level - 1);
        setMagicDefense(80 + level - 1);

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
        cast = setUp(carpeta + "cloud_cast");
        takeDamage = setUp(carpeta + "cloud_damage");
        imageAttack = setUp(carpeta + "cloud_attack");
        dead = setUp(carpeta + "cloud_dead");
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
