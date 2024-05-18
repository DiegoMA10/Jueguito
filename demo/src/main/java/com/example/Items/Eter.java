package com.example.object;

import com.example.GamePanel;
import com.example.entity.Character;
import com.example.entity.Entity;

public class Eter extends Object{

    public Eter(GamePanel gp) {
    super(gp);
     name ="Eter";
     description="Recupera 20 MP";
     value=2;
    }

    @Override
    public boolean useObject(Character e) {

        if (e.getMp() != e.getMaxMp()) {
            amount--;
            e.setMp(e.getMp() + value);
            if (amount == 0) {
                gp.group.getInventory().remove(this);
                return  true;
            }
            
        }
        return false;
    }

  

    
    
}


