package com.example.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import com.example.GamePanel;
import com.example.UtilityTool;

public class Cloud extends Character {
  
  
    
    public Cloud(GamePanel gp){
        super(gp);
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getPlayerImagen();
        RawStats();
    }

    public void RawStats(){
        setName("Cloud");
        setIndexGroup(2);
        setLevel(1);
        setExp(0);
        setNextLevelExp(32);
        setMaxHp(50);
        setHp(20);
        setMaxMp(14);
        setMp(2);
        setStrength(35);
        setDexterity(35);
        setMagic(30);
        setAttack(20);
        setDefense(80);
        setMagicDefense(80);
       
    }

    
    

    public void getPlayerImagen(){
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta+"cloud_left");
        left1 = setUp(carpeta+"cloud_left1");

        right = setUp(carpeta+"cloud_right");
        right1 = setUp(carpeta+"cloud_right1");

        up = setUp(carpeta+"cloud_up");
        up1 = setUp(carpeta+"cloud_up1");
        up2 = setUp(carpeta+"cloud_up2");

        down = setUp(carpeta+"cloud_down");
        down1 = setUp(carpeta+"cloud_down1");
        down2 = setUp(carpeta+"cloud_down2");
        portrait = setUp(carpeta+"cloudPortrait"); 
    }

}

