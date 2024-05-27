package com.example.entity;


import java.awt.Graphics2D;

import com.example.GamePanel;

public class Aerith extends Character {
   
  
    
    public Aerith(GamePanel gp){ 
        super(gp);
        RawStats(10);
        defaultX = (gp.screenWidth - gp.tileSize * 6) + (24 * indexGroup);
        defaultY = (gp.tileSize * 5 - 24) + ((gp.tileSize + 12) * indexGroup);
        x = defaultX;
        y = defaultY;
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        characterID=1;
        getPlayerImagen();
        
    }
    
    public Aerith(GamePanel gp,int level, int exp, int partyIndex, int hp, int mp) {
        super(gp);
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        characterID=1;
        setLevel(level);
        RawStats(level);
        setHp(hp);
        setIndexGroup(partyIndex);
        setExp(exp);
        setMp(mp);
        getPlayerImagen();
    }

    public void RawStats(int level){
        setName("Aerith");
        setLevel(1);
        setIndexGroup(0);
        setExp(0);
        setNextLevelExp(32);
        setMaxHp(40);
        setHp(40);
        setMaxMp(20);
        setMp(20);
        setStrength(25+level-1);
        setDexterity(30+level-1);
        setMagic(40+level-1);
        setAttack(15+level-1);
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
        cast = setUp(carpeta+"aerith_cast");
        takeDamage = setUp(carpeta+"aerith_damage");
        imageAttack = setUp(carpeta+"aerith_attack");
        dead = setUp(carpeta+"aerith_dead");
    }

    @Override
    public void draw(Graphics2D g2) {
       
        super.draw(g2);
    }

    @Override
    public void update() {
       
        super.update();
    }

}
