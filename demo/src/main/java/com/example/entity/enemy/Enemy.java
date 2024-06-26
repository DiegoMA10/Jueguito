package com.example.entity.enemy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import com.example.AnimatedText;
import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.entity.ATB;
import com.example.entity.Entity;
import com.example.entity.Character;

public class Enemy extends Entity {

    protected String name;
    public BufferedImage[] animationAttack;
    protected int level;
    protected int MaxHp;
    protected int hp;
    protected int strength;
    protected int dexterity;
    protected int defense;
    protected int magicDefense;
    protected int attack;
    protected int exp;
    protected int gil;

    private ATB atb;
    Graphics2D g2;
    private float transparency = 1.0f;
    private final float TRANSPARENCY_CHANGE_PER_UPDATE = 0.02f;
    Random r = new Random();

    public Enemy(GamePanel gp, int level, int maxHp, int gil, int exp, int x, int y) {
        super(gp);
        this.strength = r.nextInt(63 - 56) + 56;
        this.level = level;
        this.gil = gil;
        this.exp = exp;
        this.MaxHp = maxHp;
        defaultX = x;
        defaultY = y;
        getImages();
    }

    boolean action;
    private int spritcont = 0;
    private int cont = 0;

    public void update() {

        if (isAlive) {
            atb.update();
            defaultMove();
            if (gp.turnHandler.getCurrentTurn() == this) {
                if (gp.party.isAlive()) {
                    cont++;

                    if (cont > 20 && !action) {
                        spritcont++;
                        action = true;
                    }

                    if (cont > 40) {
                        spritcont = 0;
                    }

                    if (cont > 130) {
                        action = false;
                        spritcont = 0;
                        atb.resetATB();
                        Entity target = gp.party.aliveRandom();
                        gp.battle.animationAttack.setAnimation(animationAttack, target, this, 0);

                        gp.playSE(8);
                        gp.turnHandler.nextTurn();
                        cont = 0;
                    }
                } else {
                    action = false;
                    spritcont = 0;
                    atb.resetATB();
                    gp.turnHandler.nextTurn();
                    cont = 0;
                }

            }

        } else {
            if (gp.turnHandler.getCurrentTurn() == this) {
                gp.turnHandler.getCurrentTurn().setIsAlive(false);
            }

            transparency -= TRANSPARENCY_CHANGE_PER_UPDATE;
            transparency = Math.max(0.0f, transparency);

            if (Math.round(transparency * 100) == 50) {
                gp.playSE(17);
            }
            if (transparency == 0.0f) {

                gp.battle.level.get(gp.battle.currentRound).remove(this);
                gp.battle.iterator = gp.battle.level.get(gp.battle.currentRound).iterator();
                gp.ui.subNumCommand2 = 0;

            }
        }
    }

    public BufferedImage setAttack(String path) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/com/example/image/attacks/" + path + ".png"));
            image = tool.imageScale(image, gp.tileSize * 3, gp.tileSize * 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void getImages() {
        animationAttack = new BufferedImage[8];
        for (int i = 0; i < animationAttack.length; i++) {
            animationAttack[i] = setAttack("attack" + (i + 1));
        }
    }

    public void defaultMove() {

        if (spritcont == 0) {
            image = right;
        } else {
            image = right1;
        }

    }

    public void attackEntity(Entity e) {

        int damage = level * level * (attack * 4 + strength) / 256;

        if (e != null) {
            if (e instanceof Character) {
                ((Character) e).takeDamage(damage);
            }

            if (e instanceof Enemy) {
                ((Enemy) e).takeDamage(damage);
            }
        }

    }

    public void takeDamage(int damage) {
        int damageFinal = (damage * (255 - defense) / 256) + 1;
        setHp(getHp() - damageFinal);
        AnimatedText animatedText = new AnimatedText(Integer.toString(damageFinal), defaultX + sizeWidth / 2,
                defaultY + sizeHeight / 2, Color.WHITE, 1, 30);
        gp.battle.addAnimatedText(animatedText);
        if (hp <= 0) {
            gp.battle.addExp(this.exp);
            gp.battle.addGil(this.gil);

            isAlive = false;
        }
    }

    public void takeMagicDamage(int damage) {
        int damageFinal = (damage * (255 - magicDefense) / 256) + 1;
        setHp(getHp() - damageFinal);
        AnimatedText animatedText = new AnimatedText(Integer.toString(damageFinal), defaultX + sizeWidth / 2,
                defaultY + sizeHeight / 2, Color.WHITE, 1, 30);
        gp.battle.addAnimatedText(animatedText);
        if (hp <= 0) {
            gp.battle.addExp(this.exp);
            gp.battle.addGil(this.gil);
            isAlive = false;
        }
    }

    private BufferedImage image;

    public void draw(Graphics2D g2) {
        if (isAlive || transparency > 0.0f) {
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
            g2.setComposite(alphaComposite);
            this.g2 = g2;
            g2.drawImage(image, defaultX, defaultY, sizeWidth, sizeHeight, null);

            for (AnimatedText text : animatedTexts) {
                text.draw(g2);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    public void setAtb(ATB atb) {
        this.atb = atb;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setHp(int hp) {
        this.hp = hp;
        if (this.hp > this.MaxHp) {
            this.hp = this.MaxHp;
        }

        if (this.hp < 0) {
            this.hp = 0;
        }

    }

    public int getHp() {
        return this.hp;
    }

}