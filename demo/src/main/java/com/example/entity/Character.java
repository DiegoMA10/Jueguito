package com.example.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import com.example.AnimatedText;
import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.Items.Ether;
import com.example.Items.Item;
import com.example.Items.Potion;
import com.example.entity.enemy.Enemy;
import com.example.spells.Electro;
import com.example.spells.Hielo;
import com.example.spells.Piro;
import com.example.spells.Spell;

public abstract class Character extends Entity {

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
    public int defaultX;
    public int defaultY;
    protected int level;
    protected int baseMaxHp;
    protected int baseMaxMp;
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
    private ArrayList<Spell> abilities;

    private boolean takeDamageOn = false;
    public ATB atb;

    private Entity target;
    private Item item;
    private Spell spell;

    private boolean jumping = false;
    private boolean returning = false;

    private int jumpDistance = 120;
    private int jumpHeight = 60;
    private int jumpSpeed = 4;
    private int jumpProgress = 0;
    private int returnSpeed = 6;

    public Character(GamePanel gp) {
        super(gp);
        abilities = new ArrayList<>();
        abilities.add(new Piro(gp));
        abilities.add(new Hielo(gp));
        abilities.add(new Electro(gp));
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
    private int contTransition = 0;
    private int spriteTransition = 0;

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

    }

    public int characterAction;

    public void updateAction() {
        if (isAlive) {
            if (!gp.battle.transitioning) {
                defaultMove();
                if (gp.turnHandler.getCurrentTurn() == this) {

                    if (gp.turnHandler.areEnemiesAlive()) {
                        switch (characterAction) {
                            case 1:
                                if (action) {
                                    jump();
                                }
                                break;
                            case 2:
                                if (action) {
                                    move();
                                }
                                break;
                            case 3:
                                if (action) {
                                    move();
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
                    } else {
                        returnToPosition();
                        if (cont > 140) {
                            action = false;
                            cont = 0;
                            x = defaultX;
                            y = defaultY;
                            gp.turnHandler.nextTurn();
                            atb.resetATB();
                            resetJump();
                        }

                    }
                }
            } else {
                transitiongMove();
            }

        } else {
            takeDamageOn = false;
            image = dead;
            atb.resetATB();
        }
    }

    public void transitiongMove() {
        contTransition++;

        x -= 6;
        if (contTransition > 14) {
            spriteTransition++;
            contTransition = 0;
            if (spriteTransition > 1) {
                spriteTransition = 0;
                image = left;
            } else {
                image = left1;
            }
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

            gp.battle.animationAttack.setAnimation(attackAnimation, target, this, 0);
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
                resetJump();
                resetMove();
            }
        }
    }

    public void resetJump() {
        jumping = false;
        returning = false;
        jumpProgress = 0;
        jumpcont = 0;
        x = defaultX;
        y = defaultY;
    }

    public void move() {
        if (cont > 10 && !jumping && !returning) {
            startMove();
        }
        if (jumping) {
            performMove();
        }
        if (returning) {
            returnToPosition();
        }
    }

    private void startMove() {
        jumping = true;
        jumpProgress = 0;
        image = imageAttack;
    }

    private void performMove() {

        x = defaultX - jumpProgress;

        jumpProgress += 6;

        if (jumpProgress >= jumpDistance / 2 && jumpProgress < jumpDistance) {
            image = left1;
        }

        if (jumpProgress >= jumpDistance) {

            image = cast;
            jumpProgress -= 6;
            if (cont > 50) {

                switch (characterAction) {
                    case 2:
                        useSpell();
                        break;
                    case 3:
                        useItem();
                        break;
                    default:

                }

                jumping = false;
                returning = true;
            }

        }
    }

    public void useItem() {
        item.useObject(target);
        gp.battle.animationAttack.setAnimation(item.getAnimation(), this, target, 1);
        Character c = (Character) target;
        Color color = null;
        if (item instanceof Potion) {
            color = Color.green;
        } else if (item instanceof Ether) {
            color = new Color(0, 223, 223);
        }
        AnimatedText animatedText = new AnimatedText(Integer.toString(item.getValue()), c.x,
                c.y, color, new Font("Arial", Font.BOLD, 24), 1, 30);
        gp.battle.addAnimatedText(animatedText);
        gp.playSE(8);
    }

    public void useSpell() {

        if (spell instanceof Piro) {
            gp.battle.animationAttack.setAnimation(spell.getAnimation(), target, this, 2);
            gp.playSE(13);
        }

        if (spell instanceof Electro) {
            gp.battle.animationAttack.setAnimation(spell.getAnimation(), target, this, 4);
            gp.playSE(14);
        }

        if (spell instanceof Hielo) {
            gp.battle.animationAttack.setAnimation(spell.getAnimation(), target, this, 3);
            gp.playSE(15);
        }

        setMp(getMp() - spell.getCost());

    }

    public void resetMove() {
        jumping = false;
        returning = false;
        jumpProgress = 0;
        jumpcont = 0;
        x = defaultX;
        y = defaultY;
    }

    public void defaultMove() {

        if (gp.battle.endBattle) {

            contTransition++;
            if (contTransition > 15) {
                spriteTransition++;
                contTransition = 0;
                if (spriteTransition > 2) {
                    spriteTransition = 0;
                    image = left;
                } else {
                    image = cast;
                }
            }

        } else {

            if (action) {

                if (takeDamageOn) {
                    animationTakeDamage();
                } else if (!jumping && !returning) {
                    image = imageAttack;
                }

            } else {

                if (takeDamageOn) {
                    animationTakeDamage();
                } else {
                    image = left;
                }

            }
        }
    }

    public void animationTakeDamage() {
        spritCount++;
        image = takeDamage;
        if (spritCount > 15) {
            spritCount = 0;
            takeDamageOn = false;
        }
    }

    public void magicAttackEntity(Entity e) {
        if (!e.getIsAlive()) {
            target = findNewTarget();
        }

        int damage = spell.getSpellPower() * 4 + (level * magic * spell.getSpellPower() / 32);

        if (e instanceof Character) {
            ((Character) target).takeMagicDamage(damage);
        }
        if (e instanceof Enemy) {
            ((Enemy) target).takeMagicDamage(damage);
        }

    }

    public void attackEntity(Entity e) {
        if (!e.getIsAlive()) {
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
            if (enemy.getIsAlive()) {
                return enemy;
            }
        }
        return null;
    }

    private BufferedImage image;

    public void takeDamage(int damage) {
        int damageFinal = (damage * (255 - defense) / 256) + 1;
        setHp(getHp() - damageFinal);
        AnimatedText animatedText = new AnimatedText(Integer.toString(damageFinal), defaultX + sizeWidth / 2,
                defaultY + sizeHeight / 2, Color.WHITE, new Font("Arial", Font.BOLD, 24), 1, 30);
        gp.battle.addAnimatedText(animatedText);
        takeDamageOn = true;
        if (hp <= 0) {
            isAlive = false;
        }
    }

    public void takeMagicDamage(int damage) {
        int damageFinal = (damage * (255 - magicDefense) / 256) + 1;
        setHp(getHp() - damageFinal);
        AnimatedText animatedText = new AnimatedText(Integer.toString(damageFinal), defaultX + sizeWidth / 2,
                defaultY + sizeHeight / 2, Color.WHITE, new Font("Arial", Font.BOLD, 24), 1, 30);
        gp.battle.addAnimatedText(animatedText);
        takeDamageOn = true;
        if (hp <= 0) {
            isAlive = false;
        }
    }

    public Boolean checkLevel() {
        if (level < 99) {
            if (this.getExp() >= this.getNextLevelExp()) {
                this.isAlive = true;
                RawStats(getLevel() + 1);
                checkLevel();
                return true;
            }
        }

        return false;
    }

    public abstract void RawStats(int level);

    int cont = 0;

    public void draw(Graphics2D g2) {

        if (isAlive) {
            g2.drawImage(image, x, y, sizeWidth, sizeHeight, null);

            if (!gp.battle.transitioning && !gp.battle.endBattle) {
                if (gp.turnHandler.getCurrentCharacter() != null &&
                        gp.turnHandler.getCurrentCharacter().indexGroup == this.indexGroup) {
                    g2.drawImage(battleCursor[spriteCursor], x, y - gp.tileSize / 2, 12 * 3, 7 * 3, null);
                }
            }
        } else {
            g2.drawImage(image, x, y + 23, 24 * 3, 13 * 3, null);
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

    public void setItem(Item item) {
        this.item = item;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public ArrayList<Spell> getAbilities() {
        return abilities;
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
        c1.refreshPosition();
        c2.refreshPosition();
    }

    public void refreshPosition() {
        defaultX = (gp.screenWidth - gp.tileSize * 6) + (24 * indexGroup);
        defaultY = (gp.tileSize * 5 - 24) + ((gp.tileSize + 12) * indexGroup);
        x = defaultX;
        y = defaultY;
    }

    public int getCharacterID() {
        return characterID;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        if (exp > 2637112) {
            exp = 2637112;
        }
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
        if (level > 99) {
            level = 99;
        }
        this.level = level;
    }

    public int getMaxHp() {
        return MaxHp;
    }

    public void setMaxHp(int maxHp) {
        MaxHp = baseMaxHp + maxHp;
        if (MaxHp > 9999) {
            maxHp = 9999;
        }
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
        MaxMp = baseMaxMp + maxMp;
        if (MaxMp > 999) {
            maxMp = 999;
        }
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
