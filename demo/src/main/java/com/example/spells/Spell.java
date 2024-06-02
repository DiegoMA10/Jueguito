package com.example.spells;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.entity.Character;

public abstract class Spell {
    GamePanel gp ;
    protected BufferedImage[] animation;
    protected int SpellPower;
    protected String name;
    protected int cost;

    public Spell(GamePanel gp) {
        this.gp = gp;
    }
    
    public BufferedImage[] getAnimation() {
        return animation;
    }

    public int getSpellPower() {
        return SpellPower;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

     public void setUp(int n, String name) {
        UtilityTool tool = new UtilityTool();

        try {

            animation = new BufferedImage[n];
            for (int i = 1; i <= animation.length; i++) {
                animation[i - 1] = ImageIO.read(getClass().getResourceAsStream("/com/example/image/spells/" + name + i+".png"));
                animation[i - 1] = tool.imageScale(animation[i-1], animation[i-1].getWidth(), animation[i-1].getHeight());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void checkEvolution(Character character);

    

    

}
