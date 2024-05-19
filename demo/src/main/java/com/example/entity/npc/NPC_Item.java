package com.example.entity.npc;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.example.GamePanel;
import com.example.Items.Item;
import com.example.entity.Entity;

public class NPC_Item extends Entity {

    public NPC_Item(GamePanel gp) {
        super(gp);
        direction = "down";
        sizeWidth = 15 * gp.scale;
        sizeHeight = 23 * gp.scale;
        getImagen();
        setDialogue();
    }

    ArrayList<Item> inventory = new ArrayList<>();

    public void getImagen() {
        String carpeta = "/com/example/image/npc/";

        right = setUp(carpeta + "item_merchant_right");
        down = setUp(carpeta + "item_merchant_down");

    }

    public void setDialogue() {

    }

    public void draw(Graphics2D g2) {

        super.draw(g2);

    }

    public void speak() {
        super.speak();
        gp.ui.gameStateTransition = GamePanel.tradeState;
        gp.ui.itemNpc = this;

    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

}
