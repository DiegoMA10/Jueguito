package com.example.entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import com.example.GamePanel;
import com.example.entity.enemy.Enemy;

public class ATB {
    GamePanel gp;
    Enemy enemy;
    Character character;
    int value = 0;
    int maxValue = 65536;
    public boolean full;

    public ATB(GamePanel gp, Character character) {
        this.gp = gp;
        this.character = character;
    }

    public ATB(GamePanel gp, Enemy enemy) {
        this.gp = gp;
        this.enemy = enemy;
    }

    public void update() {
        incrementarATB();
    }

    public void incrementarATB() {
        if (character != null) {
            int incremento = (96 * (character.getDexterity() + 20)) / 16;
            setValue(getValue() + incremento / 3);
        }

        if (enemy != null) {
            int incremento = (96 * (enemy.getDexterity() + 20)) / 16;
            setValue(getValue() + incremento / 3);
        }

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value >= maxValue) {
            value = maxValue;
            if (character!=null) {
                 if (!full) {
                gp.turnHandler.addCharacterQueue(character);
            }
            }

            if (enemy!=null) {
                if (!full) {
               gp.turnHandler.addGameQueue(enemy);
               
           }
           }
            full = true;
        }
        this.value = value;
    }

    public void resetATB(){
        full = false;
        value = 0;
    }

    public void draw(Graphics2D g2, int x, int y) {
        g2.setColor(Color.lightGray);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(x, y, 150, 13, 35, 20);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, 150, 15, 35, 20);
        if (value == maxValue) {
            g2.setColor(Color.orange);
            g2.fillRect(x + 5, y + 4, progressbarr(), 6);
        } else {
            g2.fillRect(x + 5, y + 3, progressbarr(), 8);
        }

    }

    public int progressbarr() {
        double percentageFilled = (double) value / maxValue;
        int barWidth = (int) (percentageFilled * 140);
        return barWidth;
    }
}
