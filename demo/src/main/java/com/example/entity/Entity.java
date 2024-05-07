package com.example.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int sizeWidth,sizeHeight;
    public int worldX,worldY;
    public int speed;
    public BufferedImage left ,left1,left2,right,right1,right2,up,up1,up2,down,down1,down2;
    public String direction;
    public int spritCount = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
