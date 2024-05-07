package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public boolean upPressed, downPressed, leftPressed, rightPressed;

  public GamePanel gp;

  public KeyHandler(GamePanel gp) {
    this.gp = gp;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
   
    if (gp.gameState==gp.tileSize) {
      
    }else if (gp.gameState == gp.playState) {
      playState(code);
    }else if (gp.gameState == gp.menuState) {
      menuState(code);
    }

  }

  public void titleState(int code) {
   
  }

  public void playState(int code) {
    if (code == KeyEvent.VK_W) {
      upPressed = true;
    }

    if (code == KeyEvent.VK_S) {
      downPressed = true;
    }

    if (code == KeyEvent.VK_A) {
      leftPressed = true;
    }

    if (code == KeyEvent.VK_D) {
      rightPressed = true;
    }

    if (code == KeyEvent.VK_ESCAPE) {
      gp.gameState = gp.menuState;
     
    }

  }

  public void menuState(int code) {
    if (code == KeyEvent.VK_ESCAPE) {
      gp.gameState = gp.playState;

    }
  }

  

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    if (code == KeyEvent.VK_W) {
      upPressed = false;
    }

    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }

    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }

    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }

  }

}
