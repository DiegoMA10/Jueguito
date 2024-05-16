package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.example.entity.*;
import com.example.tile.*;;

public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 14;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    public final int maxMap = 3;
    public int currentMap = 0;

    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Databases databases = new Databases(this);;
    Thread gameThread;
    Sound music = new Sound();
    Sound soundEfect = new Sound();
    TileManager tl = new TileManager(this);
    BattlePanel battle = new BattlePanel(this);

    Tifa tifa = new Tifa(this);
    Aerith aerith = new Aerith(this);
    Cloud cloud = new Cloud(this);
    public Group grupo = new Group();

    public PlayerLeader player;
    public Entity[][] npc = new Entity[2][4];
    public ArrayList<Entity> entityList = new ArrayList<>();
    public CollisionCheck ck = new CollisionCheck(this);
    public UI ui = new UI(this);

    int FPS = 60;

    public int gameState;

    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int menuState = 2;
    public static final int battleState = 3;
    public static final int transitionState = 4;
    public static final int dialogueState = 5;
    public static final int breakState = 6;

    public GamePanel() {

        setGroup();
        player = new PlayerLeader(this, keyH, grupo);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame() {
       
        gameState = 1;
        aSetter.setNPC();
        playMusic(1);

    }

    public void setGroup() {
        grupo.agregarPersonaje(tifa);
        grupo.agregarPersonaje(aerith);
        grupo.agregarPersonaje(cloud);
    }

    /*
     * @Override
     * public void run() {
     * 
     * double drawInterval = 1000000000/FPS;
     * double nextDrawTime = System.nanoTime() + drawInterval;
     * 
     * while (gameThread != null) {
     * 
     * update();
     * 
     * repaint();
     * 
     * 
     * try {
     * double remainingTime = nextDrawTime - System.nanoTime();
     * remainingTime = remainingTime/1000000;
     * 
     * if (remainingTime<0) {
     * remainingTime = 0;
     * }
     * Thread.sleep((long)remainingTime);
     * nextDrawTime +=drawInterval;
     * } catch (InterruptedException e) {
     * 
     * // TODO Auto-generated catch block
     * e.printStackTrace();
     * 
     * }
     * }
     * 
     * }
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if (timer >= 1000000000) {
                // System.out.println("FPS: "+drawCount );
                drawCount = 0;
                timer = 0;

            }

        }

    }

    public void update() {

        if (gameState == playState) {
             player.update();
        }
       
    }

    public BufferedImage imagen;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        long start = System.nanoTime();

        if (gameState == titleState || gameState == menuState) {
            ui.draw(g2);
        } else if (gameState == battleState) {
            battle.draw(g2);
            ui.draw(g2);
        } else {
            tl.draw(g2);
           
            entityList.add(player);
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            Collections.sort(entityList);
            for (Entity entity : entityList) {
                entity.draw(g2);
            } 
                tl.drawSuperior(g2);
                ui.draw(g2);   
               
        }

        long end = System.nanoTime();

        long tiempo = end - start;
        // System.out.println(tiempo);

        g2.dispose();
        entityList.clear();
    }

    public void playMusic(int n) {
        music.setFile(n);
        music.setVolume(0.05f);

        music.play();
        music.loop();
    }

    public void stopMusic() {
        
        music.stop();
    }

    public void playSE(int n) {
        soundEfect.setFile(n);
        soundEfect.play();
    }
}
