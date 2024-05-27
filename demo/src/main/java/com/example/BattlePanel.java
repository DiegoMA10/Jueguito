package com.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.example.entity.enemy.Enemy;

public class BattlePanel {
    BufferedImage[] background = new BufferedImage[10];
    GamePanel gp;
    public HashMap<Integer, ArrayList<Enemy>> level;
    public int currentRound = 0;
    public AnimationAttack animationAttack;
    public Iterator<Enemy> iterator;
    int cont = 0;
    public boolean enemiesAlive;
    public boolean transitioning = false;
    public boolean endBattle = false;
    private Color backgroundColor = new Color(0, 0, 0, 0);
    private int totalExp=0;
    private int totalGil=0;
   

    public BattlePanel(GamePanel gp) {
        this.gp = gp;
        animationAttack = new AnimationAttack(gp);
        level = new HashMap<>();

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

        if (!enemiesAlive && level.containsKey(currentRound + 1) && gp.turnHandler.getCurrentTurn() == null) {
            startTransition();
        } else if (!enemiesAlive && !level.containsKey(currentRound + 1) && !endBattle) {
            endBattle();
          
        }

        gp.turnHandler.turnCharacters();
        gp.turnHandler.gameTurn();
        animationAttack.update();

        if (transitioning) {
            transitionToNextRound();
        }

        if (endBattle) {
            
        }
    }

    public void transitionToNextRound() {
        cont++;
        backgroundColor = new Color(0, 0, 0, cont * 2);
        if (cont > 120) {
            cont = 0;
            transitioning = false;
            currentRound++;
            enemiesAlive = false;
            gp.party.defaultPosition();
            gp.turnHandler.resetTurnState();
        }
    }

    public void endBattle() {
        endBattle = true;
        System.out.println(totalExp+"."+totalGil);
    }

    public void startTransition() {
        transitioning = true;
        backgroundColor = new Color(0, 0, 0, 0);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(background[1], -10, 0, gp.screenWidth + 20, gp.screenHeight - gp.tileSize * 3, null);
        gp.party.draw(g2);

        for (Enemy enemy : level.get(currentRound)) {
            enemy.draw(g2);
        }

        animationAttack.draw(g2);

        if (transitioning) {
            g2.setColor(backgroundColor);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        }

    }

    public void addExp(int exp){
        this.totalExp+=exp;
    }

}
