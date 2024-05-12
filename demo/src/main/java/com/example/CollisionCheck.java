package com.example;

import com.example.entity.Entity;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity e) {
        int entityLeftX = e.worldX + e.hitBox.x;
        int entityRightX = entityLeftX + e.hitBox.width;
        int entityUpY = e.worldY + e.hitBox.y;
        int entityDownY = entityUpY + e.hitBox.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityUpY / gp.tileSize;
        int entityDownRow = entityDownY / gp.tileSize;

        int tileNum1, tileNum2;
        try {
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
    
              
            }
        } catch (IndexOutOfBoundsException exception) {
          gp.player.collisionOn=true;
        }
        

    }

    public int checkEntity(Entity e, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                e.hitBox.x = e.worldX + e.hitBox.x;
                e.hitBox.y = e.worldY + e.hitBox.y;

                target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
                target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;

                switch (e.direction) {
                    case "up":
                        e.hitBox.y -= e.speed;
                        if (e.hitBox.intersects(target[i].hitBox)) {
                            e.collisionOn=true;
                            index = i;
                        }
                        break;
                    case "down":
                        e.hitBox.y += e.speed;
                        if (e.hitBox.intersects(target[i].hitBox)) {
                            e.collisionOn=true;
                            index = i;
                        }
                        break;
                    case "left":
                        e.hitBox.x -= e.speed;
                        if (e.hitBox.intersects(target[i].hitBox)) {
                            e.collisionOn=true;
                            index = i;
                        }
                        break;
                    case "right":
                        e.hitBox.x += e.speed;
                        if (e.hitBox.intersects(target[i].hitBox)) {
                            e.collisionOn=true;
                            index = i;
                        }
                        break;
                }

                e.hitBox.x = e.hitboxDefaultX;
                e.hitBox.y = e.hitboxDefaultY;
                target[i].hitBox.x = target[i].hitboxDefaultX;
                target[i].hitBox.y = target[i].hitboxDefaultY;

            }

        }

        return index;
    }
}
