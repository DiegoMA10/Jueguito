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
        setName("Aerith");
        setLevel(1);
        setIndexGroup(0);
        setExp(0);
        setNextLevelExp(32);
        setMaxHp(40);
        setHp(20);
        setMaxMp(20);
        setMp(2);
        setStrength(25);
        setDexterity(30);
        setMagic(40);
        setAttack(15);
        setDefense(80);
        setMagicDefense(80);
       
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
