package com.example.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.IntFunction;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.KeyHandler;
import com.example.UtilityTool;

public class PlayerLeader extends Entity {

    KeyHandler keyH;
    public int screenX;
    public int screenY;
    Group group;
    boolean action, actionX;
    int defaultX;
    public boolean salida = true;;
    int cont = 0;
    int spriteCont=0;
    
    

    public PlayerLeader(GamePanel gp, KeyHandler keyH, Group group) {
        super(gp);
        this.keyH = keyH;
        this.group = group;
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
       // defaultX = screenX;
        hitBox = new Rectangle();
        hitBox.x = 5;
        hitBox.y = 25;
        hitboxDefaultX = hitBox.x;
        hitboxDefaultY = hitBox.y;
        hitBox.width = 35;
        hitBox.height = 45;
        setDefaultValues();
        getPlayerImagen();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 41;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImagen() {
         
        left = group.getGroup().get(0).left;
        left1 = group.getGroup().get(0).left1;

        right = group.getGroup().get(0).right;
        right1 = group.getGroup().get(0).right1;

        up = group.getGroup().get(0).up;
        up1 = group.getGroup().get(0).up1;
        up2 = group.getGroup().get(0).up2;

        down = group.getGroup().get(0).down;
        down1 = group.getGroup().get(0).down1;
        down2 = group.getGroup().get(0).down2;
    }

    public void update() {

        if (keyH.upPressed == true) {
            direction = "up";

        } else if (keyH.downPressed == true) {
            direction = "down";

        } else if (keyH.leftPressed == true) {
            direction = "left";

        } else if (keyH.rightPressed == true) {
            direction = "right";

        }

      
       
        collisionOn = false;
        gp.ck.checkTile(this);
        int indexNPC = gp.ck.checkEntity(this, gp.npc);
        gp.eHandler.checkEvent();
        interactNPC(indexNPC);

        gp.keyH.enterPressed = false;
        if (collisionOn == false) {

            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

        }

        spritCount++;
        if (spritCount > 15) {

            if (spriteNumber == 1) {

                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spritCount = 0;
        }

    }



    public void interactNPC(int i) {
        if (i != 999) {
            if (keyH.enterPressed == true) {

                gp.npc[gp.currentMap][i].speak();

            }
        }
    }

    public void draw(Graphics2D g2) {

        /* 
          g2.setColor(Color.white);
          g2.fillRect(gp.tileSize, 0, gp.tileSize, gp.tileSize);
          */
        BufferedImage image = null;
        switch (direction) {
            case "left":

                if (keyH.leftPressed) {
                    if (spriteNumber == 1) {
                        image = left;
                    }
                    if (spriteNumber == 2) {
                        image = left1;
                    }
                } else {
                    image = left;
                }

                break;

            case "right":

                if (keyH.rightPressed) {
                    if (spriteNumber == 1) {
                        image = right;
                    }
                    if (spriteNumber == 2) {
                        image = right1;
                    }
                } else {
                    image = right;
                }

                break;
            case "up":

                if (keyH.upPressed) {
                    if (spriteNumber == 1) {
                        image = up1;
                    }
                    if (spriteNumber == 2) {
                        image = up2;
                    }
                } else {
                    image = up;
                }

                break;
            case "down":

                if (keyH.downPressed) {
                    if (spriteNumber == 1) {
                        image = down1;
                    }
                    if (spriteNumber == 2) {
                        image = down2;
                    }
                } else {
                    image = down;
                }

                break;
        }

        int x = screenX;
        int y = screenY;

        if (screenX>worldX) {
            x = worldX;
        }
        if (screenY>worldY) {
            y = worldY;
        }
        int rightX = gp.screenWidth - screenX;
        if (rightX>gp.worldWidth-worldX) {
            x = gp.screenWidth - (gp.worldWidth - worldX);
        }
        int bottomY = gp.screenHeight - screenY;
        if (bottomY>gp.worldHeight-gp.player.worldY) {

            y = gp.screenHeight - (gp.worldHeight - worldY);
        }
      
        g2.drawImage(image, x, y, sizeWidth, sizeHeight, null);
        g2.setColor(Color.red);
        g2.drawRect(x + hitBox.x, y + hitBox.y, hitBox.width, hitBox.height);
        cont++;
        

             
        
        
        

    }

    /*
     * public void pruebas(){
     * 
     * if (action) {
     * 
     * if (actionX) {
     * screenX-=3;
     * direction="left";
     * if (screenX<gp.tileSize*2) {
     * actionX=false;
     * direction="right";
     * }
     * 
     * }else{
     * screenX+=3;
     * direction="right";
     * if (defaultX<screenX) {
     * action=false;
     * direction ="left";
     * }
     * }
     * 
     * 
     * }
     * }
     */

}
