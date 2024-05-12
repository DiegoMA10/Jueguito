package com.example.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Entity {
    public GamePanel gp;
    public int sizeWidth,sizeHeight;
    public int worldX,worldY;
    public int speed;
    public BufferedImage left ,left1,left2,right,right1,right2,up,up1,up2,down,down1,down2;
    public String direction;
    public int spritCount = 0;
    public int spriteNumber = 1;
    public Rectangle hitBox;
    public int hitboxDefaultX,hitboxDefaultY;
    public boolean collisionOn = false;
    String dialogues[]= new String[10];
    int dialogueI=0;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

     public BufferedImage setUp(String path){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path+".png"));
            image = tool.imageScale(image, sizeWidth, sizeHeight);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         return image;
    }

    public void update(){
        
    }

    public void speak(){
        if (dialogues[dialogueI]==null) {
            dialogueI=0;
        }
        gp.ui.currentDialogue = dialogues[dialogueI];
        dialogueI++;
    }
}
