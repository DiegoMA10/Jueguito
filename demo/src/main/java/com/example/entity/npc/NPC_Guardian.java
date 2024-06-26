package com.example.entity.npc;

import java.awt.Graphics2D;

import com.example.GamePanel;
import com.example.entity.Entity;

public class NPC_Guardian extends Entity {

    public NPC_Guardian(GamePanel gp) {
        super(gp);
        direction = "down";
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getImagen();
        setDialogue();
    }

    public void getImagen() {
        String carpeta = "/com/example/image/npc/";

        left = setUp(carpeta + "guardian_left");
        right = setUp(carpeta + "guardian_right");
        up = setUp(carpeta + "guardian_up");
        down = setUp(carpeta + "guardian_down");

    }

    public void setDialogue(){
        dialogues[0] = "Hola aventurero te aperece enfrentarte \na mis pruebas";
      
    }

    public void draw(Graphics2D g2){

        super.draw(g2);
        
    }

    public void speak(){
        super.speak();
        gp.ui.gameStateTransition=GamePanel.dialogueToBattleState;
        gp.gameState = GamePanel.dialogueToBattleState;
       
    }

}
