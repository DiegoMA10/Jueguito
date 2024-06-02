package com.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.example.entity.Character;
import com.example.entity.enemy.Enemy;

public class BattlePanel {
    BufferedImage[] background = new BufferedImage[10];
    GamePanel gp;
    public HashMap<Integer, ArrayList<Enemy>> level;
    public int currentRound = 0;
    public AnimationAttack animationAttack;
    public List<AnimatedText> animatedTexts;
    public Iterator<Enemy> iterator;
    int cont = 0;
    public boolean enemiesAlive;
    public boolean transition = false;
    public boolean endBattle = false;
    public boolean gameOver = false;
    private Color backgroundColor = new Color(0, 0, 0, 0);
    private int totalExp = 0;
    private int totalGil = 0;

    public BattlePanel(GamePanel gp) {
        this.gp = gp;
        animationAttack = new AnimationAttack(gp);
        level = new HashMap<>();
        animatedTexts = new ArrayList<>();
        loadBackground();
    }

    public void setUp(int i, String path) {
        UtilityTool tool = new UtilityTool();

        try {

            background[i] = ImageIO
                    .read(getClass().getResourceAsStream("/com/example/image/backgrounds/" + path + ".png"));
            background[i] = tool.imageScale(background[i], gp.screenWidth + 10, gp.screenHeight + 10);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addAnimatedText(AnimatedText animatedText) {
        animatedTexts.add(animatedText);
    }

    public void loadBackground() {
        setUp(0, "background_1");
        setUp(1, "background_2");

    }

    public void update() {
        gp.party.update();

        ArrayList<Enemy> currentLevel = level.get(currentRound);
        enemiesAlive = false;
        if (currentLevel != null && !currentLevel.isEmpty()) {
            iterator = currentLevel.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();

                enemy.update();

                if (enemy.getIsAlive()) {
                    enemiesAlive = true;
                }
            }
        }

        if ((!gameOver && !gp.party.isAlive()) && gp.turnHandler.getCurrentTurn() == null
                && gp.ui.gameStateTransition == GamePanel.battleState) {
            gameOver();
        } else if (!enemiesAlive && !level.containsKey(currentRound + 1) && !endBattle
                && gp.turnHandler.getCurrentTurn() == null && gp.ui.gameStateTransition == GamePanel.battleState) {

            endBattle();
        } else if (!enemiesAlive && level.containsKey(currentRound + 1) && gp.turnHandler.getCurrentTurn() == null
                && gp.ui.gameStateTransition == GamePanel.battleState && !transition) {

            startTransition();

        }

        gp.turnHandler.turnCharacters();
        gp.turnHandler.gameTurn();
        animationAttack.update();
        Iterator<AnimatedText> iteratorText = animatedTexts.iterator();
        while (iteratorText.hasNext()) {
            AnimatedText text = iteratorText.next();
            if (!text.update()) {
                iteratorText.remove();
            }
        }

        if (transition) {
            transitionToNextRound();
        }

    }

    public void gameOver() {
        gameOver = true;
        gp.stopMusic();
        gp.playSE(12);
        gp.ui.addBattleMessage("Muelto");
    }

    public void transitionToNextRound() {
        cont++;
        backgroundColor = new Color(0, 0, 0, cont * 2);
        if (cont > 120) {
            cont = 0;
            transition = false;
            currentRound++;
            enemiesAlive = false;
            gp.party.defaultPosition();
            gp.turnHandler.resetTurnState();
        }
    }

    public void endBattle() {
        gp.stopMusic();
        gp.playMusic(11);
        endBattle = true;
        gp.party.addExp(totalExp);
        gp.party.addGil(totalGil);
        gp.ui.addBattleMessage("Has conseguido " + totalExp + " de  experiencia");
        for (Character character : gp.party.getParty()) {
            if (character.checkLevel()) {
                gp.ui.addBattleMessage(character.getName() + " a subido de nivel");
            }
        }
        gp.ui.addBattleMessage("Has conseguido " + totalGil + "G");
        totalExp = 0;
        totalGil = 0;

    }

    public void startTransition() {
        transition = true;
        backgroundColor = new Color(0, 0, 0, 0);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(background[1], -10, 0, gp.screenWidth + 20, gp.screenHeight - gp.tileSize * 3, null);
        gp.party.draw(g2);

        for (Enemy enemy : level.get(currentRound)) {
            enemy.draw(g2);
        }

        animationAttack.draw(g2);
        for (AnimatedText animatedText : animatedTexts) {
            animatedText.draw(g2);
        }
        if (transition) {
            g2.setColor(backgroundColor);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        }

    }

    public void addExp(int exp) {
        this.totalExp += exp;
    }

    public void addGil(int gil) {
        this.totalGil += gil;
    }

}
