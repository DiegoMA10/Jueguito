package com.example.entity.enemy;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class Guard extends Enemy {

    public Guard(GamePanel gp, int level, int gil, int exp, int x, int y) {
        super(gp, level, exp, gil, x, y);
        sizeHeight = 48 * gp.scale;
        sizeWidth = 32 * gp.scale;
        dexterity = 50;
        MaxHp = 40;
        hp = 40;
        attack = 20;
        strength = 10;
        defense = 80;
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
