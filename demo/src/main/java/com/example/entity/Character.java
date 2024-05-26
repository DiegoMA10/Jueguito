package com.example.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.Items.Item;
import com.example.entity.enemy.Enemy;
import com.example.tile.AnimatedText;

public class Character extends Entity {

    public BufferedImage portrait;
    public BufferedImage[] attackAnimation;
    public BufferedImage[] castSpellAnimation;
    public BufferedImage[] battleCursor;
    public BufferedImage cast;
    public BufferedImage dead;
    public BufferedImage imageAttack;
    public BufferedImage takeDamage;
    protected int indexGroup;
    protected String name;
    protected int characterID;
    public int x;
    public int y;
    protected int defaultX;
    protected int defaultY;
    protected int level;
    protected int MaxHp;
    protected int hp;
    protected int MaxMp;
    protected int mp;
    protected int strength;
    protected int dexterity;
    protected int magic;
    protected int defense;
    protected int magicDefense;
    protected int attack;
    protected int exp;
    protected int nextLevelExp;
    protected boolean deadState=true;
    public ATB atb;
    private Graphics2D g2;
    private Entity target;

    private boolean jumping = false;
    private boolean returning = false;
    private int jumpDistance = 120;
    private int jumpHeight = 60;
    private int jumpSpeed = 4;
    private int jumpProgress = 0;
    private int returnSpeed = 6;

    public Character(GamePanel gp) {
        super(gp);
        getImages();
    }

    public void setATB(ATB atb) {
        this.atb = atb;
    }

    public boolean useObject(Item o) {
        return o.useObject(this);
    }

    public boolean action = false;
    private int spriteCursor = 0;
    private int contCursor = 0;

    public void update() {
        atb.update();
        updateAction();

        if (gp.turnHandler.getCurrentTurn() == this) {
            cont++;
        }
        contCursor++;
        if (contCursor > 12) {
            spriteCursor++;
            if (spriteCursor > 2) {
                spriteCursor = 0;
            }
            contCursor = 0;
        }

        Iterator<AnimatedText> iterator = animatedTexts.iterator();
        while (iterator.hasNext()) {
            AnimatedText text = iterator.next();
            if (!text.update()) {
                iterator.remove();
            }
        }
    }

    public int characterAction;

    public void updateAction() {
        if (deadState) {
            defaultMove();
            if (gp.turnHandler.getCurrentTurn() == this) {
                switch (characterAction) {
                    case 1:
                        if (action) {
                            jump();
                        }

                        break;
                }

                if (cont > 160) {
                    action = false;
                    cont = 0;

                    x = defaultX;
                    y = defaultY;
                    gp.turnHandler.nextTurn();
                    atb.resetATB();
                }
            }
        } else {
            image = dead;
            atb.resetATB();
        }

    }

    public void jump() {
        if (cont > 30 && !jumping && !returning) {
            startJump();
        }
        if (jumping) {
            performJump();
        }
        if (returning) {
            returnToPosition();
        }
    }

    private void startJump() {
        jumping = true;
        jumpProgress = 0;
        image = imageAttack;
    }

    int jumpcont = 0;

    private void performJump() {
        double fraction = (double) jumpProgress / jumpDistance;
        x = defaultX - jumpProgress;
        y = defaultY - (int) (jumpHeight * 4 * (fraction - fraction * fraction));
        jumpProgress += jumpSpeed;

        if (jumpProgress >= jumpDistance / 2 && jumpProgress < jumpDistance) {
            image = left1;
        }

        if (jumpProgress >= jumpDistance) {
            if (jumpProgress < jumpDistance + 5) {
                return;
            }

            attackEntity(target);
            gp.battle.animationAttack.setAnimation(attackAnimation, target);

            gp.playSE(8);
            jumping = false;
            returning = true;
            image = left;
            y = defaultY;
        }
    }

    private void returnToPosition() {
        if (returning) {
            jumpcont++;
        }
        if (jumpcont > 50) {
            x += returnSpeed;
            image = right;
            if (x >= defaultX) {
                action = false;
                jumpcont = 0;
                returning = false;
                image = left;
                x = defaultX;
                y = defaultY;

            }
        }
    }

    public void defaultMove() {
        if (action) {
            if (!jumping && !returning) {
                image = imageAttack;
            }
        } else {
            image = left;
        }
    }

    public void attackEntity(Entity e) {
        if (!e.getDeadState()) {
            target = findNewTarget();
        }

        int attack2 = attack + strength * 2;
        int damage = attack + ((level * level * attack2) / (256)) * 3 / 2;

        if (e instanceof Character) {
            ((Character) target).takeDamage(damage);
        }
        if (e instanceof Enemy) {
            ((Enemy) target).takeDamage(damage);
        }

    }

    public Entity findNewTarget() {

        for (Entity enemy : gp.battle.level.get(gp.battle.currentRound)) {
            if (enemy.getDeadState()) {
                return enemy;
            }
        }
        return null;
    }

    public void takeDamage(int damage) {
        int damageFinal = (damage * (255 - defense) / 256) + 1;
        setHp(getHp() - damageFinal);
        AnimatedText animatedText = new AnimatedText(Integer.toString(damageFinal), defaultX + sizeWidth / 2,
                defaultY + sizeHeight / 2, Color.WHITE, new Font("Arial", Font.BOLD, 24), 1, 30);
        animatedTexts.add(animatedText);
        if (hp <= 0) {
            deadState = false;
        }
    }

    private BufferedImage image;

    public void drawText(String text, int x, int y, Color c) {
        if (c == null) {
            c = Color.WHITE;
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24));
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 3, y + 3);
        g2.setColor(c);
        g2.drawString(text, x, y);
    }

    int cont = 0;

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (deadState) {
            g2.drawImage(image, x, y, sizeWidth, sizeHeight, null);
            for (AnimatedText text : animatedTexts) {
                text.draw(g2);
            }
            if (gp.turnHandler.getCurrentCharacter() != null &&
                    gp.turnHandler.getCurrentCharacter().indexGroup == this.indexGroup) {
                g2.drawImage(battleCursor[spriteCursor], x, y - gp.tileSize / 2, 12 * 3, 7 * 3, null);
            }
        }else{
            g2.drawImage(image, x, y+23, 24*3, 17*3, null);
        }

    }

    public BufferedImage setUpCursor(String path) {
        UtilityTool tool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/com/example/image/UI/" + path + ".png"));
            image = tool.imageScale(image, 12 * 3, 7 * 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
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
        battleCursor = new BufferedImage[3];
        for (int i = 0; i < battleCursor.length; i++) {
            battleCursor[i] = setUpCursor("battleCursor" + (i + 1));
        }
        attackAnimation = new BufferedImage[8];
        for (int i = 0; i < attackAnimation.length; i++) {
            attackAnimation[i] = setAttack("attack" + (i + 1));
        }
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    public int getIndexGroup() {
        return indexGroup;
    }

    public void setIndexGroup(int indexGroup) {
        this.indexGroup = indexGroup;
    }

    public static void changeInexGroup(Character c1, Character c2) {
        int aux = c1.getIndexGroup();
        c1.setIndexGroup(c2.getIndexGroup());
        c2.setIndexGroup(aux);
    }

    public int getCharacterID() {
        return characterID;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMagicDefense() {
        return magicDefense;
    }

    public void setMagicDefense(int magicDefense) {
        this.magicDefense = magicDefense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(int maxHp) {
        MaxHp = maxHp;
    }

    public int getHp() {
        return hp;
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

    public int getMaxMp() {
        return MaxMp;
    }

    public void setMaxMp(int maxMp) {
        MaxMp = maxMp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
        if (this.mp > this.MaxMp) {
            this.mp = this.MaxMp;
        }
    }
}
