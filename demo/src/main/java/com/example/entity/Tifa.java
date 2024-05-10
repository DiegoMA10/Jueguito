package com.example.entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;

public class Tifa extends Character{
  
    
    public Tifa(GamePanel gp){
        super(gp);
         sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getPlayerImagen();
       
    }

    
    public void getPlayerImagen(){
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta+"tifa_left");
        left1 = setUp(carpeta+"tifa_left1");

        right = setUp(carpeta+"tifa_right");
        right1 = setUp(carpeta+"tifa_right1");

        up = setUp(carpeta+"tifa_up");
        up1 = setUp(carpeta+"tifa_up1");
        up2 = setUp(carpeta+"tifa_up2");

        down = setUp(carpeta+"tifa_down");
        down1 = setUp(carpeta+"tifa_down1");
        down2 = setUp(carpeta+"tifa_down2");
        portrait = setUp(carpeta+"tifaPortrait"); 
    }
}
