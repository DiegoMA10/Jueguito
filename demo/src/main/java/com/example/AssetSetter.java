package com.example;

import com.example.entity.NPC_Guardian;
import com.example.entity.NPC_Inkeeper;
import com.example.entity.NPC_Item;
import com.example.entity.NPC_Spell;
import com.example.entity.NPC_Weapon;

public class AssetSetter {

    private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
        int i = 0;
       gp.npc [0][i] = new NPC_Guardian(gp);
       gp.npc[0][i].worldX = gp.player.worldX-gp.tileSize*2;
       gp.npc[0][i].worldY = gp.player.worldY;
        
     /*   gp.npc [0][1] = new NPC_Inkeeper(gp);
       gp.npc[0][1].worldX =  gp.player.worldX+gp.tileSize*2;
       gp.npc[0][1].worldY = gp.player.worldY; */



       gp.npc [1][i] = new NPC_Weapon(gp);
       gp.npc[1][i].worldX = gp.tileSize*15;
       gp.npc[1][i].worldY = gp.tileSize*33-(gp.tileSize/2);

       i++;

       gp.npc [1][i] = new NPC_Spell(gp);
       gp.npc[1][i].worldX = gp.tileSize*26;
       gp.npc[1][i].worldY = gp.tileSize*33-(gp.tileSize/2);

       i++;

        gp.npc [1][i] = new NPC_Inkeeper(gp);
       gp.npc[1][i].worldX = gp.tileSize*16;
       gp.npc[1][i].worldY = gp.tileSize*10-(gp.tileSize/2); 

       i++;

       gp.npc [1][i] = new NPC_Item(gp);
       gp.npc[1][i].worldX = gp.tileSize*32;
       gp.npc[1][i].worldY = gp.tileSize*8-(gp.tileSize/2);

    }

    
}
