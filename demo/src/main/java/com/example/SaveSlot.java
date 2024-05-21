package com.example;

import java.util.ArrayList;

import com.example.entity.Character;
import com.example.entity.Party;

public class SaveSlot {
    private int id;
    private Party party;
    private boolean isEmpty;
    private double playTime;
    GamePanel gp;

    public SaveSlot(GamePanel gp, int id) {
        this.gp = gp;
        this.id = id;
        this.isEmpty = true;
        setSaveSlot();
    }

    public void setSaveSlot() {
        Database db = new Database(gp);
        this.party = new Party(db.getCharactersByGame(id), db.getInventoryByParty(id), db.getGilByParty(id));
        this.playTime = db.getPlayTime(id);
        this.isEmpty = !db.checkSave(id);
        
    }

    public Party getParty() {
        return party;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public double getPlayTime() {
        return playTime;
    }

}