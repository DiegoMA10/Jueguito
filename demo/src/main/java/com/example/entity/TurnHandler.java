package com.example.entity;

import java.util.LinkedList;
import java.util.Queue;

import com.example.GamePanel;

public class TurnHandler {
    private Queue<Character> turnCharacters;
    private Character currentCharacter;

    private Queue<Entity> turnGame;
    private Entity currentTurn;
   
   

    private GamePanel gp;

    public TurnHandler(GamePanel gp) {
        this.gp = gp;
        turnCharacters = new LinkedList<>();
        turnGame = new LinkedList<>();
    }

    private boolean characterAction = false;
    private boolean turnAction = false;

    

    public void gameTurn(){
        if (!turnAction) {
            setCurrentTurn(turnGame.poll()); 
           
        }
       
        
    }

    public void turnCharacters() {
        if (!characterAction) {
            setCurrentCharacter(turnCharacters.poll());
        }

    }


    public void addCharacterQueue(Character c) {
        turnCharacters.add(c);

    }

    public void addGameQueue(Entity e) {
       turnGame.add(e);

    }




     
    public void nextTurnCharacter() {
        this.characterAction = false;
    }

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character currentCharacter) {
        if (currentCharacter != null) {
            characterAction = true;
        }
        this.currentCharacter = currentCharacter;

    }

    public void nextTurn() {
        this.turnAction = false;
    }

    public Entity getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Entity currentTurn) {
        if (currentTurn != null) {
            turnAction = true;
        }
        this.currentTurn = currentTurn;
    }

}
