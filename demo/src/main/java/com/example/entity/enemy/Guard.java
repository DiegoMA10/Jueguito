package com.example.entity.enemy;

import java.awt.Graphics2D;
import java.util.Random;

import com.example.GamePanel;

public class Guard extends Enemy {

    public Guard(GamePanel gp, int level, int maxHp, int gil, int exp, int x, int y) {
        super(gp, level, maxHp, gil, exp, x, y);
        sizeHeight = 48 * gp.scale;
        sizeWidth = 32 * gp.scale;
        setHp(maxHp);
        dexterity = 25 + level;
        attack = 16;
        defense = 100;
        magicDefense = 140;
        getEnemyImagen();
    }

    public void getEnemyImagen() {
        String carpeta = "/com/example/image/enemy/";
        right = setUp(carpeta + "guard");
        right1 = setUp(carpeta + "guard_1");
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
