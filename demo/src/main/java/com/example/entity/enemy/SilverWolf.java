package com.example.entity.enemy;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class SilverWolf extends Enemy {
    public SilverWolf(GamePanel gp, int level, int maxHp, int gil, int exp, int x, int y) {
        super(gp, level, maxHp, gil, exp, x, y);
        sizeHeight = 50 * gp.scale;
        sizeWidth = 48 * gp.scale;

        setHp(maxHp);
        attack = 14;
        dexterity = 30 + level;
        defense = 100;
        magicDefense = 140;
        getEnemyImagen();
    }

    public void getEnemyImagen() {
        String carpeta = "/com/example/image/enemy/";
        right = setUp(carpeta + "silverWolf");
        right1 = setUp(carpeta + "silverWolf_1");
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
