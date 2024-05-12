package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Graphics2D g2 ;
    double playTimer;
    BufferedImage cursor;
    int subState=0;
    int numCommand=0;
    Font arial40;
    public String currentDialogue;

    

    public UI(GamePanel gp){
        this.gp = gp;
        getCursorImagen();
        arial40 = new Font("Arial",Font.PLAIN,40);
    }

    public BufferedImage setUp(String path){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("image/UI/"+path+".png"));
            image = tool.imageScale(image, gp.tileSize, gp.tileSize);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         return image;
    }

    public void getCursorImagen(){
        cursor = setUp("cursor");
    }

 public void draw(Graphics2D g2){
    this.g2 = g2;
    playTimer+= 1.0/60; 
    g2.setFont(arial40);
    
  switch (gp.gameState) {
    case GamePanel.titleState:break;
    case GamePanel.menuState:drawMenuWindows();break;
    case GamePanel.playState:break;
    case GamePanel.battleState:drawBattleMenu();break;
   
       
  }
  

    
    
   
  

 }

    public void drawMenuWindows(){
        int windowsX=5;
        int windowsY=5;
        int width = gp.screenWidth-10;
        int height = gp.screenHeight-10;
        BufferedImage image = null;
        image = cursor;
        drawSubwindows(windowsX, windowsY, width, height);
        windowsX+=gp.tileSize*2;
        windowsY+=gp.tileSize;
        g2.drawImage(gp.grupo.getGroup().get(0).portrait, windowsX, windowsY ,gp.tileSize*2,gp.tileSize*2,null);

        windowsY+=gp.tileSize*3;
        g2.drawImage(gp.grupo.getGroup().get(1).portrait, windowsX, windowsY ,gp.tileSize*2,gp.tileSize*2,null);

        windowsY+=gp.tileSize*3;
        g2.drawImage(gp.grupo.getGroup().get(2).portrait, windowsX, windowsY ,gp.tileSize*2,gp.tileSize*2,null);
        switch (subState) {
            case 0:menuSelection();break;
            default:break;
        }
    }

    public void drawBattleMenu(){
        int windowsX=5;
        int windowsY=gp.screenHeight-gp.tileSize*3-5;
        int width = gp.screenWidth-10;
        int height = gp.tileSize*3; 
     
        BufferedImage image = null;
        image = cursor;
        drawSubwindows(windowsX, windowsY, width, height);
        
       
    }


    public void menuSelection(){
        int x = gp.screenWidth-(gp.tileSize*4+5);
        int y = 5;
        int width = gp.tileSize*4;
        int height = gp.tileSize*6+16;
        drawSubwindows(x, y, width, height);
        x+=gp.tileSize-24;
        y+=gp.tileSize;
        g2.setColor(Color.white);
        g2.drawString("Items", x, y);
        if (numCommand == 0) {
            
            g2.drawImage(cursor,x-gp.tileSize,y-gp.tileSize+10,gp.tileSize,gp.tileSize,null);
        }

        y+=gp.tileSize;

       
        g2.drawString("Config", x, y);

        if (numCommand == 1) {
            
            g2.drawImage(cursor,x-gp.tileSize,y-gp.tileSize+10,gp.tileSize,gp.tileSize,null);
        }
        
       
    }

    public void drawSubwindows(int x , int y , int width , int height){
            int gradientStartY = y;
            int gradientEndY = y + height;
           GradientPaint color = new GradientPaint(x,gradientStartY, new Color(100,99,171,255), x,gradientEndY, new Color(0,0,90,255),false);
           g2.setPaint(color);
           g2.fillRoundRect(x,y,width,height, 35, 35);
           g2.setColor(Color.LIGHT_GRAY);
           g2.setStroke(new BasicStroke(5));
           g2.drawRoundRect(x,y,width,height, 20, 20);
           
    }
}
