package com.example;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class BattlePanel {
   BufferedImage[] background = new BufferedImage[10];
   GamePanel gp;
   //public Entity[][] moustros = new Entity[2][4];

   public BattlePanel(GamePanel gp){
    this.gp = gp;
    loadBackground();
   }

    public void setUp(int i, String path) {
        UtilityTool tool = new UtilityTool();

        try { 
            
            background[i]= ImageIO.read(getClass().getResourceAsStream("/com/example/image/backgrounds/"+path+".png"));
            background[i]= tool.imageScale(background[i], gp.screenWidth+10, gp.screenHeight+10);
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void loadBackground(){
        setUp(0, "background_1");
        setUp(1, "background_2");

    }

    public void update(){
        gp.party.update();

    }

    public void draw(Graphics2D g2){
        g2.drawImage(background[1], -10, 0,gp.screenWidth+20, gp.screenHeight-gp.tileSize*3,null);
        gp.party.draw(g2);
    }

}
