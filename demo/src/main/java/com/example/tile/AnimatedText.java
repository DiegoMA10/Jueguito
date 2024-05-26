package com.example.tile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class AnimatedText {
    private String text;
    private int x;
    private int y;
    private Color color;
    private Font font;
    private int velocityY;
    private int lifeTime;

    public AnimatedText(String text, int x, int y, Color color, Font font, int velocityY, int lifeTime) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.font = font;
        this.velocityY = velocityY;
        this.lifeTime = lifeTime;
    }

    public boolean update() {
        // Movimiento vertical
        y -= velocityY;
        // Reducción del tiempo de vida
        lifeTime--;
        // Retorna true si el texto aún debe ser mostrado
        return lifeTime > 0;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);
        g2.setColor(color);
        g2.drawString(text, x, y);
    }
}
