package com.example.entity;


import com.example.GamePanel;

public class Aerith extends Character {
   
  
    
    public Aerith(GamePanel gp){ 
        super(gp);
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getPlayerImagen();
        RawStats();
    }
    
    public void RawStats(){
        name = "Aerith";
        level = 1;
        indexGroup=0;
        exp = 0;
        nextLevelExp=32;
        MaxHp = 40;
        hp = 20;
        MaxMp = 20;
        mp = 2;
        strength = 25;
        dexterity = 30;
        magic = 40;
        attack = 15;
        defense = 80;
        magicDefense=80;
       
    }
    
    public void getPlayerImagen(){
        String carpeta = "/com/example/image/characters/";
        left = setUp(carpeta+"aerith_left");
        left1 = setUp(carpeta+"aerith_left1");

        right = setUp(carpeta+"aerith_right");
        right1 = setUp(carpeta+"aerith_right1");

        up = setUp(carpeta+"aerith_up");
        up1 = setUp(carpeta+"aerith_up1");
        up2 = setUp(carpeta+"aerith_up2");

        down = setUp(carpeta+"aerith_down");
        down1 = setUp(carpeta+"aerith_down1");
        down2 = setUp(carpeta+"aerith_down2");
        portrait = setUp(carpeta+"aerithPortrait"); 
    }

}
