package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.example.entity.Group;
import com.example.entity.PlayerLeader;
import com.example.entity.Tifa;
import com.example.tile.TileManager;

import javafx.scene.control.ProgressBar;



public class GamePanel extends JPanel implements Runnable {
    final int originalTitleSize = 16;
    public final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;
   
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol*tileSize;
    public final int worldHeight = maxWorldRow*tileSize;

    public final int maxMap = 10;
    public int currentMap = 0;

    KeyHandler keyH = new KeyHandler(this);

    Thread gameThread;
    Sound sound = new Sound();
    TileManager tl = new TileManager(this);
    
    Tifa tifa = new Tifa(this);
    
    Group grupo = new Group();
    
    public PlayerLeader player ;
    public CollisionCheck ck = new CollisionCheck(this);
    public UI ui = new UI(this);
    int cont = 0;
    int FPS = 60;

    public int gameState;
    
    public final int titleState = 0;
    public static final int playState = 1;
    public static final int menuState = 2;
   
    public final int battleState = 3;
   
    public GamePanel(){
      
     
        setGroup();
        player = new PlayerLeader(this, keyH,grupo);
      
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

  
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setUpGame(){
       
        gameState=1;
        playMusic(1);
        
    }

    public void setGroup(){
        grupo.agregarPersonaje(tifa);
    }

    

    /* @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
         
            update();

            repaint();

           
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime<0) {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime +=drawInterval;
            } catch (InterruptedException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
        
    }
 */
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
            while (gameThread != null) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime-lastTime);
                lastTime = currentTime;
                if (delta>=1) {
                    update();
                    repaint();
                    delta--;
                  drawCount++;
                  cont++;
                }
                if (timer >= 1000000000) {
                   // System.out.println("FPS: "+drawCount );
                    drawCount = 0;
                    timer = 0;
                    
                    if (cont > 400) {
                        cont = 0;
                    }
                }
             
             
            }

    }

    public void update(){
       player.update();
    }
    public BufferedImage imagen;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        long start = System.nanoTime();
   
       if (gameState == menuState) {
        ui.draw(g2);
       }else{
        tl.draw(g2);
        player.draw(g2);    
        tl.drawSuperior(g2);
        ui.draw(g2);
     
       }

        
       
         long end = System.nanoTime();
        
        
         
        long tiempo = end-start;
        //System.out.println(tiempo);
       
       
        g2.dispose();
    }

    public void playMusic(int n){
        sound.setFile(n);
        sound.setVolume(0.1f);
    
        sound.play();
        sound.loop();
    }

    public void stopMusic(int n){
        sound.stop();
    }

    public void playSE(int n){
        sound.setFile(n);
        sound.play();
    }
}
