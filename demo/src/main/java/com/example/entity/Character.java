package com.example.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.example.GamePanel;
import com.example.UtilityTool;
import com.example.Items.Item;

public class Character extends Entity {

    public BufferedImage portrait;
    public BufferedImage[] attackAnimation;
    public BufferedImage[] castSpellAnimation;
    public BufferedImage[] battleCursor;
    protected int indexGroup;
    protected String name;
    protected int characterID;
    public int defaultX;
    public int defaultY;
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
    protected boolean deadState;
    protected Entity currentWeapon;
    public ATB atb;

    public Character(GamePanel gp) {
        super(gp);
        defaultX = gp.screenWidth - gp.tileSize * 6;
        defaultY = gp.tileSize * 5 - 24;
        getCursorImagen();
    }

    /*
     * formula
     * attack2 = attack + strengh2
     * Damage = Attack +((Level*Level*Attack2)(256))*3/2
     * 
     * Defensa Formula
     * 
     * DamageFinal = (Damage*(255-Defense)/256)+1
     * 
     * (Magia) = SpellPower*4+(Level*Magic*SpellPower/32)
     * 
     * (Monsters) = Level*Level*(Attack*4+Strenght)/256
     * 
     * 
     */

    public void setATB(ATB atb) {
        this.atb = atb;
    }

    public boolean useObject(Item o) {

        if (o.useObject(this)) {
            return true;
        } else {
            return false;
        }

    }

    boolean action;
    private int spritcont = 0;
    private int cont = 0;
    public void update() {
        atb.update();
       

        cont++;
        if (cont>10) {
            spritcont++;
            if (spritcont>2) {
                spritcont=0;
            }
            cont=0;
        }

    }

  

    public void draw(Graphics2D g2) {

        g2.drawImage(left, defaultX + (24 * indexGroup), defaultY + ((gp.tileSize + 12) * indexGroup), sizeWidth,sizeHeight, null);

       if (gp.turnHandler.getCurrentCharacter() !=null && gp.turnHandler.getCurrentCharacter().indexGroup==this.indexGroup ) {
         g2.drawImage(battleCursor[spritcont], defaultX+ (24 * indexGroup),(defaultY+((gp.tileSize + 12) * indexGroup))-gp.tileSize/2,12*3,7*3, null);
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


    public void getCursorImagen() {
        battleCursor = new BufferedImage[3];

        battleCursor[0] = setUpCursor("battleCursor1");
        battleCursor[1] = setUpCursor("battleCursor2");
        battleCursor[2] = setUpCursor("battleCursor3");

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
