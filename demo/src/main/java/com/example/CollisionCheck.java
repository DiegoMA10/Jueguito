package com.example;

import com.example.entity.Entity;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity e) {
        int entityLeftX = e.worldX + e.solidArea.x;
        int entityRightX = entityLeftX + e.solidArea.width;
        int entityUpY = e.worldY + e.solidArea.y;
        int entityDownY = entityUpY + e.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityUpY / gp.tileSize;
        int entityDownRow = entityDownY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (e.direction) {
            case "up":
                entityTopRow = (entityUpY - e.speed) / gp.tileSize;

                tileNum1 = gp.tl.mapaNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tl.mapaNumber[gp.currentMap][entityRightCol][entityTopRow];
              
                if (gp.tl.tile[tileNum1].collision == true || gp.tl.tile[tileNum2].collision == true) {
                    e.collisionOn = true;
                }

                break;
            case "down":
                entityDownRow = (entityDownY + e.speed) / gp.tileSize;
                tileNum1 = gp.tl.mapaNumber[gp.currentMap][entityLeftCol][entityDownRow];
                tileNum2 = gp.tl.mapaNumber[gp.currentMap][entityRightCol][entityDownRow];
             
                if (gp.tl.tile[tileNum1].collision == true || gp.tl.tile[tileNum2].collision == true) {
                    e.collisionOn = true;
                }
                break;
            case "left":
            entityLeftCol = (entityLeftX - e.speed) / gp.tileSize;

                tileNum1 = gp.tl.mapaNumber[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tl.mapaNumber[gp.currentMap][entityLeftCol][entityDownRow];
              
                if (gp.tl.tile[tileNum1].collision == true || gp.tl.tile[tileNum2].collision == true) {
                    e.collisionOn = true;
                }

                
                break;
            case "right":
            entityRightCol = (entityRightX + e.speed) / gp.tileSize;

            tileNum1 = gp.tl.mapaNumber[gp.currentMap][entityRightCol][entityTopRow];
            tileNum2 = gp.tl.mapaNumber[gp.currentMap][entityRightCol][entityDownRow];
          
            if (gp.tl.tile[tileNum1].collision == true || gp.tl.tile[tileNum2].collision == true) {
                e.collisionOn = true;
            }

                break;

            default:
                break;
        }
    }
}
