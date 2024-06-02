package com.example.spells;

import com.example.GamePanel;
import com.example.entity.Character;

public class Hielo extends Spell {

    public Hielo(GamePanel gp) {
        super(gp);
        name = "Hielo";
        SpellPower = 22;
        cost = 5;
        getImagen();
    }

    private void getImagen() {
        setUp(22, "hielo");
    }

    @Override
    public void checkEvolution(Character character) {
    
        if (character.getLevel() > 42) {
            name = "Hielo++";
            SpellPower = 82;
            cost = 52;
        } else if (character.getLevel() > 22) {
            name = "Hielo+";
            SpellPower = 42;
            cost = 21;
        }

    }
}
