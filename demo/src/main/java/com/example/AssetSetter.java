package com.example;

import java.util.ArrayList;

import com.example.entity.ATB;
import com.example.entity.Aerith;
import com.example.entity.Cloud;
import com.example.entity.Tifa;
import com.example.entity.enemy.Enemy;
import com.example.entity.enemy.Guard;
import com.example.entity.enemy.Megalodoth;
import com.example.entity.enemy.SilverWolf;
import com.example.entity.npc.NPC_Guardian;
import com.example.entity.npc.NPC_Inkeeper;
import com.example.entity.npc.NPC_Item;
import com.example.entity.npc.NPC_Spell;
import com.example.entity.npc.NPC_Weapon;
import com.example.items.Ether;
import com.example.items.Potion;

public class AssetSetter {

    private GamePanel gp;
    public Ether eter;
    public Potion potion;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        potion = new Potion(this.gp);
        eter = new Ether(this.gp);
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
        potion.setAmount(5);
        eter.setAmount(5);
        gp.party.getInventory().add(potion);
        gp.party.getInventory().add(eter);
    }

    public void setNPCObjectStart() {
        NPC_Item npc = (NPC_Item) gp.npc[1][3];
        npc.getInventory().add(eter);
        npc.getInventory().add(potion);
        gp.npc[1][3] = npc;
    }

    public void setGroup() {
        Tifa tifa = new Tifa(gp);
        Aerith aerith = new Aerith(gp);
        Cloud cloud = new Cloud(gp);
        tifa.setATB(new ATB(gp, tifa));
        aerith.setATB(new ATB(gp, aerith));
        cloud.setATB(new ATB(gp, cloud));
        gp.party.agregarPersonaje(aerith);
        gp.party.agregarPersonaje(tifa);
        gp.party.agregarPersonaje(cloud);
    }

    public void setTutorial() {

        ArrayList<Enemy> enemies = new ArrayList<>();
        SilverWolf guard = new SilverWolf(this.gp, 5, 100, 100, 40, gp.tileSize * 4, gp.tileSize * 3);
        guard.setAtb(new ATB(gp, guard));
        enemies.add(guard);
        Megalodoth guard2 = new Megalodoth(this.gp, 5, 100, 100, 40, gp.tileSize * 3, gp.tileSize * 5);
        guard2.setAtb(new ATB(gp, guard2));
        enemies.add(guard2);
        gp.battle.level.put(0, enemies);

        enemies = new ArrayList<>();
        Guard guard3 = new Guard(this.gp, 7, 200, 100, 40, gp.tileSize * 4, gp.tileSize * 3);
        guard3.setAtb(new ATB(gp, guard3));
        enemies.add(guard3);
        Guard guard4 = new Guard(this.gp, 7, 200, 100, 40, gp.tileSize * 3, gp.tileSize * 5);
        guard4.setAtb(new ATB(gp, guard4));
        enemies.add(guard4);
        gp.battle.level.put(1, enemies);

    }

    public void setBoostXP() {

        ArrayList<Enemy> enemies = new ArrayList<>();
        SilverWolf guard = new SilverWolf(this.gp, 5, 50, 100, 20000, gp.tileSize * 4, gp.tileSize * 3);
        guard.setAtb(new ATB(gp, guard));
        enemies.add(guard);
        gp.battle.level.put(0, enemies);
    }

    public void setLevel1() {

        ArrayList<Enemy> enemies = new ArrayList<>();
        SilverWolf silverWolf1 = new SilverWolf(this.gp, 9, 250, 100, 80, gp.tileSize * 4, gp.tileSize * 3);
        silverWolf1.setAtb(new ATB(gp, silverWolf1));
        enemies.add(silverWolf1);
        SilverWolf silverWolf2 = new SilverWolf(this.gp, 9, 250, 100, 80, gp.tileSize * 3, gp.tileSize * 5);
        silverWolf2.setAtb(new ATB(gp, silverWolf2));
        enemies.add(silverWolf2);
        gp.battle.level.put(0, enemies);

        enemies = new ArrayList<>();
        Guard guard3 = new Guard(this.gp, 10, 275, 125, 80, gp.tileSize * 4, gp.tileSize * 3);
        guard3.setAtb(new ATB(gp, guard3));
        enemies.add(guard3);
        Guard guard4 = new Guard(this.gp, 10, 275, 125, 80, gp.tileSize * 3, gp.tileSize * 5);
        guard4.setAtb(new ATB(gp, guard4));
        enemies.add(guard4);
        gp.battle.level.put(1, enemies);

        enemies = new ArrayList<>();
        Megalodoth megalodoth = new Megalodoth(this.gp, 11, 300, 125, 80, gp.tileSize * 4, gp.tileSize * 3);
        megalodoth.setAtb(new ATB(gp, megalodoth));
        enemies.add(megalodoth);
        Megalodoth megalodoth2 = new Megalodoth(this.gp, 11, 300, 125, 80, gp.tileSize * 3, gp.tileSize * 5);
        megalodoth2.setAtb(new ATB(gp, megalodoth2));
        enemies.add(megalodoth2);
        gp.battle.level.put(2, enemies);

    }

    public void setLevel2() {

        ArrayList<Enemy> enemies = new ArrayList<>();
        SilverWolf silverWolf1 = new SilverWolf(this.gp, 13, 350, 100, 140, gp.tileSize * 4, gp.tileSize * 3);
        silverWolf1.setAtb(new ATB(gp, silverWolf1));
        enemies.add(silverWolf1);
        SilverWolf silverWolf2 = new SilverWolf(this.gp, 13, 350, 100, 140, gp.tileSize * 3,
                gp.tileSize * 5 - gp.tileSize / 2);
        silverWolf2.setAtb(new ATB(gp, silverWolf2));
        enemies.add(silverWolf2);
        SilverWolf silverWolf3 = new SilverWolf(this.gp, 13, 350, 100, 140, gp.tileSize * 2,
                gp.tileSize * 7 - gp.tileSize / 2);
        silverWolf3.setAtb(new ATB(gp, silverWolf3));
        enemies.add(silverWolf3);
        gp.battle.level.put(0, enemies);

        enemies = new ArrayList<>();
        Guard guard3 = new Guard(this.gp, 14, 375, 150, 140, gp.tileSize * 4, gp.tileSize * 3);
        guard3.setAtb(new ATB(gp, guard3));
        enemies.add(guard3);
        Guard guard4 = new Guard(this.gp, 14, 375, 150, 140, gp.tileSize * 3, gp.tileSize * 5 - gp.tileSize / 2);
        guard4.setAtb(new ATB(gp, guard4));
        enemies.add(guard4);
        Guard guard = new Guard(this.gp, 14, 375, 150, 140, gp.tileSize * 2, gp.tileSize * 7 - gp.tileSize / 2);
        guard.setAtb(new ATB(gp, guard));
        enemies.add(guard);
        gp.battle.level.put(1, enemies);

        enemies = new ArrayList<>();
        Megalodoth megalodoth = new Megalodoth(this.gp, 15, 400, 200, 145, gp.tileSize * 4, gp.tileSize * 3);
        megalodoth.setAtb(new ATB(gp, megalodoth));
        enemies.add(megalodoth);
        Megalodoth megalodoth2 = new Megalodoth(this.gp, 15, 400, 200, 145, gp.tileSize * 3,
                gp.tileSize * 5 - gp.tileSize / 2);
        megalodoth2.setAtb(new ATB(gp, megalodoth2));
        enemies.add(megalodoth2);
        Megalodoth megalodoth3 = new Megalodoth(this.gp, 15, 400, 200, 145, gp.tileSize * 2,
                gp.tileSize * 7 - gp.tileSize / 2);
        megalodoth3.setAtb(new ATB(gp, megalodoth3));
        enemies.add(megalodoth3);
        gp.battle.level.put(2, enemies);

    }

}
