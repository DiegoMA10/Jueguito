package com.example.entity.npc;

import java.awt.Graphics2D;

import com.example.GamePanel;
import com.example.entity.Entity;

public class NPC_Inkeeper extends Entity{

    public NPC_Inkeeper(GamePanel gp) {
        super(gp);
        direction = "down";
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getImagen();
        setDialogue();
    }

    public void getImagen() {
        String carpeta = "/com/example/image/npc/";

        left = setUp(carpeta+ "inkeeper_left");
        down = setUp(carpeta + "inkeeper_down");

    }

    public void setDialogue(){
        dialogues[0] = "Buenas aventurero\nte apertece descansar ?";
      
    }

    public void draw(Graphics2D g2){

        super.draw(g2);
        
    }

    public void speak(){
        super.speak();
       
        gp.gameState = GamePanel.breakState;
       
    }
}
