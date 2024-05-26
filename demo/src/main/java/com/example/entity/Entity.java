package com.example.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;
 /*
     * formula
     * attack2 = attack + strengh2
     * Damage = Attack +((Level*Level*Attack2)(256))*3/2
     * 
     * Defensa Formula
     * 
     * DamageFinal = (Damage*(255-Defense)/256)+1
     * 
     * (Magia) = SpellPower*4+(Level*Magic*SpellPower/32)
     * 
     * (Monsters) = Level*Level*(Attack*4+Strenght)/256
     * 
     * 
     */
import com.example.tile.AnimatedText;

public class Entity implements Comparable<Entity> {
    protected ArrayList<AnimatedText> animatedTexts = new ArrayList<>();
    protected GamePanel gp;
    public int sizeWidth;
    public int sizeHeight;
    public int worldX, worldY;
    public int speed;
    public int defaultX;
    public int defaultY;
    public BufferedImage left, left1, left2, right, right1, right2, up, up1, up2, down, down1, down2;
    public String direction;
    protected int spritCount = 0;
    protected int spriteNumber = 1;
    public Rectangle hitBox = new Rectangle(0, 20, 40, 45);
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    protected String dialogues[] = new String[10];
    int dialogueI = 0;
    protected Boolean deadState = true;


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

            int x = screenX;
            int y = screenY;

            if (screenX > worldX) {
                x = worldX;
            }
            if (screenY > worldY) {

                y = worldY;
            }
            int rightX = gp.screenWidth - screenX;
            if (rightX > gp.worldWidth - worldX) {
                x = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomY = gp.screenHeight - screenY;
            if (bottomY > gp.worldHeight - worldY) {

                y = gp.screenHeight - (gp.worldHeight - worldY);
            }

            g2.drawImage(image, x, y, sizeWidth, sizeHeight, null);

        }

    }

    public void speak() {
       

        if (dialogues[0] != null) {
            gp.gameState = GamePanel.dialogueState;
        }
        
        if (dialogues[dialogueI] == null) {
            dialogueI = 0;
        }

        gp.ui.currentDialogue = dialogues[dialogueI];
        dialogueI++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }

    }

    public Boolean getDeadState() {
        return deadState;
    }


    public int compareTo(Entity e1) {

        return Integer.compare(this.worldY, e1.worldY);
    }

}
