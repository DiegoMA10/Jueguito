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

    switch (gp.gameState) {
      case GamePanel.playState: playState(code);break;
      case GamePanel.menuState: menuState(code);break;
      default: break;
    }
   
   
 

  }

  public void titleState(int code) {
   
  }

  public void playState(int code) {
    switch (code) {
      case KeyEvent.VK_W: upPressed = true;  break;
      case KeyEvent.VK_S: downPressed = true;  break;
      case KeyEvent.VK_A: leftPressed = true;  break;
      case KeyEvent.VK_D: rightPressed = true;  break;
      case KeyEvent.VK_ESCAPE:  gp.gameState = GamePanel.menuState; break;
    }
   

  }

  public void menuState(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:gp.gameState = GamePanel.playState; break;
      case KeyEvent.VK_W:
            if (gp.ui.numCommand>0) {
              gp.ui.numCommand--;
            }
            break;
      case KeyEvent.VK_S:
            if (gp.ui.numCommand<1) {
              gp.ui.numCommand++;
            }
            break;
   
    }
   

   
  }

  

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_W: upPressed = false;  break;
      case KeyEvent.VK_S: downPressed = false;  break;
      case KeyEvent.VK_A: leftPressed = false;  break;
      case KeyEvent.VK_D: rightPressed = false;  break;
      
    }

  }

}
