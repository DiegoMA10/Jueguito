package com.example.entity.npc;

import java.awt.Graphics2D;

import com.example.GamePanel;
import com.example.entity.Entity;

public class NPC_Item extends Entity {

    public NPC_Item(GamePanel gp) {
        super(gp);
        direction = "down";
        sizeWidth = 15*gp.scale;
        sizeHeight = 23*gp.scale;
        getImagen();
        setDialogue();
    }

    public void getImagen() {
        String carpeta = "/com/example/image/npc/";

        right = setUp(carpeta+ "item_merchant_right");
        down = setUp(carpeta + "item_merchant_down");

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
        gp.gameState = GamePanel.tradeState;
    }
}
