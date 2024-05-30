package com.example.tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.midi.Patch;

import com.example.GamePanel;
import com.example.UtilityTool;

import javafx.stage.Screen;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapaNumber;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[200];
        mapaNumber = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("src/main/resources/com/example/worlds/mapa.txt", 0);
        loadMap("src/main/resources/com/example/worlds/interior.txt", 1);
        loadMap("src/main/resources/com/example/worlds/mapaSup.txt", 2);

    }

    public void setUp(int i, String path, boolean collision) {
        UtilityTool tool = new UtilityTool();

        try {
            tile[i] = new Tile();
            tile[i].image = ImageIO.read(getClass().getResourceAsStream("/com/example/image/tiles/" + path));
            tile[i].image = tool.imageScale(tile[i].image, gp.tileSize, gp.tileSize);
            tile[i].collision = collision;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getTileImage() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/resources/com/example/worlds/dataTile.txt"))) {
            String line = "";
            int cont = 0;
            String path = "";
            String collision;

            while ((line = br.readLine()) != null) {
                path = line;
                collision = br.readLine();

                if (collision.equals("true")) {
                    setUp(cont, path, true);
                } else {
                    setUp(cont, path, false);
                }
                cont++;

            }
        } catch (IOException e) {
           
            e.printStackTrace();
        }

    }

    public void loadMap(String worldPath, int numberMap) {

        try (BufferedReader br = new BufferedReader(new FileReader(worldPath))) {

            for (int row = 0; row < gp.maxWorldRow; row++) {

                String line = br.readLine();
                String[] tileNumer = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol; col++) {

                    int n = Integer.parseInt(tileNumer[col]);

                    mapaNumber[numberMap][col][row] = n;

                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {

            for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                int n = mapaNumber[gp.currentMap][worldCol][worldRow];

                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if (gp.player.screenX > gp.player.worldX) {
                    screenX = worldX;
                }

                if (gp.player.screenY > gp.player.worldY) {
                    screenY = worldY;
                }

                int rightX = gp.screenWidth - gp.player.screenX;
                if (rightX > gp.worldWidth - gp.player.worldX) {
                    screenX = gp.screenWidth - (gp.worldWidth - worldX);
                }

                int bottomY = gp.screenHeight - gp.player.screenY;
                if (bottomY > gp.worldHeight - gp.player.worldY) {
                    screenY = gp.screenHeight - (gp.worldHeight - worldY);
                }

                if ((worldX + gp.tileSize > gp.player.worldX - gp.player.screenX) &&
                        (worldX - gp.tileSize < gp.player.worldX + gp.player.screenX) &&
                        (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY) &&
                        (worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)) {
                    g2.drawImage(tile[n].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                   /*  g2.setColor(Color.red);
                    g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
                    g2.setColor(Color.blue);
                    g2.fillRect(screenX+23, screenY+23, 2, 2); */

                } else if (gp.player.screenX > gp.player.worldX ||
                        gp.player.screenY > gp.player.worldY ||
                        rightX > gp.worldWidth - gp.player.worldX ||
                        bottomY > gp.worldHeight - gp.player.worldY) {
                    g2.drawImage(tile[n].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }

            }
        }

    }

    public void drawSuperior(Graphics2D g2) {
        if (gp.currentMap == 0) {
            for (int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {

                for (int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {

                    int n = mapaNumber[2][worldCol][worldRow];

                    if (n != 2) {
                        int worldX = worldCol * gp.tileSize;
                        int worldY = worldRow * gp.tileSize;
                        int screenX = worldX - gp.player.worldX + gp.player.screenX;
                        int screenY = worldY - gp.player.worldY + gp.player.screenY;

                        if (gp.player.screenX > gp.player.worldX) {
                            screenX = worldX;
                        }

                        if (gp.player.screenY > gp.player.worldY) {
                            screenY = worldY;
                        }

                        int rightX = gp.screenWidth - gp.player.screenX;
                        if (rightX > gp.worldWidth - gp.player.worldX) {
                            screenX = gp.screenWidth - (gp.worldWidth - worldX);
                        }

                        int bottomY = gp.screenHeight - gp.player.screenY;
                        if (bottomY > gp.worldHeight - gp.player.worldY) {
                            screenY = gp.screenHeight - (gp.worldHeight - worldY);
                        }

                        if ((worldX + gp.tileSize > gp.player.worldX - gp.player.screenX) &&
                                (worldX - gp.tileSize < gp.player.worldX + gp.player.screenX) &&
                                (worldY + gp.tileSize > gp.player.worldY - gp.player.screenY) &&
                                (worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)) {
                            g2.drawImage(tile[n].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

                        } else if (gp.player.screenX > gp.player.worldX ||
                                gp.player.screenY > gp.player.worldY ||
                                rightX > gp.worldWidth - gp.player.worldX ||
                                bottomY > gp.worldHeight - gp.player.worldY) {
                            g2.drawImage(tile[n].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                        }

                    }

                }
            }
        }

    }
}
