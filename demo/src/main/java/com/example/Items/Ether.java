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
        value = 2;
        price = 100;

    }

    @Override
    public boolean useObject(Character e) {

        if (e.getMp() != e.getMaxMp()) {
            amount--;
            e.setMp(e.getMp() + value);
            if (amount == 0) {
                gp.party.getInventory().remove(this);
                return false;
            }

        }
        return true;
    }

}
