package com.example;

import com.example.entity.Aerith;
import com.example.entity.Cloud;
import com.example.entity.Tifa;
import com.example.entity.npc.NPC_Guardian;
import com.example.entity.npc.NPC_Inkeeper;
import com.example.entity.npc.NPC_Item;
import com.example.entity.npc.NPC_Spell;
import com.example.entity.npc.NPC_Weapon;
import com.example.object.Eter;
import com.example.object.Potion;

public class AssetSetter {

    private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        int i = 0;
        gp.npc[0][i] = new NPC_Guardian(gp);
        gp.npc[0][i].worldX = gp.player.worldX - gp.tileSize * 2;
        gp.npc[0][i].worldY = gp.player.worldY;

        /*
         * gp.npc [0][1] = new NPC_Inkeeper(gp);
         * gp.npc[0][1].worldX = gp.player.worldX+gp.tileSize*2;
         * gp.npc[0][1].worldY = gp.player.worldY;
         */

        gp.npc[1][i] = new NPC_Weapon(gp);
        gp.npc[1][i].worldX = gp.tileSize * 15;
        gp.npc[1][i].worldY = gp.tileSize * 33 - (gp.tileSize / 2);

        i++;

        gp.npc[1][i] = new NPC_Spell(gp);
        gp.npc[1][i].worldX = gp.tileSize * 26;
        gp.npc[1][i].worldY = gp.tileSize * 33 - (gp.tileSize / 2);

        i++;

        gp.npc[1][i] = new NPC_Inkeeper(gp);
        gp.npc[1][i].worldX = gp.tileSize * 16;
        gp.npc[1][i].worldY = gp.tileSize * 10 - (gp.tileSize / 2);

        i++;

        gp.npc[1][i] = new NPC_Item(gp);
        gp.npc[1][i].worldX = gp.tileSize * 32;
        gp.npc[1][i].worldY = gp.tileSize * 8 - (gp.tileSize / 2);

    }

    public void setObjectStart() {
        Potion potion = new Potion(gp);
        potion.setAmount(5);
        Eter eter = new Eter(gp);
        eter.setAmount(5);
        gp.group.getInventory().add(potion);
        gp.group.getInventory().add(eter);
    }

    public void setGroup() {
        Tifa tifa = new Tifa(gp);
        Aerith aerith = new Aerith(gp);
        Cloud cloud = new Cloud(gp);
        gp.group.agregarPersonaje(aerith);
        gp.group.agregarPersonaje(tifa);
        gp.group.agregarPersonaje(cloud);
    }

}
