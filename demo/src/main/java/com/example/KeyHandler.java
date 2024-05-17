package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

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
      case GamePanel.titleState:
        titleState(code);
        break;
      case GamePanel.playState:
        playState(code);
        break;
      case GamePanel.menuState:
        menuState(code);
        break;
      case GamePanel.dialogueState:
        dialoguesState(code);
        break;
      case GamePanel.breakState:
        breakState(code);
        break;

    }

  }

  private void breakState(int code) {
    switch (code) {
      case KeyEvent.VK_W:

        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;

        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.numCommand < 1) {
          gp.ui.numCommand++;

        }
        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;

    }
  }

  public void titleState(int code) {
    switch (code) {
      case KeyEvent.VK_W:

        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;

        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.numCommand < 2) {
          gp.ui.numCommand++;

        }
        break;
        
      case KeyEvent.VK_ENTER:
          if (gp.ui.numCommand==0) {
            gp.gameState = GamePanel.playState;
            gp.stopMusic();
            gp.playMusic(1);
          }

          if (gp.ui.numCommand==2) {
            System.exit(0);
          }
        break;

    }
  }

  public void playState(int code) {
    switch (code) {
      case KeyEvent.VK_W:
        upPressed = true;
        break;
      case KeyEvent.VK_S:
        downPressed = true;
        break;
      case KeyEvent.VK_A:
        leftPressed = true;
        break;
      case KeyEvent.VK_D:
        rightPressed = true;
        break;
      case KeyEvent.VK_ESCAPE:
        gp.gameState = GamePanel.menuState;

        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
    }

  }

  public void menuState(int code) {

    switch (gp.ui.subState) {
      case 0:
        menuSelector(code);
        break;
      case 2:
        statsSelector(code);
        break;
      case 3:
        orderSelector(code);
        break;

    }
  }

  public void menuSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.gameState = GamePanel.playState;
        gp.ui.numCommand = 0;
        break;
      case KeyEvent.VK_W:
        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;

        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.numCommand < 6) {
          gp.ui.numCommand++;

        }
        break;

      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
    }

  }

  public void orderSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.ui.subState = 0;
        gp.ui.order = 0;
        gp.ui.subNumCommand = 0;
        gp.ui.menuStatus = false;
        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand > 0) {
          gp.ui.subNumCommand--;

        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand < 2) {
          gp.ui.subNumCommand++;

        }
        break;

      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
    }
  }

  public void statsSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:

        gp.ui.menuStatus = false;
        gp.ui.subState = 0;
        gp.ui.subNumCommand = 0;

        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand > 0 && !gp.ui.menuStatus) {
          gp.ui.subNumCommand--;

        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand < 2 && !gp.ui.menuStatus) {
          gp.ui.subNumCommand++;

        }
        break;

      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
    }
  }

  public void dialoguesState(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.gameState = GamePanel.playState;
        break;

      case KeyEvent.VK_ENTER:
        gp.gameState = GamePanel.playState;
        break;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_W:
        upPressed = false;
        break;
      case KeyEvent.VK_S:
        downPressed = false;
        break;
      case KeyEvent.VK_A:
        leftPressed = false;
        break;
      case KeyEvent.VK_D:
        rightPressed = false;
        break;

    }

  }

}
