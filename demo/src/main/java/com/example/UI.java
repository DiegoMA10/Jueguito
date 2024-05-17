package com.example;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.example.entity.Character;
import com.example.entity.Entity;

public class UI {
    private GamePanel gp;
    private Graphics2D g2;
    private double playTimer;
    private BufferedImage cursor;
    private BufferedImage titleScreen;
    public int subState = 0;
    public int numCommand = 0;
    public int subNumCommand = 0;
    public int numIndexGroup;
    public boolean menuStatus = false;
    private Color blueMenu = new Color(0, 223, 223);
    private Font arial40;
    private Font normalFont;
    public String currentDialogue;
    int cont = 0;
    Entity npc;

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
            case GamePanel.titleState:drawTitleScreen();break;
            case GamePanel.menuState:drawMenuWindows();break;
            case GamePanel.playState: break;
            case GamePanel.battleState:drawBattleMenu(); break;
            case GamePanel.transitionState:drawTransition();break;
            case GamePanel.dialogueState: drawDialogues(); break;
            case GamePanel.breakState:breakOptions();break;

        }

    }

    private void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.BLACK);
        g2.drawImage(titleScreen, 0, 0,gp.screenWidth,gp.screenWidth,null);
        String text = "New Game";
        int x = getXforCenteredText(text);
        int y=gp.screenHeight/2+gp.tileSize*2;
       
        g2.drawString(text, x, y);
        if (numCommand==0) {
            g2.drawImage(cursor, x-gp.tileSize-10, y-gp.tileSize/2-5,gp.tileSize,gp.tileSize,null);
        }

        text = "Load Game";
        x = getXforCenteredText(text);
        y+=gp.tileSize;
       
        g2.drawString(text, x, y);
        if (numCommand==1) {
            g2.drawImage(cursor, x-gp.tileSize-10, y-gp.tileSize/2-5,gp.tileSize,gp.tileSize,null);
        }
      

        text = "Exit";
        x = getXforCenteredText(text);
        y+=gp.tileSize;
       
        g2.drawString(text, x, y);
        if (numCommand==2) {
            g2.drawImage(cursor, x-gp.tileSize-10, y-gp.tileSize/2-5,gp.tileSize,gp.tileSize,null);
        }
      
      
    }

    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 -lenght/2;
        return x;
    }

    public Boolean state = true;

    private void breakOptions() {

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
        drawText("dormir", x, y, null);
        if (numCommand == 0) {
            g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 20, gp.tileSize, gp.tileSize, null);
            if (gp.keyH.enterPressed == true) {

                subState = 1;
            }
        }
        y += gp.tileSize;
        drawText("irse", x, y, null);
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

        if (state) {
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
            state = false;
            gp.grupo.breakGroup();
            gp.playSE(4);
        }

        if (cont == 0 && !state) {

            subState = 0;
            gp.gameState = GamePanel.playState;
            cont = 0;
            state = true;

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
        g2.drawImage(gp.grupo.getGroup().get(0).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 0);

        windowsY += gp.tileSize * 4;
        g2.drawImage(gp.grupo.getGroup().get(1).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 1);

        windowsY += gp.tileSize * 4;
        g2.drawImage(gp.grupo.getGroup().get(2).portrait, windowsX + order, windowsY, gp.tileSize * 2, gp.tileSize * 2,
                null);
        drawStatsPj(windowsX + order, windowsY, 2);
        windowsX = gp.screenWidth - (gp.tileSize * 4 + 5);
        windowsY = gp.screenHeight - gp.tileSize * 4;
        width = gp.tileSize * 4;
        height = gp.tileSize * 2;
        drawSubwindows(windowsX + order, windowsY, width, height);
        windowsX += gp.tileSize;
        windowsY += gp.tileSize;
        drawText("gil: " + gp.grupo.gil, windowsX + order, windowsY, null);

        drawMenu();

        switch (subState) {
            case 0:
                menuSelection();
                break;
            case 1:
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
        g2.drawImage(gp.grupo.getGroup().get(i).portrait, windowsX, windowsY, gp.tileSize * 2, gp.tileSize * 2, null);
        drawStatsPj(windowsX, windowsY, i);
        textX = gp.tileSize;
        textY = windowsY + gp.tileSize * 3;
        drawText("Your Exp:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText(gp.grupo.getGroup().get(i).exp + "", textX + gp.tileSize * 3, textY, null);
        textY += gp.tileSize + 10;
        drawText("For level up:", textX, textY, blueMenu);
        textY += gp.tileSize - 10;
        drawText(gp.grupo.getGroup().get(i).nextLevelExp + "", textX + gp.tileSize * 3, textY, null);

        textY += gp.tileSize * 2;
        drawText("Strenght", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).strength + "", textX + gp.tileSize * 4, textY, null);
        drawText("Attack", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).attack + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Dexterity", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).dexterity + "", textX + gp.tileSize * 4, textY, null);
        drawText("Defense", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).defense + "", textX + gp.tileSize * 12, textY, null);
        textY += gp.tileSize - 10;
        drawText("Magic", textX, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 3, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).magic + "", textX + gp.tileSize * 4, textY, null);
        drawText("Mag.Def", textX + gp.tileSize * 8, textY, blueMenu);
        drawText("··", textX + gp.tileSize * 11, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).magicDefense + "", textX + gp.tileSize * 12, textY, null);

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
        int height = gp.tileSize * 2;
        drawSubwindows(x, y, width, height);
        int textX = x + gp.tileSize / 2;
        int textY = y + gp.tileSize;
        g2.setFont(arial40);

        drawText("Order", textX, textY, null);

        int windowsX = 205 + gp.tileSize;
        int windowsY = gp.tileSize * 2;
        if (subNumCommand == 0 || (numIndexGroup == 0 && menuStatus)) {
            if (gp.keyH.enterPressed) {

                if (menuStatus) {
                    menuStatus = false;
                    gp.keyH.enterPressed = false;
                    Character.changeInexGroup(gp.grupo.getGroup().get(numIndexGroup),
                            gp.grupo.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.grupo.getGroup());
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
                    Character.changeInexGroup(gp.grupo.getGroup().get(numIndexGroup),
                            gp.grupo.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.grupo.getGroup());
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
                    Character.changeInexGroup(gp.grupo.getGroup().get(numIndexGroup),
                            gp.grupo.getGroup().get(subNumCommand));
                    UtilityTool.sortByIndexGroup(gp.grupo.getGroup());
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

    String[] prueba2 = { "hola", "hola1", "hola2", "hola3", "hola4", "hola5", "hola6", "hola7" };

    String[] prueba = { "hola", "hola1", "hola2", "hola3", "hola4", "hola5", "hola6", "hola7", "hola8", "hola9",
            "hola10", "hola11'", "hola¡", "hol3", "hola7", "hola23", "hola32", "hola12", "hola423", "hola423", "hola4",
            "hola324", "hola34", "hola34", "ultima" };

    public void pruebas() {
        // scroll menu
        int x = gp.screenWidth - (gp.tileSize * 4 + 5);
        int y = 5;
        int width = gp.tileSize * 4;
        int height = gp.tileSize * 6 + 16;
        drawSubwindows(x, y, width, height);
        x += gp.tileSize - 24;
        y += gp.tileSize;

        g2.setColor(Color.white);
        int contadorCursor = 0;
        contadorCursor = numCommand - 5;

        for (int i = 0; i < 6; i++) {

            if (numCommand > 5) {
                g2.drawString(prueba[contadorCursor + i], x, y);
            } else {
                g2.drawString(prueba[i], x, y);
            }

            if (numCommand == i || (numCommand > 5 && numCommand == i + contadorCursor)) {

                g2.drawImage(cursor, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);

            }

            y += gp.tileSize;
        }
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
        drawText(gp.grupo.getGroup().get(i).name, textX, textY, null);
        textY += 40;
        textX += 20;
        drawText("LV", textX, textY, blueMenu);
        drawText(gp.grupo.getGroup().get(i).level + "", textX + gp.tileSize * 2, textY, null);
        textY += 25;
        drawText("PV", textX, textY, blueMenu);
        String vida = gp.grupo.getGroup().get(i).hp + "/" + gp.grupo.getGroup().get(i).MaxHp;
        drawText(vida, textX + gp.tileSize * 2, textY, null);

        textY += 25;
        drawText("PM", textX, textY, blueMenu);
        String mp = gp.grupo.getGroup().get(i).mp + "/" + gp.grupo.getGroup().get(i).MaxMp;
        drawText(mp, textX + gp.tileSize * 2, textY, null);

    }
}
