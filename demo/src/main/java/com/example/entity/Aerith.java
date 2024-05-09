package com.example.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.example.GamePanel;
import com.example.UtilityTool;

public class Aerith extends Character {
   
    GamePanel gp ;
    
    public Aerith(GamePanel gp){ 
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getPlayerImagen();
       
    }

     public BufferedImage setUp(String path){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            String imagePath = "/com/example/image/aerith/" + path + ".png";
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = tool.imageScale(image, sizeWidth, sizeHeight);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         return image;
    }

    public void getPlayerImagen(){
       
        left = setUp("aerith_left");
        left1 = setUp("aerith_left1");

        right = setUp("aerith_right");
        right1 = setUp("aerith_right1");

        up = setUp("aerith_up");
        up1 = setUp("aerith_up1");
        up2 = setUp("aerith_up2");

        down = setUp("aerith_down");
        down1 = setUp("aerith_down1");
        down2 = setUp("aerith_down2");
        portrait = setUp("aerithPortrait"); 
    }

}
