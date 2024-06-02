package com.example.spells;

import com.example.GamePanel;
import com.example.entity.Character;

public class Piro extends Spell {

    public Piro(GamePanel gp) {
        super(gp);
        name = "Piro";
        SpellPower = 21;
        cost = 4;
        getImagen();
    }

    private void getImagen() {
        setUp(4, "piro");
    }

    @Override
    public void checkEvolution(Character character) {

        if (character.getLevel() > 40) {

            name = "Piro++";
            SpellPower = 81;
            cost = 51;
        } else if (character.getLevel() > 20) {
            name = "Piro+";
            SpellPower = 41;
            cost = 20;
        }

    }

}
