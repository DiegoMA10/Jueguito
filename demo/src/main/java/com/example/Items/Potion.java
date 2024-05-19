package com.example.Items;

import com.example.BattlePanel;
import com.example.GamePanel;
import com.example.entity.Character;
import com.example.entity.Entity;

public class Potion extends Item {

    public Potion(GamePanel gp) {
        super(gp);
        name = "Potion";
        description = "Recupera 150 PV";
        value = 4;
        price = 50;
    }

    @Override
    public boolean useObject(Character e) {
        if (e.getHp() != e.getMaxHp()) {
            amount--;
            e.setHp(e.getHp() + value);
            if (amount == 0) {
                gp.group.getInventory().remove(this);
                return false;
            }
        }
        return true;
    }
}
