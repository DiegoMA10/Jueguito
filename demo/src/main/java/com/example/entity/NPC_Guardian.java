package com.example.entity;

import java.awt.Graphics2D;

import com.example.GamePanel;

public class NPC_Guardian extends Entity {

    public NPC_Guardian(GamePanel gp) {
        super(gp);
        direction = "left";
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
