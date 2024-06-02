package com.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.example.items.Item;

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
        titleSelector(code);
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
      case GamePanel.tradeState:
        tradeState(code);
        break;
      case GamePanel.dialogueToBattleState:
        dialogueToBattleSelector(code);
        break;
      case GamePanel.battleState:
        battleState(code);
        break;
    }

  }

  private void battleState(int code) {
    switch (gp.ui.subState) {
      case 0:
        battleMenu(code);
        break;
      case 1:
        attackSelector(code);
        break;
      case 2:
        spellSelector(code);
        break;
      case 3:
        itemSelector(code);
        break;

    }

  }

  private void spellSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {
          gp.ui.subState2 = 0;

        } else {
          gp.ui.subState = 0;

          gp.ui.subNumCommand = 0;
        }
        break;
      case KeyEvent.VK_W:

        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 > 0) {
            gp.playSE(3);
            gp.ui.subNumCommand2--;

          }
        } else {
          if (gp.ui.subNumCommand > 0) {
            gp.playSE(3);
            gp.ui.subNumCommand--;

          }
        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 < gp.battle.level.get(gp.battle.currentRound).size() - 1) {
            gp.playSE(3);
            gp.ui.subNumCommand2++;

          }
        } else {
          if (gp.ui.subNumCommand < gp.party.getParty().get(0).getAbilities().size() - 1) {
            gp.playSE(3);
            gp.ui.subNumCommand++;

          }
        }

        break;

      case KeyEvent.VK_ENTER:
        gp.playSE(3);
        enterPressed = true;
        break;
    }
  }

  private void battleMenu(int code) {
    switch (code) {

      case KeyEvent.VK_W:

        if (gp.ui.numCommand > 0) {
          gp.playSE(3);
          gp.ui.numCommand--;

        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.numCommand < 2) {
          gp.playSE(3);
          gp.ui.numCommand++;

        }
        break;
      case KeyEvent.VK_ENTER:
        if (gp.turnHandler.getCurrentCharacter() != null) {
          gp.playSE(3);
        }

        gp.keyH.enterPressed = true;
        break;

    }
  }

  private void attackSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.ui.subState = 0;
        gp.ui.subNumCommand = 0;
        gp.playSE(3);
        break;
      case KeyEvent.VK_W:

        if (gp.ui.subNumCommand > 0) {
          gp.ui.subNumCommand--;
          gp.playSE(3);
        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.subNumCommand < gp.battle.level.get(gp.battle.currentRound).size() - 1) {
          gp.ui.subNumCommand++;
          gp.playSE(3);
        }
        break;
      case KeyEvent.VK_ENTER:

        gp.playSE(3);
        gp.keyH.enterPressed = true;
        break;

    }
  }

  private void dialogueToBattleSelector(int code) {
    switch (gp.ui.subState) {
      case 0:
        breakState(code);
        break;
      case 1:
        levelSelector(code);
        break;

    }
  }

  private void levelSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.playSE(3);
        gp.ui.subState = 0;
        gp.ui.subNumCommand = 0;
        break;
      case KeyEvent.VK_W:

        if (gp.ui.subNumCommand > 0) {
          gp.playSE(3);
          gp.ui.subNumCommand--;

        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.subNumCommand < 3) {
          gp.playSE(3);
          gp.ui.subNumCommand++;

        }
        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;

    }
  }

  private void tradeState(int code) {

    switch (gp.ui.subState) {
      case 0:
        TradeSelector(code);
        break;
      case 1:
        tradeBuySelector(code);
        break;
      case 2:
        tradeSellSelector(code);
        break;
    }
  }

  private void tradeSellSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        if (!gp.ui.menuStatus) {
          gp.playSE(3);
          if (gp.ui.subState2 == 1) {
            gp.ui.subState2 = 0;
            gp.ui.subNumCommand2 = 0;
          } else {
            gp.ui.subState = 0;
            gp.ui.subNumCommand = 0;
          }
        }

        break;
      case KeyEvent.VK_W:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {

          int newAmount = gp.ui.subNumCommand2 + 10;
          int maxAmount = gp.party.getInventory().get(gp.ui.subNumCommand).getAmount();

          gp.ui.subNumCommand2 = Math.min(maxAmount, newAmount);

        } else {
          if (gp.ui.subNumCommand > 0) {
            gp.ui.subNumCommand--;

          }
        }

        break;

      case KeyEvent.VK_S:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {

          gp.ui.subNumCommand2 = Math.max(1, gp.ui.subNumCommand2 - 10);

        } else {
          if (gp.ui.subNumCommand < gp.party.getInventory().size() - 1) {
            gp.ui.subNumCommand++;

          }
        }

        break;

      case KeyEvent.VK_A:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 > 1) {
            gp.ui.subNumCommand2--;
          }
        }

        break;
      case KeyEvent.VK_D:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {
          int newAmount = gp.ui.subNumCommand2 + 1;
          int maxAmount = gp.party.getInventory().get(gp.ui.subNumCommand).getAmount();

          gp.ui.subNumCommand2 = Math.min(maxAmount, newAmount);
        }

        break;

      case KeyEvent.VK_ENTER:
        if (gp.ui.subState2 == 0) {
          gp.playSE(3);
        }

        enterPressed = true;
        break;
    }

  }

  private void tradeBuySelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:

        if (!gp.ui.menuStatus) {
          gp.playSE(3);
          if (gp.ui.subState2 == 1) {
            gp.ui.subState2 = 0;
            gp.ui.subNumCommand2 = 0;

          } else {

            gp.ui.subState = 0;
            gp.ui.subNumCommand = 0;

          }
        }

        break;
      case KeyEvent.VK_W:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {

          int newAmount = gp.ui.subNumCommand2 + 10;
          int maxAmount = Item.maxAmount - gp.ui.itemNpc.getInventory().get(gp.ui.subNumCommand).getAmount();
          int itemPrice = gp.ui.itemNpc.getInventory().get(gp.ui.subNumCommand).getPrice();

          if (gp.party.getGil() >= itemPrice * newAmount) {
            gp.ui.subNumCommand2 = Math.min(maxAmount, newAmount);
          } else {
            gp.ui.subNumCommand2 = gp.party.getGil() / itemPrice;
          }

        } else {
          if (gp.ui.subNumCommand > 0) {
            gp.ui.subNumCommand--;

          }

        }

        break;

      case KeyEvent.VK_S:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {

          gp.ui.subNumCommand2 = Math.max(1, gp.ui.subNumCommand2 - 10);

        } else {

          if (gp.ui.subNumCommand < gp.ui.itemNpc.getInventory().size() - 1) {
            gp.ui.subNumCommand++;

          }
        }

        break;

      case KeyEvent.VK_A:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 > 1) {
            gp.ui.subNumCommand2--;
          }
        }

        break;
      case KeyEvent.VK_D:
        gp.playSE(3);
        if (gp.ui.subState2 == 1) {
          int newAmount = gp.ui.subNumCommand2 + 1;
          int maxAmount = Item.maxAmount - gp.ui.itemNpc.getInventory().get(gp.ui.subNumCommand).getAmount();
          int itemPrice = gp.ui.itemNpc.getInventory().get(gp.ui.subNumCommand).getPrice();

          if (gp.party.getGil() >= itemPrice * newAmount) {
            gp.ui.subNumCommand2 = Math.min(maxAmount, newAmount);
          } else {
            gp.ui.subNumCommand2 = gp.party.getGil() / itemPrice;
          }
        }

        break;

      case KeyEvent.VK_ENTER:
        if (gp.ui.subState2 == 0) {
          gp.playSE(3);
        }
        enterPressed = true;
        break;
    }
  }

  private void TradeSelector(int code) {
    switch (code) {
      case KeyEvent.VK_A:

        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;
          gp.playSE(3);
        }

        break;
      case KeyEvent.VK_D:
        if (gp.ui.numCommand < 2) {
          gp.ui.numCommand++;
          gp.playSE(3);
        }
        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
    }
  }

  private void breakState(int code) {
    switch (code) {
      case KeyEvent.VK_W:

        if (gp.ui.numCommand > 0) {
          gp.playSE(3);
          gp.ui.numCommand--;

        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.numCommand < 1) {
          gp.playSE(3);
          gp.ui.numCommand++;

        }
        break;
      case KeyEvent.VK_ENTER:
        gp.playSE(3);
        enterPressed = true;
        break;

    }
  }

  private void titleSelector(int code) {
    switch (gp.ui.subState) {
      case 0:
        titleState(code);
        break;
      case 1:
        saveSelector(code);
        break;

    }
  }

  public void titleState(int code) {
    switch (code) {

      case KeyEvent.VK_W:

        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;
          gp.playSE(3);
        }
        break;
      case KeyEvent.VK_S:
        if (gp.ui.numCommand < 2) {
          gp.ui.numCommand++;
          gp.playSE(3);
        }
        break;

      case KeyEvent.VK_ENTER:
        gp.keyH.enterPressed = true;
        gp.playSE(3);
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
        gp.ui.gameStateTransition = GamePanel.menuState;
        break;
      case KeyEvent.VK_ENTER:
        enterPressed = true;
        break;
      case KeyEvent.VK_P:
        if (gp.debug) {
          gp.debug = false;
        } else {
          gp.debug = true;
        }
        break;
    }

  }

  public void menuState(int code) {

    switch (gp.ui.subState) {
      case 0:
        menuSelector(code);
        break;
      case 1:
        itemSelector(code);
        break;
      case 2:
        statsSelector(code);
        break;
      case 3:
        orderSelector(code);
        break;
      case 6:
        saveSelector(code);
        break;

    }
  }

  private void saveSelector(int code) {
    switch (gp.ui.subState2) {
      case 0:
        saveMenu(code);
        break;
      case 1:
        updateMenu(code);
        break;

    }
  }

  private void saveMenu(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.playSE(3);
        gp.ui.subState = 0;
        gp.ui.subNumCommand = 0;
        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand > 0) {
          gp.ui.subNumCommand--;
          gp.playSE(3);
        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand < 2) {
          gp.ui.subNumCommand++;
          gp.playSE(3);
        }
        break;

      case KeyEvent.VK_ENTER:
        enterPressed = true;
        gp.playSE(3);
        break;
    }
  }

  private void updateMenu(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.ui.subState2 = 0;
        gp.ui.subNumCommand2 = 0;
        gp.playSE(3);
        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand2 > 0) {
          gp.ui.subNumCommand2--;
          gp.playSE(3);
        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand2 < 1) {
          gp.playSE(3);
          gp.ui.subNumCommand2++;

        }
        break;

      case KeyEvent.VK_ENTER:
        gp.playSE(3);
        enterPressed = true;
        break;
    }
  }

  private void itemSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:

        if (gp.ui.subState2 == 1) {
          gp.playSE(3);
          gp.ui.subState2 = 0;
          gp.ui.order = 0;
        } else {
          gp.playSE(3);
          gp.ui.subState = 0;
          gp.ui.order = 0;
          gp.ui.subNumCommand = 0;
        }
        break;
      case KeyEvent.VK_W:

        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 > 0) {
            gp.playSE(3);
            gp.ui.subNumCommand2--;

          }
        } else {
          if (gp.ui.subNumCommand > 0) {
            gp.playSE(3);
            gp.ui.subNumCommand--;

          }
        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subState2 == 1) {
          if (gp.ui.subNumCommand2 < 2) {
            gp.ui.subNumCommand2++;
            gp.playSE(3);
          }
        } else {
          if (gp.ui.subNumCommand < gp.party.getInventory().size() - 1) {
            gp.ui.subNumCommand++;
            gp.playSE(3);
          }
        }

        break;

      case KeyEvent.VK_ENTER:
        gp.playSE(3);
        enterPressed = true;
        break;
    }
  }

  public void menuSelector(int code) {
    switch (code) {
      case KeyEvent.VK_ESCAPE:
        gp.ui.gameStateTransition = GamePanel.playState;
        gp.ui.numCommand = 0;
        gp.playSE(3);
        break;
      case KeyEvent.VK_W:
        if (gp.ui.numCommand > 0) {
          gp.ui.numCommand--;
          gp.playSE(3);
        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.numCommand < 6) {
          gp.ui.numCommand++;
          gp.playSE(3);
        }
        break;

      case KeyEvent.VK_ENTER:
        enterPressed = true;
        gp.playSE(3);
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
        gp.playSE(3);
        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand > 0) {

          gp.ui.subNumCommand--;
          gp.playSE(3);

        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand < 2) {

          gp.ui.subNumCommand++;
          gp.playSE(3);

        }
        break;

      case KeyEvent.VK_ENTER:
        gp.playSE(3);
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
        gp.playSE(3);
        break;
      case KeyEvent.VK_W:
        if (gp.ui.subNumCommand > 0 && !gp.ui.menuStatus) {
          gp.playSE(3);
          gp.ui.subNumCommand--;

        }

        break;
      case KeyEvent.VK_S:

        if (gp.ui.subNumCommand < 2 && !gp.ui.menuStatus) {
          gp.playSE(3);
          gp.ui.subNumCommand++;

        }
        break;

      case KeyEvent.VK_ENTER:
        if (!gp.ui.menuStatus) {
          gp.playSE(3);
        }

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
