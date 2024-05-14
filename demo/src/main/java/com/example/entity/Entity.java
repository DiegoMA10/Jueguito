package com.example.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Entity implements Comparable <Entity>{
    public GamePanel gp;
    public int sizeWidth, sizeHeight;
    public int worldX, worldY;
    public int speed;
    public BufferedImage left, left1, left2, right, right1, right2, up, up1, up2, down, down1, down2;
    public String direction;
    public int spritCount = 0;
    public int spriteNumber = 1;
    public Rectangle hitBox = new Rectangle(0, 20, 40, 45);
    public int hitboxDefaultX,hitboxDefaultY;
    public boolean collisionOn = false;
    String dialogues[] = new String[10];
    int dialogueI = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
        hitboxDefaultX = hitBox.x;
        hitboxDefaultY = hitBox.y;
    }

    public BufferedImage setUp(String path) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path + ".png"));
            image = tool.imageScale(image, sizeWidth, sizeHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if ((worldX + gp.tileSize > gp.player.worldX - gp.player.screenX) &&
            (worldX - gp.tileSize < gp.player.worldX + gp.player.screenX) &&
            (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY) &&
            (worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)) {
            switch (direction) {
                case "left":
                    image = left;
                    break;
                case "right":
                    image = right;
                    break;
                case "up":
                    image = up;
                    break;
                case "down":
                    image = down;
                    break;
            }

            g2.drawImage(image, screenX, screenY, sizeWidth, sizeHeight, null);

        }

    }

    public void speak() {
        gp.gameState = GamePanel.dialogueState;
       
        if (dialogues[dialogueI] == null) {
            dialogueI = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueI];
        dialogueI++;

        switch (gp.player.direction) {
            case "up":direction = "down";break;
            case "down":direction = "up";break;
            case "left":direction = "right";break;
            case "right":direction = "left";break;
        }
        
    }

    @Override
    public int compareTo(Entity e1) {
       
        return Integer.compare(this.worldY, e1.worldY);
    }

    
}
