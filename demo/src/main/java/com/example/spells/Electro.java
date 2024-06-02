package com.example.spells;

import com.example.GamePanel;
import com.example.entity.Character;

public class Electro extends Spell {

    public Electro(GamePanel gp) {
        super(gp);
        this.name = "Electro";
        this.SpellPower = 21;
        this.cost = 6;
        getImagen();
    }

    private void getImagen() {
        setUp(19, "electro");
    }

    public void checkEvolution(Character character) {

        if (character.getLevel() > 40) {

            this.name = "Electro++";
            this.SpellPower = 81;
            this.cost = 53;
        } else if (character.getLevel() > 24) {
            this.name = "Electro+";
            this.SpellPower = 41;
            this.cost = 22;
        }

    }

}
