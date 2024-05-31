package com.example.spells;

import com.example.GamePanel;

public class Electro extends Spell {

    public Electro(GamePanel gp) {
        super(gp);
        name = "Electro";
        SpellPower = 21;
        cost = 6;
        getImagen();
    }

    private void getImagen(){
        setUp(19, "electro");
    }
    
}
