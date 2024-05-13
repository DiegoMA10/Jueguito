package com.example;

import com.example.entity.NPC_Guardian;

public class AssetSetter {

    private GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setNPC() {
       gp.npc [0] = new NPC_Guardian(gp);
       gp.npc[0].worldX = gp.player.worldX-gp.tileSize*2;
       gp.npc[0].worldY = gp.player.worldY;
    }

    
}
