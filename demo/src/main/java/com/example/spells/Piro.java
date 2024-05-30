package com.example.spells;

import com.example.GamePanel;

public class Piro extends Spell {

    public Piro(GamePanel gp) {
        super(gp);
        name = "Piro";
        SpellPower = 21;
        cost = 4;
        getImagen();
    }
    
    
    private void getImagen(){
        setUp(4, "piro");
    }

}
