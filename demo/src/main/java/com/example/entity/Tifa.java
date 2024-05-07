package com.example.entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Tifa extends Entity{
    GamePanel gp ;
    
     public BufferedImage setUp(String path){
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/com/example/image/tifa/"+path+".png"));
            image = tool.imageScale(image, sizeWidth, sizeHeight);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
         return image;
    }

    public void getPlayerImagen(){
       
        left = setUp("tifa_left");
        left1 = setUp("tifa_left1");

        right = setUp("tifa_right");
        right1 = setUp("tifa_right1");

        up = setUp("tifa_up");
        up1 = setUp("tifa_up1");
        up2 = setUp("tifa_up2");

        down = setUp("tifa_down");
        down1 = setUp("tifa_down1");
        down2 = setUp("tifa_down2");
    }
}
