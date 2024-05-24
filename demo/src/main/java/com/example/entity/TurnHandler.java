package com.example.entity;

import java.util.LinkedList;
import java.util.Queue;

import com.example.GamePanel;


public class TurnHandler {
    private Queue<Character> turnCharacters;
    private Character currentCharacter;
    
    private Queue<Entity> turnGame;
    private GamePanel gp;

    public TurnHandler (GamePanel gp){
        this.gp = gp;
        turnCharacters = new LinkedList<>();
        turnGame = new LinkedList<>();
    }

    public boolean setAction = false;

    public void turnCharacters(){
        if (!setAction ) {
            setCurrentCharacter(turnCharacters.poll());
        }
      
    }

    public void addCharacterQueue(Character c){
        turnCharacters.add(c);
       
    }



    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter) {
       if (currentCharacter!=null) {
        setAction=true;
       }
        this.currentCharacter = currentCharacter; 
        
    }



}
