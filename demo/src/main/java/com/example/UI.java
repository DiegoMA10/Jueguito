package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.example.Items.Item;
import com.example.entity.Character;

import com.example.entity.npc.NPC_Item;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private double playTimer;
    private BufferedImage cursor;
    private BufferedImage titleScreen;
    public int subState = 0;
    public int subState2 = 0;
    public int numCommand = 0;
    public int subNumCommand = 0;
    public int subNumCommand2 = 0;
    public int numIndexGroup;
    public boolean menuStatus = false;

    private Color blueMenu = new Color(0, 223, 223);
    private Font arial40;
    private Font normalFont;
    public String currentDialogue;
    private Boolean animationState = true;
    int cont = 0;
    public NPC_Item itemNpc;
    private String menuMessage;

    public UI(GamePanel gp) {
        this.gp = gp;
        getImagen();
        arial40 = new Font("Arial", Font.PLAIN, 40);
        normalFont = new Font("Arial", Font.BOLD, 30);

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

    public void getImagen() {
        cursor = setUp("cursor");

        try {
            titleScreen = ImageIO.read(getClass().getResourceAsStream("image/UI/titleScreen.png"));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        playTimer += 1.0 / 60;
        g2.setFont(arial40);

        switch (gp.gameState) {
            case GamePanel.titleState:
                drawTitleScreen();
                break;
            case GamePanel.menuState:
                drawMenuWindows();
                break;
            case GamePanel.playState:
                break;
            case GamePanel.battleState:
                drawBattleMenu();
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
                break;

        }

    }

    private void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setColor(Color.BLACK);
        g2.drawImage(titleScreen, 0, 0, gp.screenWidth, gp.screenWidth, null);
        String text = "Start Game";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 + gp.tileSize * 2;

        g2.drawString(text, x, y);
        if (numCommand == 0) {
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.drawString(text, x, y);
        if (numCommand == 1) {
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

        text = "Exit";
        x = getXforCenteredText(text);
        y += gp.tileSize;

        g2.drawString(text, x, y);
        if (numCommand == 2) {
            g2.drawImage(cursor, x - gp.tileSize - 10, y - gp.tileSize / 2 - 5, gp.tileSize, gp.tileSize, null);
        }

    }

    public int getXforCenteredText(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght / 2;
        return x;
    }

    private void TradeSelector() {

        switch (subState) {
            case 0:
                tradeMenu();
                break;
            case 1:
                tradeBuyMenu();
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
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
        }
        textX += gp.tileSize * 4 - 20;
        drawText("IRSE", textX, textY, null);
        if (numCommand == 2) {
            if (gp.keyH.enterPressed) {

                numCommand = 0;
                subState = 0;
                gp.gameState = GamePanel.playState;
            }
            g2.drawImage(cursor, textX - gp.tileSize, textY - gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
        }

        windowsX += width;
        width = gp.screenWidth - width - 10;
        drawSubwindows(windowsX, windowsY, width, height);

        String gilText = String.valueOf(gp.group.getGil());
        FontMetrics fm = g2.getFontMetrics();
        int gilTextWidth = fm.stringWidth(gilText);

        textX = gp.screenWidth - gp.tileSize * 2; // Ajusta el espaciado según necesites
        drawNumberText(gp.group.gil, textX, textY, null);
        drawText("G", gp.screenWidth - gp.tileSize * 2, textY, blueMenu);
    }

    private void drawNumberText(int n, int x, int y, Color c) {

        String num = String.valueOf(n);
        FontMetrics fm = g2.getFontMetrics();
        int gilTextWidth = fm.stringWidth(num);

        x = x - gilTextWidth;
        drawText(num, x, y, null);

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
                    subNumCommand = 0;
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
                            if (gp.group.getGil() > itemPrice) {
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
                gp.group.buyItem(itemNpc.getInventory().get(subNumCommand), subNumCommand2);
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
        drawNumberText(gp.group.getGil(), textX, textY, null);
        drawText("G", gp.screenWidth - gp.tileSize * 2, textY, blueMenu);

        textX = gp.screenWidth - gp.tileSize * 4;
        textY += gp.tileSize;
        drawText("Tienes", textX, textY, blueMenu);
        textY += gp.tileSize / 2;
        textX += gp.tileSize * 3;
        drawNumberText(itemNpc.getInventory().get(subNumCommand).getAmount(), textX, textY, blueMenu);

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
            gp.group.breakGroup();
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

        for (String line : currentDialogue.split("\n")) {
            drawText(line, x, y, Color.white);
            y += gp.tileSize - 10;
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

    int order = 0;

    public void drawMenuWindows() {

        int windowsX = 5;
        int windowsY = 5;
        int width = gp.screenWidth - 10;
        int height = gp.screenHeight - 10;
        BufferedImage image = null;
        image = cursor;
        drawSubwindows(windowsX + order, windowsY, width, height);
        windowsX += gp.tileSize * 2;
        windowsY += gp.tileSize;
        g2.drawImage(gp.group.getGroup().get(0).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 0);

        windowsY += gp.tileSize * 4;
        g2.drawImage(gp.group.getGroup().get(1).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 1);

        windowsY += gp.tileSize * 4;
        g2.drawImage(gp.group.getGroup().get(2).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 2);
        windowsX = gp.screenWidth - (gp.tileSize * 4 + 5);
        windowsY = gp.screenHeight - gp.tileSize * 4;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;
        drawSubwindows(windowsX + order, windowsY, width, height);
        windowsX += gp.tileSize;
        windowsY += gp.tileSize;
        drawText("gil: " + gp.group.gil, windowsX + order, windowsY, null);

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
                break;
            default:
                break;
        }

        gp.keyH.enterPressed = false;
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
        drawText(gp.group.getInventory().size() + "", width - gp.tileSize / 2, descripcionTextY + gp.tileSize / 2 + 5,
                null);

        g2.setColor(Color.white);
        int contadorCursor = 0;
        contadorCursor = subNumCommand - 13;
        windowsX += gp.tileSize + 5;
        windowsY += gp.tileSize;
        for (int i = 0; i < 14 && i < gp.group.getInventory().size(); i++) {

            if (subNumCommand > 13) {
                drawText(gp.group.getInventory().get(i + contadorCursor).getName(), windowsX, windowsY, null);
                drawText(":", windowsX + gp.tileSize * 4, windowsY, null);
                drawText(gp.group.getInventory().get(i + contadorCursor).getAmount() + "", windowsX + gp.tileSize * 5,
                        windowsY, null);
            } else {
                drawText(gp.group.getInventory().get(i).getName(), windowsX, windowsY, null);
                drawText(":", windowsX + gp.tileSize * 4, windowsY, null);
                drawText(gp.group.getInventory().get(i).getAmount() + "", windowsX + gp.tileSize * 5, windowsY, null);
            }

            if (subNumCommand == i || (subNumCommand > 13 && subNumCommand == i + contadorCursor)) {
                if (gp.keyH.enterPressed) {
                    subState2 = 1;
                    order = 300;
                    gp.keyH.enterPressed = false;
                }
                drawText(gp.group.getInventory().get(i).getDescription(), descripcionTextX, descripcionTextY, null);
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
        drawText(gp.group.getInventory().get(subNumCommand).getName(), textX, textY, null);
        y += gp.tileSize * 2 + 5;
        drawSubwindows(x, y, width, height);
        textY += y;
        drawText("Tienes: " + gp.group.getInventory().get(subNumCommand).getAmount(), textX, textY, null);

        int windowsX = gp.tileSize + 300;
        int windowsY = gp.tileSize * 2;
        if (subNumCommand2 == 0) {
            if (gp.keyH.enterPressed) {
                if (!gp.group.getGroup().get(subNumCommand2).useObject(gp.group.getInventory().get(subNumCommand))) {
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
                if (!gp.group.getGroup().get(subNumCommand2).useObject(gp.group.getInventory().get(subNumCommand))) {
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
                if (!gp.group.getGroup().get(subNumCommand2).useObject(gp.group.getInventory().get(subNumCommand))) {
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
        g2.setFont(arial40);
        x += gp.tileSize - 24;
        y += gp.tileSize;
        g2.setColor(Color.white);
        g2.setFont(arial40);
        drawText("Items", x, y, null);
        y += gp.tileSize;
        drawText("Stats", x, y, null);
        y += gp.tileSize;
        drawText("Equip", x, y, null);
        y += gp.tileSize;
        drawText("Order", x, y, null);
        y += gp.tileSize;
        drawText("Config", x, y, null);
        y += gp.tileSize;
        drawText("Save", x, y, null);
        y += gp.tileSize;
        drawText("Exit", x, y, null);
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
        g2.drawImage(gp.group.getGroup().get(i).portrait, windowsX, windowsY, gp.tileSize * 2, gp.tileSize * 2, null);
        drawStatsPj(windowsX, windowsY, i);
        textX = gp.tileSize;
        textY = windowsY + gp.tileSize * 3;
        drawText("Your Exp:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText(gp.group.getGroup().get(i).getExp() + "", textX + gp.tileSize * 3, textY, null);
        textY += gp.tileSize + 10;
        drawText("For level up:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText(gp.group.getGroup().get(i).getNextLevelExp() + "", textX + gp.tileSize * 3, textY, null);

        textY += gp.tileSize * 2;
        drawText("Strenght", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getStrength() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Attack", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getAttack() + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Dexterity", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getDexterity() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Defense", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getDefense() + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Magic", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getMagic() + "", textX + gp.tileSize * 4, textY, null);
        drawText("Mag.Def", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getMagicDefense() + "", textX + gp.tileSize * 12, textY, null);

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
                    Character.changeInexGroup(gp.group.getGroup().get(numIndexGroup),
                            gp.group.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.group.getGroup());
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
                    Character.changeInexGroup(gp.group.getGroup().get(numIndexGroup),
                            gp.group.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.group.getGroup());
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
                    Character.changeInexGroup(gp.group.getGroup().get(numIndexGroup),
                            gp.group.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.group.getGroup());
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
        int windowsY = gp.screenHeight - gp.tileSize * 3 - 5;
        int width = gp.screenWidth - 10;
        int height = gp.tileSize * 3;

        BufferedImage image = null;
        image = cursor;
        drawSubwindows(windowsX, windowsY, width, height);

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
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize;

        if (numCommand == 3) {
            if (gp.keyH.enterPressed) {
                order = 200;
                subState = 3;
            }
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }

        y += gp.tileSize;
        if (numCommand == 4) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize;
        if (numCommand == 5) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        }
        y += gp.tileSize;
        if (numCommand == 6) {
            if (gp.keyH.enterPressed) {
                gp.stopMusic();
                gp.playMusic(6);
                numCommand = 0;

                gp.gameState = GamePanel.titleState;
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

    public void drawStatsPj(int windowsX, int windowsY, int i) {
        int textX = windowsX + gp.tileSize * 3;
        int textY = windowsY + 20;
        g2.setFont(normalFont);
        drawText(gp.group.getGroup().get(i).getName(), textX, textY, null);
        textY += 40;
        textX += 20;
        drawText("LV", textX, textY, blueMenu);
        drawText(gp.group.getGroup().get(i).getLevel() + "", textX + gp.tileSize * 2, textY, null);
        textY += 25;
        drawText("PV", textX, textY, blueMenu);
        String vida = gp.group.getGroup().get(i).getHp() + "/" + gp.group.getGroup().get(i).getMaxHp();
        drawText(vida, textX + gp.tileSize * 2, textY, null);

        textY += 25;
        drawText("PM", textX, textY, blueMenu);
        String mp = gp.group.getGroup().get(i).getMp() + "/" + gp.group.getGroup().get(i).getMaxMp();
        drawText(mp, textX + gp.tileSize * 2, textY, null);

    }

}
