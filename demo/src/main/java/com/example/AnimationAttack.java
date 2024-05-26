package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.example.entity.Character;
import com.example.entity.Entity;

public class AnimationAttack {
    GamePanel gp;
    BufferedImage[]animation=null;
    int spriteCont=0;
    int cont=0;
    boolean status;
    Entity target;
    

    public AnimationAttack(GamePanel gp) {
        this.gp = gp;
       
    }

    public void setAnimation( BufferedImage[]animation,Entity e){
        this.animation=animation;
        this.target = e;
    }

    public void update(){
        if (animation!=null) {
            spriteCont++;
        if (spriteCont>animation.length-1) {
            spriteCont=0;
            animation=null;
        }
        }
        
    }

    public void draw(Graphics2D g2){

        if (animation!=null) {
            if (target instanceof Character) {
                Character character = (Character) target;
                g2.drawImage(animation[spriteCont], character.x-gp.tileSize,character.y-gp.tileSize,animation[spriteCont].getWidth(),animation[spriteCont].getHeight(),null);
            }else{
                g2.drawImage(animation[spriteCont], target.defaultX,target.defaultY,animation[spriteCont].getWidth(),animation[spriteCont].getHeight(),null);
            }
           
        }
    }

    

    
    
}
