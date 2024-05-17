package com.example;

import com.example.entity.Entity;

public class EventHandler {

    // dormir col 16 row 17..ext col 17 row 14
    // potis col 32 row 14..ext col 28 row 37
    // armas y hechizos izquierda(col 15 row 41)..ext(col 12 row 37) derecha (col 26
    // row 41)..ext(col 19 row 37)
    GamePanel gp;
    EventRect[][][] eventRect;
    int tpMap, tpCol, tpRow;
    int previousEventX, previousEventY;
    boolean canTouchEvent;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        for (int map = 0; map < gp.maxMap; map++) {

            for (int row = 0; row < gp.maxWorldRow; row++) {

                for (int col = 0; col < gp.maxWorldCol; col++) {

                    eventRect[map][col][row] = new EventRect();
                    eventRect[map][col][row].x = 23;
                    eventRect[map][col][row].y = 23;
                    eventRect[map][col][row].width = 2;
                    eventRect[map][col][row].height = 2;
                    eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
                    eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
                }

            }

        }

    }

    public void checkEvent() {

        int x = Math.abs(gp.player.worldX - previousEventX);
        int y = Math.abs(gp.player.worldY - previousEventY);
        int distancia = Math.max(x, y);
        if (distancia > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(0, 19, 37, "any")) {teleport(1, 26, 40);}
            if (hit(1, 26, 41, "any")) {teleport(0, 19, 37);}
            if (hit(0, 12, 37, "any")) {teleport(1, 15, 40);}
            if (hit(1, 15, 41, "any")) {teleport(0, 12, 37);}
            if (hit(0, 28, 37, "any")) {teleport(1, 32, 13);}
            if (hit(1, 32, 14, "any")) {teleport(0, 28, 37);}
            if (hit(0, 17, 14, "any")) {teleport(1, 16, 16);}
            if (hit(1, 16, 17, "any")) {teleport(0, 17, 14);}
            if (hit(1, 15, 35, "up")) {speak(gp.npc[1][0]);}
            if (hit(1, 26, 35, "up")) {speak(gp.npc[1][1]);}
            if (hit(1, 16, 12, "up")) {speak(gp.npc[1][2]);}
            if (hit(1, 14, 10, "right")) {speak(gp.npc[1][2]);}
            if (hit(1, 32, 10, "up")) {speak(gp.npc[1][3]);}
            if (hit(1, 34, 8, "left")) {speak(gp.npc[1][3]);}

        }
    }

    private void speak(Entity entity) {

        if (gp.keyH.enterPressed == true) {
            gp.gameState = GamePanel.dialogueState;

            entity.speak();

        }
    }

    public Boolean hit(int map, int col, int row, String direction) {
        Boolean hit = false;
        if (gp.currentMap == map) {
            gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
            gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.hitBox.intersects(eventRect[map][col][row])) {
                if (gp.player.direction.equals(direction) || direction.equals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.hitBox.x = gp.player.hitboxDefaultX;
            gp.player.hitBox.y = gp.player.hitboxDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;

        }

        return hit;
    }

    public void teleport(int map, int col, int row) {
        tpMap = map;
        tpCol = col;
        tpRow = row;
        gp.gameState = GamePanel.transitionState;
        canTouchEvent = false;
        gp.playSE(2);
    }

}
