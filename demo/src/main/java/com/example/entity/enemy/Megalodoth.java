package com.example.entity.enemy;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class Megalodoth extends Enemy {

    public Megalodoth(GamePanel gp, int level, int maxHp, int gil, int exp, int x, int y) {
        super(gp, level, maxHp, gil, exp, x, y);
        sizeHeight = 48 * gp.scale;
        sizeWidth = 63 * gp.scale;
        dexterity = 20 + level;
        setHp(maxHp);
        attack = 20;
        defense = 100;
        magicDefense = 140;
        getEnemyImagen();
    }

    public void getEnemyImagen() {
        String carpeta = "/com/example/image/enemy/";
        right = setUp(carpeta + "megalodoth");
        right1 = setUp(carpeta + "megalodoth_1");
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
