package com.example;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.example.entity.enemy.Enemy;


public class BattlePanel {
   BufferedImage[] background = new BufferedImage[10];
   GamePanel gp;
   public HashMap<Integer,ArrayList<Enemy>> level;
   public int currentRound=0;
   public AnimationAttack animationAttack;
   public Iterator<Enemy> iterator;
   public BattlePanel(GamePanel gp){
    this.gp = gp;
    animationAttack = new AnimationAttack(gp);
    level = new HashMap<>();
    
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
        gp.turnHandler.turnCharacters();
        gp.turnHandler.gameTurn();

        if (gp.turnHandler.getCurrentTurn() != null) {
            System.out.println(gp.turnHandler.getCurrentTurn());
        }
        
       iterator = level.get(currentRound).iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update();
           
        }
        animationAttack.update();
    

    }

    public void draw(Graphics2D g2){
        g2.drawImage(background[1], -10, 0,gp.screenWidth+20, gp.screenHeight-gp.tileSize*3,null);
        gp.party.draw(g2);
        
        for (Enemy enemy : level.get(currentRound)) {
            enemy.draw(g2);
        }

        animationAttack.draw(g2);
    }

}
