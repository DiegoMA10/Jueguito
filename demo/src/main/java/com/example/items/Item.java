package com.example.items;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.BattlePanel;
import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.entity.Character;
import com.example.entity.Entity;
import com.example.tile.Tile;

public abstract class Item {
    GamePanel gp;

    protected BufferedImage[] animation;
    public int idItem;
    protected String name;
    protected String description;
    protected int amount = 0;
    protected int price;
    protected int value;
   
    public static int maxAmount = 99;

    public Item(GamePanel gp) {
        this.gp = gp;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public boolean setAmount(int amount) {

        this.amount = amount;

        return false;
    }

    public abstract boolean useObject(Entity target);

    public void setUp(int n, String name) {
        UtilityTool tool = new UtilityTool();

        try {

            animation = new BufferedImage[n];
            for (int i = 1; i <= animation.length; i++) {
                animation[i - 1] = ImageIO.read(getClass().getResourceAsStream("/com/example/image/items/" + name + i+".png"));
                animation[i - 1] = tool.imageScale(animation[i-1], animation[i-1].getWidth(), animation[i-1].getHeight());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BufferedImage[] getAnimation() {
        return animation;
    }

    public int getValue() {
        return value;
    }


}
