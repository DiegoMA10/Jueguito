package com.example.Items;

import com.example.GamePanel;
import com.example.entity.Character;
import com.example.entity.Entity;

public class Ether extends Item {

    public Ether(GamePanel gp) {
        super(gp);
        idItem = 2;
        name = "Ether";
        description = "Recupera 20 MP";
        value = 20;
        price = 100;
        getImagen();
    }

    public void getImagen() {
        setUp(14, "ether");
    }

    @Override
    public boolean useObject(Entity entity) {
        Character e = (Character) entity;

        if (e.getMp() != e.getMaxMp() || gp.gameState == GamePanel.battleState) {
            amount--;
            if (e.getIsAlive()) {
                e.setMp(e.getMp() + value);
                if (amount == 0) {
                    gp.party.getInventory().remove(this);
                    return false;
                }
            }

        }
        return true;
    }

}
