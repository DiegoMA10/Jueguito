package com.example.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.example.GamePanel;
import com.example.UtilityTool;

public class Cloud extends Character {
  
    GamePanel gp ;
    
    public Cloud(GamePanel gp){
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getPlayerImagen();
       
    }

     public BufferedImage setUp(String path){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/com/example/image/cloud/"+path+".png"));
            image = tool.imageScale(image, sizeWidth, sizeHeight);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         return image;
    }

    public void getPlayerImagen(){
       
        left = setUp("cloud_left");
        left1 = setUp("cloud_left1");

        right = setUp("cloud_right");
        right1 = setUp("cloud_right1");

        up = setUp("cloud_up");
        up1 = setUp("cloud_up1");
        up2 = setUp("cloud_up2");

        down = setUp("cloud_down");
        down1 = setUp("cloud_down1");
        down2 = setUp("cloud_down2");
        portrait = setUp("cloudPortrait"); 
    }

}

