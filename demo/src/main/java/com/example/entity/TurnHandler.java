package com.example.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.example.GamePanel;
import com.example.entity.enemy.Enemy;

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

    public void gameTurn() {
        checkAndCleanUp();
        if (!turnAction && !gp.battle.transitioning) {
            setCurrentTurn(turnGame.poll());
        }
    }

    public void turnCharacters() {
        checkAndCleanUp();
        if (!characterAction && !gp.battle.transitioning) {
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
        if (currentCharacter != null && currentCharacter.getIsAlive()) {
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
        if (currentTurn != null && currentTurn.getIsAlive()) {
            turnAction = true;
        }
        this.currentTurn = currentTurn;
    }

    public void cleanUpQueues() {
        turnCharacters.removeIf(character -> !character.getIsAlive());
        turnGame.removeIf(entity -> !entity.getIsAlive());
    }

    public void checkAndCleanUp() {
        if (currentCharacter != null && !currentCharacter.getIsAlive()) {
            currentCharacter = null;
            nextTurnCharacter();
        }

        if (currentTurn != null && !currentTurn.getIsAlive()) {
            currentTurn = null;
            nextTurn();
        }

        cleanUpQueues();
    }

    public boolean areEnemiesAlive() {

        ArrayList<Enemy> currentEnemies = gp.battle.level.get(gp.battle.currentRound);

        if (currentEnemies != null && !currentEnemies.isEmpty()) {
            for (Enemy enemy : currentEnemies) {
                if (enemy.getIsAlive()) {
                    return true;
                }
            }
        }

        return false;
    }

    public void resetTurnState() {
        this.currentTurn = null;
        this.turnAction = false;
        cleanUpQueues();
    }

    public void resetBattle() {
        turnCharacters.clear();
        turnGame.clear();
        currentCharacter = null;
        currentTurn = null;
        turnAction = false;
        characterAction = false;

    }

}
