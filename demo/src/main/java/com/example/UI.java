package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import com.example.Items.Item;
import com.example.entity.Character;
import com.example.entity.Party;
import com.example.entity.enemy.Enemy;
import com.example.entity.npc.NPC_Item;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private Double playTimer = 0.0;

    private BufferedImage cursor;
    private BufferedImage titleScreen;
    private BufferedImage cursorInvert;
    public int subState = 0;
    public int subState2 = 0;
    public int numCommand = 0;
    public int subNumCommand = 0;
    public int subNumCommand2 = 0;
    public int numIndexGroup;
    public boolean menuStatus = false;
    public int gameStateTransition = 0;
    private Color blueMenu = new Color(0, 223, 223);
    private Font arial40;
    private Font normalFont;
    private Font UIBattleFont;
    public String currentDialogue;
    private Boolean animationState = true;
    int cont = 0;
    public NPC_Item itemNpc;
    private String menuMessage;
    private Queue<String> battleMessage = new LinkedList<>();
    private String currentMessage = null;
    SaveSlot save1 = null;
    SaveSlot save2 = null;
    SaveSlot save3 = null;

    public UI(GamePanel gp) {

        this.gp = gp;
        getImagen();
        save1 = new SaveSlot(gp, 1);
        save2 = new SaveSlot(gp, 2);
        save3 = new SaveSlot(gp, 3);
        arial40 = new Font("Arial", Font.PLAIN, 40);
        normalFont = new Font("Arial", Font.BOLD, 30);
        UIBattleFont = new Font("Arial", Font.BOLD, 24);

    }

    public void addBattleMessage(String message) {
        if (currentMessage == null) {
            currentMessage = message;
        } else {
            battleMessage.add(message);
        }

    }

    public BufferedImage setUp(String path) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("image/UI/" + path + ".png"));
            image = tool.imageScale(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage setUpBackground(String path) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("image/UI/" + path + ".png"));
            image = tool.imageScale(image, gp.screenWidth, gp.screenHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getImagen() {
        cursor = setUp("cursor");
        cursorInvert = setUp("cursorInvert");
        titleScreen = setUpBackground("titleScreen");

    }

    public Double getPlayTimer() {
        return playTimer;
    }

    public void setPlayTimer(Double playTimer) {
        this.playTimer = playTimer;
    }

    public String formatSecondsToHoursMinutes(Double totalSeconds) {
        int hours = (int) (totalSeconds / 3600);
        int minutes = (int) ((totalSeconds % 3600) / 60);

        return String.format("%02d:%02d", hours, minutes);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (gp.gameState != GamePanel.titleState) {
            playTimer += 1.0 / 60;

        }

        g2.setFont(arial40);

        switch (gp.gameState) {
            case GamePanel.titleState:
                titleSelector();
                if (gameStateTransition != GamePanel.titleState) {
                    drawGameStateTransition();
                }

                break;
            case GamePanel.menuState:
                drawMenuWindows();
                if (gameStateTransition != GamePanel.menuState) {
                    drawGameStateTransition();
                }
                break;
            case GamePanel.playState:
                if (gameStateTransition != GamePanel.playState) {
                    drawGameStateTransition();
                }
                break;
            case GamePanel.battleState:
                drawBattleMenu();
                if (gameStateTransition != GamePanel.battleState) {
                    drawGameStateTransition();
                }
                break;
            case GamePanel.transitionState:
                drawTransition();
                break;
            case GamePanel.dialogueState:
                drawDialogues();
                break;
            case GamePanel.breakState:
                breakSelector();
                break;
            case GamePanel.tradeState:
                TradeSelector();
                if (gameStateTransition != GamePanel.tradeState) {
                    drawGameStateTransition();
                }
                break;
            case GamePanel.dialogueToBattleState:
                drawDialogueToBattleSelector();
                if (gameStateTransition != GamePanel.dialogueToBattleState) {
                    drawGameStateTransition();
                }

                break;
        }

    }

    private void titleSelector() {
        switch (subState) {
            case 0:
                drawTitleScreen();
                break;
            case 1:
                loadSelector();
                break;

        }
    }

    private void loadSelector() {

        switch (subState2) {
            case 0:
                loadMenu();
                break;
            case 1:
                loadGame();
                break;

        }
    }

    private void drawTitleScreen() {

        g2.drawImage(titleScreen, 0, 0, gp.screenWidth, gp.screenWidth, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setColor(Color.BLACK);
        String text = "Start Game";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 + gp.tileSize * 2;

        g2.drawString(text, x, y);
        if (numCommand == 0) {
            if (gp.keyH.enterPressed) {

                gp.ui.gameStateTransition = GamePanel.playState;
                gp.aSetter.setObjectStart();
                gp.stopMusic();
                gp.playMusic(1);
            }
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.drawString(text, x, y);
        if (numCommand == 1) {
            if (gp.keyH.enterPressed) {
                subState = 1;
            }
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.drawString(text, x, y);
        if (numCommand == 2) {
            if (gp.keyH.enterPressed) {
                System.exit(0);
            }
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

        gp.keyH.enterPressed = false;
    }

    public int getXforCenteredText(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }

    private void loadMenu() {
        int x = 5;
        int y = 5;
        int width = gp.screenWidth - 10;
        int height = gp.tileSize * 2;
        g2.setFont(normalFont);
        drawSubwindows(x, y, width, height);
        drawText("Cargar", getXforCenteredText("Cargar"), y + gp.tileSize * 2 - 20, null);
        y += gp.tileSize * 2;
        height = gp.tileSize * 4 - 5;
        drawSubwindows(x, y, width, height);

        if (!save1.isEmpty()) {
            if (subNumCommand == 0) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }

            drawSaveSlot(save1, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);

        }
        if (subNumCommand == 0) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize * 4 - 5;
        height = gp.tileSize * 4 - 5;
        drawSubwindows(x, y, width, height);
        if (!save2.isEmpty()) {

            if (subNumCommand == 1) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }

            drawSaveSlot(save2, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);

        }

        if (subNumCommand == 1) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize * 4 - 5;
        height = gp.tileSize * 4;
        drawSubwindows(x, y, width, height);

        if (!save3.isEmpty()) {

            if (subNumCommand == 2) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }
            drawSaveSlot(save3, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);
        }

        if (subNumCommand == 2) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }

        gp.keyH.enterPressed = false;
    }

    private void loadGame() {

        switch (subNumCommand) {
            case 0:
                drawMenuPortrait(save1.getParty());
                loadInterface(save1);
                break;
            case 1:
                drawMenuPortrait(save2.getParty());
                loadInterface(save2);
                break;
            case 2:
                drawMenuPortrait(save3.getParty());
                loadInterface(save3);
                break;

        }

    }

    private void loadInterface(SaveSlot save) {

        int x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 7 + 16;
        drawSubwindows(x, y, width, height);
        g2.setColor(Color.white);

        x += gp.tileSize - 24;
        y += gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.setFont(normalFont);

        drawText("¿Quieres", x, y, null);
        y += gp.tileSize / 2;
        drawText("cargar", x, y, null);
        y += gp.tileSize / 2;
        drawText("este", x, y, null);
        y += gp.tileSize / 2;
        drawText("Juego?", x, y, null);
        y += gp.tileSize * 2;

        drawText("Si", x + gp.tileSize, y, null);
        if (subNumCommand2 == 0) {
            if (gp.keyH.enterPressed) {

                save.loadGame();
                gp.ui.gameStateTransition = GamePanel.playState;

                gp.stopMusic();
                gp.playMusic(1);
                gp.keyH.enterPressed = false;

            }
            g2.drawImage(cursor, x, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);

        }
        y += gp.tileSize;
        drawText("No", x + gp.tileSize, y, null);
        if (subNumCommand2 == 1) {
            if (gp.keyH.enterPressed) {
                subState2 = 0;
                subNumCommand2 = 0;
                gp.keyH.enterPressed = false;
            }
            g2.drawImage(cursor, x, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
        }
        g2.setFont(normalFont);
        x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        y += gp.tileSize * 2;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;

        drawSubwindows(x, y, width, height);
        drawText("Tmp.", x + gp.tileSize, y + gp.tileSize / 2 + 10, blueMenu);
        drawText(formatSecondsToHoursMinutes(save.getPlayTime()), x + gp.tileSize, y + gp.tileSize + 20, null);

        x -= gp.tileSize - order;
        y += gp.tileSize * 2 + 10;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        x += gp.tileSize * 3;
        y += gp.tileSize + 10;
        drawNumberText(save.getParty().getGil(), x, y, null);
        drawText("G", x, y, blueMenu);
    }

    private void TradeSelector() {

        switch (subState) {
            case 0:
                tradeMenu();
                break;
            case 1:
                tradeBuyMenu();
                break;
            case 2:
                tradeSellMenu();
                break;
        }

        gp.keyH.enterPressed = false;
    }

    private void tradeMenu() {

        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;

        drawSubwindows(windowsX, windowsY, width, height);

        width = gp.tileSize * 3 - 5;
        height = gp.tileSize * 1 + 20;
        drawSubwindows(windowsX, windowsY, width, height);
        int textX = windowsX + gp.tileSize / 2;
        int textY = windowsY + 20 + gp.tileSize / 2;
        g2.setFont(normalFont);
        drawText("items", textX, textY, blueMenu);
        windowsX += gp.tileSize * 3;
        width = gp.tileSize * 15 - 10;
        drawSubwindows(windowsX, windowsY, width, height);
        drawText("¡Hola! ¿Qué deseas?", getXforCenteredText("¡Hola! ¿Qué deseas?"), textY, null);
        windowsX = 5;
        windowsY += height + 5;
        textY += windowsY;
        width = gp.tileSize * 11 - 5;
        height = gp.tileSize * 1 + 20;
        drawSubwindows(windowsX, windowsY, width, height);
        textX += gp.tileSize;
        drawText("CMPR.", textX, textY, null);

        if (numCommand == 0) {
            if (gp.keyH.enterPressed) {
                subState = 1;

            }
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
        }
        textX += gp.tileSize * 4 - 20;
        drawText("VENTA", textX, textY, null);
        if (numCommand == 1) {
            if (gp.keyH.enterPressed) {
                subState = 2;

            }
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
        }
        textX += gp.tileSize * 4 - 20;
        drawText("IRSE", textX, textY, null);
        if (numCommand == 2) {
            if (gp.keyH.enterPressed) {

                subState = 0;
                gp.ui.gameStateTransition = GamePanel.playState;
            }
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
        }

        windowsX += width;
        width = gp.screenWidth - width - 10;
        drawSubwindows(windowsX, windowsY, width, height);

        textX = gp.screenWidth - gp.tileSize * 2;
        drawNumberText(gp.party.getGil(), textX, textY, null);
        drawText("G", gp.screenWidth - gp.tileSize * 2, textY, blueMenu);
    }

    public void drawNumberText(int n, int x, int y, Color c) {

        String num = String.valueOf(n);
        FontMetrics fm = g2.getFontMetrics();
        int gilTextWidth = fm.stringWidth(num);

        x = x - gilTextWidth;
        drawText(num, x, y, null);

    }

    boolean removeItem = false;
    Item itemRemove;

    private void tradeSellMenu() {
        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;

        drawSubwindows(windowsX, windowsY, width, height);

        width = gp.tileSize * 3 - 5;
        height = gp.tileSize * 1 + 20;
        drawSubwindows(windowsX, windowsY, width, height);
        int textX = windowsX + gp.tileSize / 2;
        int textY = windowsY + 20 + gp.tileSize / 2;
        g2.setFont(normalFont);
        drawText("items", textX, textY, blueMenu);
        windowsX += gp.tileSize * 3;
        width = gp.tileSize * 15 - 10;
        drawSubwindows(windowsX, windowsY, width, height);

        if (subState2 == 0) {

            if (!menuStatus) {
                drawText("¿Qué tienes?", getXforCenteredText("¿Qué tienes?"), textY, null);
            }

        } else {

            if (!menuStatus) {
                drawText("¿Cuantos?", getXforCenteredText("¿Cuantos?"), textY, null);
            } else {
                drawText("¡Gracias!", getXforCenteredText("¡Gracias!"), textY, null);
                cont++;
                if (cont > 30) {
                    cont = 0;
                    menuStatus = false;
                    subState2 = 0;
                    subNumCommand = 0;
                    if (removeItem) {
                        gp.party.getInventory().remove(itemRemove);
                        removeItem = false;
                    }

                }
            }
        }

        windowsX = 5;
        windowsY += height + 10;
        textY += windowsY;
        width = gp.screenWidth - 10;
        height = gp.tileSize * 12 + 10;
        drawSubwindows(windowsX, windowsY, width, height);

        g2.setColor(Color.white);

        if (subState2 == 0) {
            windowsX += gp.tileSize + 5;
            windowsY += gp.tileSize;
            for (int i = 0; i < gp.party.getInventory().size(); i++) {

                drawText(gp.party.getInventory().get(i).getName(), windowsX, windowsY, null);
                drawText(":", windowsX + gp.tileSize * 6, windowsY, null);
                drawNumberText(gp.party.getInventory().get(i).getAmount(), windowsX + gp.tileSize * 7, windowsY, null);

                if (subNumCommand == i) {
                    if (gp.keyH.enterPressed) {
                        subState2 = 1;
                        subNumCommand2 = 1;
                        gp.keyH.enterPressed = false;
                    }

                    g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize,
                            null);
                }

                windowsY += gp.tileSize / 2 + 5;
            }

        } else {
            int x = 5;
            int y = gp.tileSize * 2 - 12;
            width = gp.tileSize * 12 - 10;
            height = gp.tileSize * 4;
            drawSubwindows(x, y, width, height);
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
            y += gp.tileSize;

            drawText(gp.party.getInventory().get(subNumCommand).getName(), x, y, null);
            x += gp.tileSize * 10;
            drawNumberText(subNumCommand2, x, y, null);
            y += gp.tileSize / 2 + 5;
            int price = (gp.party.getInventory().get(subNumCommand).getPrice() * subNumCommand2) / 2;
            drawNumberText(price, x, y, null);
            y += gp.tileSize / 2 + 5;
            drawText("G", x - gp.tileSize / 2, y, blueMenu);
            x = 5;
            y += gp.tileSize * 2;
            width = gp.screenWidth - 10;
            height = gp.tileSize * 3;
            drawSubwindows(x, y, width, height);
            x += gp.tileSize;
            drawText(gp.party.getInventory().get(subNumCommand).getDescription(), x, y + gp.tileSize, null);
            if (gp.keyH.enterPressed && !menuStatus) {
                itemRemove = gp.party.getInventory().get(subNumCommand);
                removeItem = gp.party.sellItem(gp.party.getInventory().get(subNumCommand), subNumCommand2);

                menuStatus = true;
                gp.playSE(7);
                gp.keyH.enterPressed = false;

            }

        }
        if (subState2 == 1) {
            int x = gp.screenWidth - gp.tileSize * 6;
            int y = textY - gp.tileSize;
            width = gp.tileSize * 6 - 10;
            height = gp.tileSize * 4;
            drawSubwindows(x, y, width, height);
            textX = gp.screenWidth - gp.tileSize * 4;
            textY += gp.tileSize;
            drawText("Tienes", textX, textY, blueMenu);
            textY += gp.tileSize / 2;
            textX += gp.tileSize * 3;

            drawNumberText(gp.party.getInventory().get(subNumCommand).getAmount(), textX, textY, blueMenu);
            textY -= gp.tileSize * 2 - 24;
        }
        textX = gp.screenWidth - gp.tileSize * 2;
        drawNumberText(gp.party.getGil(), textX, textY, null);
        drawText("G", gp.screenWidth - gp.tileSize * 2, textY, blueMenu);

        textX = gp.screenWidth - gp.tileSize * 4;

    }

    private void tradeBuyMenu() {
        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;

        drawSubwindows(windowsX, windowsY, width, height);

        width = gp.tileSize * 3 - 5;
        height = gp.tileSize * 1 + 20;
        drawSubwindows(windowsX, windowsY, width, height);
        int textX = windowsX + gp.tileSize / 2;
        int textY = windowsY + 20 + gp.tileSize / 2;
        g2.setFont(normalFont);
        drawText("items", textX, textY, blueMenu);
        windowsX += gp.tileSize * 3;
        width = gp.tileSize * 15 - 10;
        drawSubwindows(windowsX, windowsY, width, height);

        if (subState2 == 0) {

            if (!menuStatus) {
                drawText("¡Sirvete!", getXforCenteredText("¡Sirvete!"), textY, null);
            } else {

                drawText(menuMessage, getXforCenteredText(menuMessage), textY, null);
                cont++;
                cont++;
                if (cont > 30) {
                    cont = 0;
                    menuStatus = false;
                }
            }

        } else {

            if (!menuStatus) {
                drawText("¿Cuantos?", getXforCenteredText("¿Cuantos?"), textY, null);
            } else {
                drawText("¡Gracias!", getXforCenteredText("¡Gracias!"), textY, null);
                cont++;
                if (cont > 30) {
                    cont = 0;
                    menuStatus = false;
                    subState2 = 0;
                    subNumCommand2 = 0;

                }
            }
        }

        windowsX = 5;
        windowsY += height + 10;
        textY += windowsY;
        width = gp.screenWidth - 10;
        height = gp.tileSize * 12 + 10;
        drawSubwindows(windowsX, windowsY, width, height);

        g2.setColor(Color.white);

        if (subState2 == 0) {
            windowsX += gp.tileSize + 5;
            windowsY += gp.tileSize;
            for (int i = 0; i < itemNpc.getInventory().size(); i++) {

                drawText(itemNpc.getInventory().get(i).getName(), windowsX, windowsY, null);
                drawNumberText(itemNpc.getInventory().get(i).getPrice(), windowsX + gp.tileSize * 7, windowsY, null);

                if (subNumCommand == i) {
                    if (gp.keyH.enterPressed) {
                        int itemPrice = itemNpc.getInventory().get(i).getPrice();

                        if (itemNpc.getInventory().get(i).getAmount() < Item.maxAmount) {
                            if (gp.party.getGil() > itemPrice) {
                                subState2 = 1;
                                subNumCommand2 = 1;
                            } else {
                                menuStatus = true;
                                menuMessage = "No tienes suficientes Gils";
                            }
                        } else {
                            menuStatus = true;
                            menuMessage = "¡Tienes Muchos!";
                        }

                        gp.keyH.enterPressed = false;
                    }

                    g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize,
                            null);
                }

                windowsY += gp.tileSize / 2 + 5;
            }
        } else {
            int x = 5;
            int y = gp.tileSize * 2 - 12;
            width = gp.tileSize * 12 - 10;
            height = gp.tileSize * 4;
            drawSubwindows(x, y, width, height);
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
            y += gp.tileSize;

            drawText(itemNpc.getInventory().get(subNumCommand).getName(), x, y, null);
            x += gp.tileSize * 10;
            drawNumberText(subNumCommand2, x, y, null);
            y += gp.tileSize / 2 + 5;
            drawNumberText(itemNpc.getInventory().get(subNumCommand).getPrice() * subNumCommand2, x, y, null);
            y += gp.tileSize / 2 + 5;
            drawText("G", x - gp.tileSize / 2, y, blueMenu);
            x = 5;
            y += gp.tileSize * 2;
            width = gp.screenWidth - 10;
            height = gp.tileSize * 3;
            drawSubwindows(x, y, width, height);
            x += gp.tileSize;
            drawText(itemNpc.getInventory().get(subNumCommand).getDescription(), x, y + gp.tileSize, null);
            if (gp.keyH.enterPressed && !menuStatus) {
                gp.party.buyItem(itemNpc.getInventory().get(subNumCommand), subNumCommand2);
                menuStatus = true;
                gp.playSE(7);
                gp.keyH.enterPressed = false;

            }
        }

        gp.keyH.enterPressed = false;
        if (subState2 == 1) {
            int x = gp.screenWidth - gp.tileSize * 6;
            int y = textY - gp.tileSize;
            width = gp.tileSize * 6 - 10;
            height = gp.tileSize * 4;
            drawSubwindows(x, y, width, height);

        }
        textX = gp.screenWidth - gp.tileSize * 2;
        drawNumberText(gp.party.getGil(), textX, textY, null);
        drawText("G", gp.screenWidth - gp.tileSize * 2, textY, blueMenu);

        textX = gp.screenWidth - gp.tileSize * 4;
        textY += gp.tileSize;
        drawText("Tienes", textX, textY, blueMenu);
        textY += gp.tileSize / 2;
        textX += gp.tileSize * 3;
        drawNumberText(itemNpc.getInventory().get(subNumCommand).getAmount(), textX, textY, blueMenu);

    }

    private void drawDialogueToBattleSelector() {
        switch (subState) {
            case 0:
                drawDialogueToBattleMenu();
                break;
            case 1:
                levelSelector();
                break;
        }

        gp.keyH.enterPressed = false;
    }

    private void levelSelector() {
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 1;
        int width = gp.tileSize * 8;
        int height = gp.screenHeight - gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        int textX = x + gp.tileSize;
        int textY = y + gp.tileSize;
        drawText("Tutorial", textX, textY, null);
        if (subNumCommand == 0) {
            if (gp.keyH.enterPressed) {
                gp.aSetter.setTutorial();
                gp.ui.subState = 0;
                gp.stopMusic();
                gp.playMusic(0);

                gameStateTransition = GamePanel.battleState;
            }
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
        }
        textY += gp.tileSize;
        drawText("Level 1", textX, textY, null);
        if (subNumCommand == 1) {
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
        }
    }

    private void drawDialogueToBattleMenu() {
        drawDialogues();
        int x = gp.screenWidth - (gp.tileSize * 5 + 5);
        int y = gp.tileSize * 5 - 24;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 3 - gp.tileSize / 2;
        drawSubwindows(x, y, width, height);
        g2.setColor(Color.white);
        x += gp.tileSize / 2;
        y += gp.tileSize;
        drawText("Si", x, y, null);
        if (numCommand == 0) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed == true) {
                subState = 1;
                gp.party.resetATB();
                gp.battle.currentRound = 0;
            }
        }
        y += gp.tileSize;
        drawText("No", x, y, null);

        if (numCommand == 1) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed == true) {
                gp.gameState = GamePanel.playState;
                gameStateTransition = GamePanel.playState;
                numCommand = 0;
                subState = 0;

            }
        }

    }

    private void breakSelector() {

        switch (subState) {
            case 0:
                breakMenu();
                break;
            case 1:
                breakTransition();
                break;
        }

        gp.keyH.enterPressed = false;
    }

    public void breakMenu() {

        drawDialogues();
        int x = gp.screenWidth - (gp.tileSize * 5 + 5);
        int y = gp.tileSize * 5 - 24;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 3 - gp.tileSize / 2;
        drawSubwindows(x, y, width, height);
        g2.setColor(Color.white);
        x += gp.tileSize / 2;
        y += gp.tileSize;
        drawText("Si", x, y, null);
        if (numCommand == 0) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed == true) {

                subState = 1;
            }
        }
        y += gp.tileSize;
        drawText("No", x, y, null);
        if (numCommand == 1) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed == true) {
                numCommand = 0;
                subState = 0;
                gp.gameState = GamePanel.playState;

            }
        }

    }

    public void breakTransition() {

        if (animationState) {
            cont++;
        } else {
            cont--;
        }

        int alfa = cont * 4;
        if (alfa > 255) {
            alfa = 255;
        }
        g2.setColor(new Color(0, 0, 0, (alfa)));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (cont == 90) {
            animationState = false;
            gp.party.breakGroup();
            gp.playSE(4);
        }

        if (cont == 0 && !animationState) {

            subState = 0;
            gp.gameState = GamePanel.playState;
            cont = 0;
            animationState = true;

        }
    }

    public void drawDialogues() {
        int x = gp.tileSize * 1;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 4;
        drawSubwindows(x, y, width, height);

        g2.setFont(normalFont);
        x += gp.tileSize / 2;
        y += gp.tileSize;
        if (currentDialogue != null) {
            for (String line : currentDialogue.split("\n")) {
                drawText(line, x, y, null);
                y += gp.tileSize - 10;
            }
        }

    }

    public void drawTransition() {
        cont += 2;
        g2.setColor(new Color(0, 0, 0, (cont * 5)));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (cont == 50) {
            gp.gameState = GamePanel.playState;
            gp.currentMap = gp.eHandler.tpMap;
            gp.player.worldX = gp.eHandler.tpCol * gp.tileSize;
            gp.player.worldY = gp.eHandler.tpRow * gp.tileSize;
            cont = 0;
        }

    }

    public void drawGameStateTransition() {
        cont += 2;

        if (cont > 50) {
            g2.setColor(new Color(0, 0, 0, (255)));
        } else {
            g2.setColor(new Color(0, 0, 0, (cont * 4) + 20));
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        if (cont > 50) {

            if (gameStateTransition == GamePanel.titleState && cont > 60) {
                gp.gameState = gameStateTransition;
                gp.stopMusic();
                gp.resetGame();
                subState = 0;
                subState2 = 0;
                numCommand = 0;
                subNumCommand = 0;
                subNumCommand2 = 0;
                cont = 0;

            }

            if (gameStateTransition != GamePanel.titleState) {
                gp.gameState = gameStateTransition;
                subState = 0;
                subState2 = 0;
                numCommand = 0;
                subNumCommand = 0;
                subNumCommand2 = 0;
                cont = 0;
            }

        }

    }

    int order = 0;

    public void drawMenuWindows() {

        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;
        BufferedImage image = null;
        image = cursor;
        drawMenuPortrait(gp.party);

        drawMenu();

        switch (subState) {
            case 0:
                menuSelection();
                break;
            case 1:
                drawItemMenu();
                break;
            case 2:
                statsSelector();
                break;
            case 3:
                orderSelector();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                saveSelector();
                break;
            default:
                break;
        }

        gp.keyH.enterPressed = false;
    }

    public void drawMenuPortrait(Party party) {
        ArrayList<Character> lista = party.getParty();
        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;
        BufferedImage image = null;
        image = cursor;
        drawSubwindows(windowsX + order, windowsY, width, height);
        windowsX += gp.tileSize * 2;
        windowsY += gp.tileSize;
        g2.drawImage(lista.get(0).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(party, windowsX + order, windowsY, 0);

        windowsY += gp.tileSize * 4;
        g2.drawImage(lista.get(1).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(party, windowsX + order, windowsY, 1);

        windowsY += gp.tileSize * 4;
        g2.drawImage(lista.get(2).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(party, windowsX + order, windowsY, 2);
    }

    public void saveMenu() {
        int x = 5;
        int y = 5;
        int width = gp.screenWidth - 10;
        int height = gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        drawText("Guardar", getXforCenteredText("Guardar"), y + gp.tileSize * 2 - 20, null);
        y += gp.tileSize * 2;
        height = gp.tileSize * 4 - 5;
        drawSubwindows(x, y, width, height);

        if (!save1.isEmpty()) {
            if (subNumCommand == 0) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }

            drawSaveSlot(save1, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);
            if (subNumCommand == 0) {
                if (gp.keyH.enterPressed) {
                    gp.dataBase.saveData(1, 1);
                    save1.setSaveSlot();
                    gp.playSE(9);

                }
            }
        }
        if (subNumCommand == 0) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize * 4 - 5;
        height = gp.tileSize * 4 - 5;
        drawSubwindows(x, y, width, height);
        if (!save2.isEmpty()) {

            if (subNumCommand == 1) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }

            drawSaveSlot(save2, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);
            if (subNumCommand == 1) {
                if (gp.keyH.enterPressed) {
                    gp.dataBase.saveData(2, 2);
                    save2.setSaveSlot();
                    gp.playSE(9);
                }
            }
        }

        if (subNumCommand == 1) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize * 4 - 5;
        height = gp.tileSize * 4;
        drawSubwindows(x, y, width, height);

        if (!save3.isEmpty()) {

            if (subNumCommand == 2) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;

                }
            }
            drawSaveSlot(save3, x, y);
        } else {
            drawText("Vacio", x + gp.tileSize, y + gp.tileSize * 1 + 10, null);
            if (subNumCommand == 2) {
                if (gp.keyH.enterPressed) {
                    gp.dataBase.saveData(3, 3);
                    save3.setSaveSlot();
                    gp.playSE(9);
                }
            }
        }

        if (subNumCommand == 2) {
            g2.drawImage(cursor, x, y + gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        }

        gp.keyH.enterPressed = false;
    }

    private void saveSelector() {
        switch (subState2) {
            case 0:
                saveMenu();
                break;
            case 1:
                updateMenu();
                break;

        }
    }

    private void updateMenu() {
        switch (subNumCommand) {
            case 0:
                drawMenuPortrait(save1.getParty());
                updateInterface(save1);
                break;
            case 1:
                drawMenuPortrait(save2.getParty());
                updateInterface(save2);
                break;
            case 2:
                drawMenuPortrait(save3.getParty());
                updateInterface(save3);
                break;

        }

    }

    private void updateInterface(SaveSlot save) {
        int x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 7 + 16;
        drawSubwindows(x, y, width, height);
        g2.setColor(Color.white);

        x += gp.tileSize - 24;
        y += gp.tileSize * 2;
        g2.setColor(Color.white);
        g2.setFont(normalFont);

        drawText("¿Sobre-", x, y, null);
        y += gp.tileSize / 2;
        drawText("escribir", x, y, null);
        y += gp.tileSize / 2;
        drawText("Juego?", x, y, null);
        y += gp.tileSize * 2;

        drawText("Si", x + gp.tileSize, y, null);
        if (subNumCommand2 == 0) {
            if (gp.keyH.enterPressed) {

                gp.dataBase.updateSaveData(subNumCommand + 1, subNumCommand + 1);
                gp.playSE(9);
                switch (subNumCommand) {
                    case 0:
                        save1.setSaveSlot();
                        break;
                    case 1:
                        save2.setSaveSlot();
                        break;
                    case 2:
                        save3.setSaveSlot();
                        break;

                }

                subState2 = 0;
                subNumCommand2 = 0;
            }
            g2.drawImage(cursor, x, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);

        }
        y += gp.tileSize;
        drawText("No", x + gp.tileSize, y, null);
        if (subNumCommand2 == 1) {
            if (gp.keyH.enterPressed) {
                subState2 = 0;
                subNumCommand2 = 0;
            }
            g2.drawImage(cursor, x, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
        }
        g2.setFont(normalFont);
        x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        y += gp.tileSize * 2;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;

        drawSubwindows(x, y, width, height);
        drawText("Tmp.", x + gp.tileSize, y + gp.tileSize / 2 + 10, blueMenu);
        drawText(formatSecondsToHoursMinutes(save.getPlayTime()), x + gp.tileSize, y + gp.tileSize + 20, null);

        x -= gp.tileSize - order;
        y += gp.tileSize * 2 + 10;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        x += gp.tileSize * 3;
        y += gp.tileSize + 10;
        drawNumberText(save.getParty().getGil(), x, y, null);
        drawText("G", x, y, blueMenu);
    }

    private void drawSaveSlot(SaveSlot save, int x, int y) {
        ArrayList<Character> party = save.getParty().getParty();
        UtilityTool.sortByIndexGroup(party);

        drawText(party.get(0).getName(), x + gp.tileSize, y + gp.tileSize * 1 + 10, null);
        drawText("Time", x + gp.tileSize, y + gp.tileSize * 2 + 24, blueMenu);
        drawText(formatSecondsToHoursMinutes(save.getPlayTime()), x + gp.tileSize, y + gp.tileSize * 3 + 10, null);

        int textX = x + gp.tileSize * 5;

        for (Character character : party) {
            g2.drawImage(character.left, textX, y + gp.tileSize * 1, character.sizeWidth, character.sizeHeight,
                    null);
            textX += gp.tileSize * 2;
        }
        textX += gp.tileSize * 2;
        drawText("LV", textX, y + gp.tileSize * 1, blueMenu);
        drawNumberText(party.get(0).getLevel(), textX + gp.tileSize * 3, y + gp.tileSize * 1,
                null);
        textX += 30;
        drawNumberText(party.get(0).getHp(), textX, y + gp.tileSize * 2, null);
        drawText("/", textX, y + gp.tileSize * 2, null);
        drawNumberText(party.get(0).getMaxHp(), textX + gp.tileSize * 2, y + gp.tileSize * 2,
                null);
    }

    private void itemSelector() {
        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;

        drawSubwindows(windowsX, windowsY, width, height);

        width = gp.tileSize * 3;
        height = gp.tileSize * 1 + 20;
        drawSubwindows(windowsX, windowsY, width, height);
        int textX = windowsX + gp.tileSize / 2;
        int textY = windowsY + 20 + gp.tileSize / 2;
        g2.setFont(normalFont);
        drawText("items", textX, textY, blueMenu);
        windowsY += gp.tileSize * 1 + 30;
        width = gp.screenWidth - 10;
        height = gp.tileSize * 2 - 5;
        drawSubwindows(windowsX, windowsY, width, height);
        int descripcionTextX = textX;
        int descripcionTextY = windowsY + gp.tileSize;

        windowsY += gp.tileSize * 2 + 5;
        width = gp.screenWidth - 10;
        height = gp.tileSize * 10;
        drawSubwindows(windowsX, windowsY, width, height);
        drawText(gp.party.getInventory().size() + "", width - gp.tileSize / 2, descripcionTextY + gp.tileSize / 2 + 5,
                null);

        g2.setColor(Color.white);
        int contadorCursor = 0;
        contadorCursor = subNumCommand - 13;
        windowsX += gp.tileSize + 5;
        windowsY += gp.tileSize;
        for (int i = 0; i < 14 && i < gp.party.getInventory().size(); i++) {

            if (subNumCommand > 13) {
                drawText(gp.party.getInventory().get(i + contadorCursor).getName(), windowsX, windowsY, null);
                drawText(":", windowsX + gp.tileSize * 4, windowsY, null);
                drawText(gp.party.getInventory().get(i + contadorCursor).getAmount() + "", windowsX + gp.tileSize * 5,
                        windowsY, null);
            } else {
                drawText(gp.party.getInventory().get(i).getName(), windowsX, windowsY, null);
                drawText(":", windowsX + gp.tileSize * 4, windowsY, null);
                drawText(gp.party.getInventory().get(i).getAmount() + "", windowsX + gp.tileSize * 5, windowsY, null);
            }

            if (subNumCommand == i || (subNumCommand > 13 && subNumCommand == i + contadorCursor)) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;
                    order = 300;
                    gp.keyH.enterPressed = false;
                }
                drawText(gp.party.getInventory().get(i).getDescription(), descripcionTextX, descripcionTextY, null);
                g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize,
                        null);

            }

            windowsY += gp.tileSize / 2 + 5;
        }

        gp.keyH.enterPressed = false;

    }

    private void drawItemMenu() {
        switch (subState2) {
            case 0:
                itemSelector();
                break;
            case 1:
                itemUseSelector();
                break;
        }

    }

    private void itemUseSelector() {

        int x = 5;
        int y = 5;
        int width = gp.tileSize * 6 + 5;
        int height = gp.tileSize * 2 - 10;
        drawSubwindows(x, y, width, height);
        int textX = x + gp.tileSize * 2 - 24;
        int textY = y + gp.tileSize + 5;
        g2.setFont(arial40);
        drawText(gp.party.getInventory().get(subNumCommand).getName(), textX, textY, null);
        y += gp.tileSize * 2 + 5;
        drawSubwindows(x, y, width, height);
        textY += y;
        drawText("Tienes: " + gp.party.getInventory().get(subNumCommand).getAmount(), textX, textY, null);

        int windowsX = gp.tileSize + 300;
        int windowsY = gp.tileSize * 2;
        if (subNumCommand2 == 0) {
            if (gp.keyH.enterPressed) {
                if (!gp.party.getParty().get(subNumCommand2).useObject(gp.party.getInventory().get(subNumCommand))) {
                    subState2 = 0;
                    subNumCommand = 0;
                }
                gp.keyH.enterPressed = false;
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }
        ;
        windowsY += gp.tileSize * 4;
        if (subNumCommand2 == 1) {
            if (gp.keyH.enterPressed) {
                if (!gp.party.getParty().get(subNumCommand2).useObject(gp.party.getInventory().get(subNumCommand))) {
                    subState2 = 0;
                    subNumCommand = 0;
                }

                gp.keyH.enterPressed = false;
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }

        windowsY += gp.tileSize * 4;
        if (subNumCommand2 == 2) {
            if (gp.keyH.enterPressed) {
                if (!gp.party.getParty().get(subNumCommand2).useObject(gp.party.getInventory().get(subNumCommand))) {
                    subState2 = 0;
                    subNumCommand = 0;
                }
                gp.keyH.enterPressed = false;
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }
    }

    private void drawMenu() {
        int x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 7 + 16;
        drawSubwindows(x, y, width, height);
        g2.setColor(Color.white);

        x += gp.tileSize - 24;
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.setFont(arial40);
        drawText("Items", x, y, null);
        y += gp.tileSize;
        drawText("Stats", x, y, null);
        y += gp.tileSize;
        drawText("Order", x, y, null);
        y += gp.tileSize;
        drawText("Equip", x, y, null);
        y += gp.tileSize;
        drawText("Config", x, y, null);
        y += gp.tileSize;
        drawText("Save", x, y, null);
        y += gp.tileSize;
        drawText("Exit", x, y, null);
        g2.setFont(normalFont);
        x = gp.screenWidth - (gp.tileSize * 4 + 5) + order;
        y += gp.tileSize / 2;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;

        drawSubwindows(x, y, width, height);
        drawText("Tmp.", x + gp.tileSize, y + gp.tileSize / 2 + 10, blueMenu);
        drawText(formatSecondsToHoursMinutes(playTimer), x + gp.tileSize, y + gp.tileSize + 20, null);

        x -= gp.tileSize - order;
        y += gp.tileSize * 2 + 10;
        width = gp.tileSize * 5;
        height = gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        x += gp.tileSize * 3;
        y += gp.tileSize + 10;
        drawNumberText(gp.party.getGil(), x, y, null);
        drawText("G", x, y, blueMenu);
    }

    private void drawStats(int i) {
        drawSubwindows(5, 5, gp.screenWidth - 10, gp.screenHeight - 10);
        int x = 5;
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 1;
        drawSubwindows(x, y, width, height);
        int textX = x + 10 + gp.tileSize;
        int textY = y + 10 + gp.tileSize / 2;
        g2.setFont(normalFont);

        drawText("stats", textX, textY, blueMenu);

        int windowsX = 5;
        int windowsY = 5;

        windowsX += gp.tileSize * 2;
        windowsY += gp.tileSize * 2;
        g2.drawImage(gp.party.getParty().get(i).portrait, windowsX, windowsY, gp.tileSize * 2, gp.tileSize * 2, null);
        drawStatsPj(gp.party, windowsX, windowsY, i);
        textX = gp.tileSize;
        textY = windowsY + gp.tileSize * 3;
        drawText("Your Exp:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText(gp.party.getParty().get(i).getExp() + "", textX + gp.tileSize * 3, textY, null);
        textY += gp.tileSize + 10;
        drawText("For level up:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText((gp.party.getParty().get(i).getNextLevelExp() - gp.party.getParty().get(i).getExp()) + "",
                textX + gp.tileSize * 3, textY, null);

        textY += gp.tileSize * 2;
        drawText("Strenght", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getStrength() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Attack", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getAttack() + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Dexterity", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getDexterity() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Defense", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getDefense() + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Magic", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getMagic() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Mag.Def", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.party.getParty().get(i).getMagicDefense() + "", textX + gp.tileSize * 12, textY, null);

    }

    private void statsSelector() {
        if (menuStatus) {
            drawStats(subNumCommand);
        } else {
            int windowsX = gp.tileSize;
            int windowsY = gp.tileSize * 2;
            if (subNumCommand == 0) {
                if (gp.keyH.enterPressed) {
                    menuStatus = true;
                }
                g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
            }
            ;
            windowsY += gp.tileSize * 4;
            if (subNumCommand == 1) {
                if (gp.keyH.enterPressed) {
                    menuStatus = true;
                }
                g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
            }

            windowsY += gp.tileSize * 4;
            if (subNumCommand == 2) {
                if (gp.keyH.enterPressed) {
                    menuStatus = true;
                }
                g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
            }
        }

    }

    private void orderSelector() {
        int x = 5;
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 2 - 10;
        drawSubwindows(x, y, width, height);
        int textX = x + gp.tileSize / 2;
        int textY = y + gp.tileSize;
        g2.setFont(arial40);

        drawText("Order", textX, textY, blueMenu);

        int windowsX = 205 + gp.tileSize;
        int windowsY = gp.tileSize * 2;
        if (subNumCommand == 0 || (numIndexGroup == 0 && menuStatus)) {
            if (gp.keyH.enterPressed) {

                if (menuStatus) {
                    menuStatus = false;
                    gp.keyH.enterPressed = false;
                    Character.changeInexGroup(gp.party.getParty().get(numIndexGroup),
                            gp.party.getParty().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.party.getParty());
                    gp.player.getPlayerImagen();
                } else {
                    menuStatus = true;
                    numIndexGroup = 0;
                }
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }
        ;
        windowsY += gp.tileSize * 4;
        if (subNumCommand == 1 || (numIndexGroup == 1 && menuStatus)) {
            if (gp.keyH.enterPressed) {

                if (menuStatus) {
                    menuStatus = false;
                    gp.keyH.enterPressed = false;
                    Character.changeInexGroup(gp.party.getParty().get(numIndexGroup),
                            gp.party.getParty().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.party.getParty());
                    gp.player.getPlayerImagen();
                } else {
                    menuStatus = true;
                    numIndexGroup = 1;
                }
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }

        windowsY += gp.tileSize * 4;
        if (subNumCommand == 2 || (numIndexGroup == 2 && menuStatus)) {
            if (gp.keyH.enterPressed) {

                if (menuStatus) {
                    menuStatus = false;
                    gp.keyH.enterPressed = false;
                    Character.changeInexGroup(gp.party.getParty().get(numIndexGroup),
                            gp.party.getParty().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.party.getParty());
                    gp.player.getPlayerImagen();
                } else {
                    menuStatus = true;
                    numIndexGroup = 2;
                }
            }
            g2.drawImage(cursor, windowsX, windowsY, gp.tileSize, gp.tileSize, null);
        }

    }

    public void drawBattleMenu() {

        int windowsX = 5;
        int windowsY = gp.screenHeight - gp.tileSize * 4 - 5;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 4;

        drawSubwindows(windowsX, windowsY, width, height);

        windowsX = 5 + gp.tileSize * 5;
        width = gp.screenWidth - gp.tileSize * 5 - 10;

        drawSubwindows(windowsX, windowsY, width, height);

        drawBattlestats(gp.party);

    }

    int index = 9;

    public void drawBattlestats(Party party) {

        int windowsX = 5 + gp.tileSize * 6;
        int windowsY = gp.screenHeight - gp.tileSize * 3 + 10;

        if (gp.turnHandler.getCurrentCharacter() != null && !gp.battle.endBattle) {
            index = gp.turnHandler.getCurrentCharacter().getIndexGroup();
        } else {
            index = 9;
        }

        g2.setFont(UIBattleFont);
        drawText("HP", windowsX + gp.tileSize * 3, windowsY - gp.tileSize + 10, blueMenu);
        drawText("MP", windowsX + gp.tileSize * 6, windowsY - gp.tileSize + 10, blueMenu);
        g2.setFont(normalFont);

        drawText(party.getParty().get(0).getName(), windowsX, windowsY, null);
        drawNumberText(party.getParty().get(0).getHp(), windowsX + gp.tileSize * 4, windowsY, null);
        drawNumberText(party.getParty().get(0).getMp(), windowsX + gp.tileSize * 7, windowsY, null);
        party.getParty().get(0).atb.draw(g2, windowsX + gp.tileSize * 8, windowsY - 20);

        windowsY += gp.tileSize;

        drawText(party.getParty().get(1).getName(), windowsX, windowsY, null);
        drawNumberText(party.getParty().get(1).getHp(), windowsX + gp.tileSize * 4, windowsY, null);
        drawNumberText(party.getParty().get(1).getMp(), windowsX + gp.tileSize * 7, windowsY, null);
        party.getParty().get(1).atb.draw(g2, windowsX + gp.tileSize * 8, windowsY - 20);
        windowsY += gp.tileSize;

        drawText(party.getParty().get(2).getName(), windowsX, windowsY, null);
        drawNumberText(party.getParty().get(2).getHp(), windowsX + gp.tileSize * 4, windowsY, null);
        drawNumberText(party.getParty().get(2).getMp(), windowsX + gp.tileSize * 7, windowsY, null);
        party.getParty().get(2).atb.draw(g2, windowsX + gp.tileSize * 8, windowsY - 20);

        if (!gp.battle.endBattle) {

            if (index == 0) {
                drawAction();
                menuAction(party, index);

            }

            if (index == 1) {
                drawAction();
                menuAction(party, index);

            }

            if (index == 2) {
                drawAction();
                menuAction(party, index);

            }

        } else {

            windowsX = 5;
            windowsY = 5;
            int width = gp.screenWidth - 10;
            int height = gp.tileSize * 2;
            drawSubwindows(windowsX, windowsY, width, height);
            windowsX += gp.tileSize;
            windowsY += gp.tileSize + 10;
            drawText(currentMessage, windowsX, windowsY, null);
            if (gp.keyH.enterPressed) {
                if (battleMessage.isEmpty()) {
                    gp.battle.endBattle = false;
                    gameStateTransition = GamePanel.playState;
                    gp.party.resetATB();
                    gp.turnHandler.resetTurnState();
                    gp.turnHandler.resetBattle();
                    gp.stopMusic();
                    gp.playMusic(1);
                    currentMessage = null;
                } else {
                    currentMessage = battleMessage.poll();
                }

            }

        }

        gp.keyH.enterPressed = false;
    }

    public void menuAction(Party party, int index) {
        switch (subState) {
            case 0:
                actionSelector(party, index);
                break;
            case 1:
                attackSelector(party, index);
            default:
                break;
        }
    }

    private void attackSelector(Party party, int index) {
        ArrayList<Enemy> enemies = gp.battle.level.get(gp.battle.currentRound);
        for (int i = 0; i < enemies.size(); i++) {

            if (subNumCommand == i) {
                int x = enemies.get(i).defaultX + enemies.get(i).sizeWidth;
                int y = enemies.get(i).defaultY + enemies.get(i).sizeHeight / 2;
                g2.drawImage(cursorInvert, x, y, gp.tileSize, gp.tileSize, null);
                if (gp.keyH.enterPressed) {
                    subState = 0;
                    subNumCommand = 0;
                    party.getParty().get(index).setTarget(enemies.get(i));
                    party.getParty().get(index).action = true;
                    party.getParty().get(index).characterAction = 1;
                    gp.turnHandler.addGameQueue(party.getParty().get(index));
                    gp.turnHandler.nextTurnCharacter();

                }
            }

        }
    }

    private void actionSelector(Party party, int index) {
        int windowsX = 5 + gp.tileSize / 2;
        int windowsY = gp.screenHeight - gp.tileSize * 4 - 5;

        windowsX += gp.tileSize;
        windowsY += gp.tileSize;

        if (numCommand == 0) {
            g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed) {
                subState = 1;

            }
        }
        windowsY += gp.tileSize;

        if (numCommand == 1) {
            g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed) {
                party.getParty().get(index).atb.setValue(0);
                party.getParty().get(index).atb.full = false;
                gp.turnHandler.nextTurnCharacter();
                numCommand = 0;
                gp.keyH.enterPressed = false;
            }

        }
        windowsY += gp.tileSize;

        if (numCommand == 2) {
            g2.drawImage(cursor, windowsX - gp.tileSize, windowsY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed) {
                party.getParty().get(index).atb.setValue(0);
                party.getParty().get(index).atb.full = false;
                gp.turnHandler.nextTurnCharacter();

                numCommand = 0;
                gp.keyH.enterPressed = false;
            }
        }
    }

    private void drawAction() {
        int windowsX = 5 + gp.tileSize / 2;
        int windowsY = gp.screenHeight - gp.tileSize * 4 - 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 4;
        drawSubwindows(windowsX, windowsY, width, height);
        windowsX += gp.tileSize;
        windowsY += gp.tileSize;
        drawText("Attack", windowsX, windowsY, null);
        windowsY += gp.tileSize;
        drawText("Magic", windowsX, windowsY, null);

        windowsY += gp.tileSize;
        drawText("Items", windowsX, windowsY, null);

    }

    public void menuSelection() {

        int x = gp.screenWidth - (gp.tileSize * 4 + 5);
        int y = 5;

        x += gp.tileSize - 24;
        y += gp.tileSize;

        if (numCommand == 0) {
            if (gp.keyH.enterPressed) {
                subState = 1;
            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize;
        if (numCommand == 1) {
            if (gp.keyH.enterPressed) {
                subState = 2;
            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);

        }

        y += gp.tileSize;

        if (numCommand == 2) {
            if (gp.keyH.enterPressed) {
                order = 200;
                subState = 3;
            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize;

        if (numCommand == 3) {

            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize;
        if (numCommand == 4) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize;
        if (numCommand == 5) {
            if (gp.keyH.enterPressed) {
                subState = 6;
            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize;
        if (numCommand == 6) {
            if (gp.keyH.enterPressed) {

                gp.ui.gameStateTransition = GamePanel.titleState;

            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }

        gp.keyH.enterPressed = false;

    }

    public void drawSubwindows(int x, int y, int width, int height) {
        int gradientStartY = y;
        int gradientEndY = y + height;
        GradientPaint color = new GradientPaint(x, gradientStartY, new Color(100, 99, 171, 255), x, gradientEndY,
                new Color(0, 0, 90, 255), false);
        g2.setPaint(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 20, 20);

    }

    public void drawText(String text, int x, int y, Color c) {
        if (c == null) {
            c = Color.WHITE;
        }
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(c);
        g2.drawString(text, x, y);
    }

    public void drawStatsPj(Party party, int windowsX, int windowsY, int i) {

        int textX = windowsX + gp.tileSize * 3;
        int textY = windowsY + 20;
        g2.setFont(normalFont);
        drawText(party.getParty().get(i).getName(), textX, textY, null);
        textY += 40;
        textX += 20;
        drawText("LV", textX, textY, blueMenu);
        drawText(party.getParty().get(i).getLevel() + "", textX + gp.tileSize * 2, textY, null);
        textY += 25;
        drawText("PV", textX, textY, blueMenu);
        String vida = party.getParty().get(i).getHp() + "/" + party.getParty().get(i).getMaxHp();
        drawText(vida, textX + gp.tileSize * 2, textY, null);

        textY += 25;
        drawText("PM", textX, textY, blueMenu);
        String mp = party.getParty().get(i).getMp() + "/" + party.getParty().get(i).getMaxMp();
        drawText(mp, textX + gp.tileSize * 2, textY, null);

    }

}
