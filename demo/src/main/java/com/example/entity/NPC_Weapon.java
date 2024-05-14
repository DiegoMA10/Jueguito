package com.example.entity;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class NPC_Weapon extends Entity {

   public NPC_Weapon(GamePanel gp) {
        super(gp);
        direction = "down";
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getImagen();
        setDialogue();
    }

    public void getImagen() {
        String carpeta = "/com/example/image/npc/";

      
        down = setUp(carpeta + "weapon_merchant");

    }

    public void setDialogue(){
        dialogues[0] = "Hola que tal estamos";
        dialogues[1] = "Que buen tiempo hace no?";
        dialogues[2] = "jeje\ngoz";
    }

    public void draw(Graphics2D g2){

        super.draw(g2);
        
    }

    public void speak(){
        super.speak();
       
    }

}
