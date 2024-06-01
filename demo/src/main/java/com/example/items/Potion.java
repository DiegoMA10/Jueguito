package com.example.items;

import com.example.BattlePanel;
import com.example.GamePanel;
import com.example.entity.Character;
import com.example.entity.Entity;

import javafx.scene.paint.Color;

public class Potion extends Item {

    public Potion(GamePanel gp) {
        super(gp);

        idItem = 1;
        name = "Potion";
        description = "Recupera 150 PV";
        value = 150;
        price = 50;
        getImagen();
    }

    public void getImagen() {
        setUp(15, "potion");
    }

    @Override
    public boolean useObject(Entity entity) {
        Character e = (Character) entity;
        if (e.getHp() < e.getMaxHp() || gp.gameState == GamePanel.battleState) {

            amount--;
            if (e.getIsAlive()) {
                e.setHp(e.getHp() + value);

                if (amount == 0) {
                    gp.party.getInventory().remove(this);
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;
    }
}
