package com.example;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.example.entity.Character;
import com.example.entity.Entity;
import com.example.entity.enemy.Enemy;
import com.example.items.Ether;
import com.example.items.Potion;

import javafx.scene.text.Font;

public class AnimationAttack {
    GamePanel gp;
    BufferedImage[] animation = null;
    int spriteCont = 0;
    int cont = 0;
    boolean status;
    Entity attack;
    Entity target;
    int type = 0;

    public AnimationAttack(GamePanel gp) {
        this.gp = gp;

    }

    public void setAnimation(BufferedImage[] animation, Entity target, Entity attack, int type) {
        this.animation = animation;
        this.attack = attack;
        this.target = target;
        this.type = type;
    }

    public void update() {
        if (target != null) {
            if (!target.getIsAlive()) {
                if (target instanceof Enemy) {
                    target = ((Character) attack).findNewTarget();
                }

            }
        }

        if (type == 0) {
            if (animation != null) {
                spriteCont++;
                if (spriteCont > animation.length - 1) {
                    if (attack instanceof Enemy) {
                        ((Enemy) attack).attackEntity(target);
                    }

                    if (attack instanceof Character) {
                        ((Character) attack).attackEntity(target);
                    }
                    spriteCont = 0;
                    animation = null;
                }
            }
        }

        if (type == 1) {
            if (animation != null) {
                cont++;
                if (cont > 4) {
                    cont = 0;
                    spriteCont++;
                    if (spriteCont > animation.length - 1) {
                        ((Character) attack).getItem().useObject(target);
                        Character c = (Character) target;
                        Color color = null;
                        if (((Character) attack).getItem() instanceof Potion) {
                            color = Color.green;
                        } else if (((Character) attack).getItem() instanceof Ether) {
                            color = new Color(0, 223, 223);
                        }
                        AnimatedText animatedText = new AnimatedText(((Character) attack).getItem().getValue() + "",
                                c.x, c.y, color, 1, 30);
                        gp.battle.addAnimatedText(animatedText);
                        spriteCont = 0;
                        type = 0;
                        animation = null;
                    }
                }

            }
        }

        if (type == 2) {
            if (animation != null) {
                cont++;
                if (cont > 5) {
                    cont = 0;
                    spriteCont++;
                    if (spriteCont > (animation.length - 1) * 3) {

                        if (attack instanceof Character) {
                            ((Character) attack).magicAttackEntity(target);
                        }
                        spriteCont = 0;
                        type = 0;
                        animation = null;
                    }
                }

            }
        }

        if (type == 3) {
            if (animation != null) {

                if (spriteCont < animation.length - 4) {

                    spriteCont++;

                } else {
                    cont++;
                    if (cont > 5) {
                        cont = 0;
                        spriteCont++;
                        if (spriteCont > animation.length - 1) {

                            if (attack instanceof Character) {
                                ((Character) attack).magicAttackEntity(target);
                            }
                            spriteCont = 0;
                            type = 0;
                            animation = null;
                        }
                    }
                }
            }

        }

        if (type == 4) {
            if (animation != null) {

                cont++;
                if (cont > 2) {
                    cont = 0;
                    spriteCont++;
                    if (spriteCont > animation.length - 1) {

                        if (attack instanceof Character) {
                            ((Character) attack).magicAttackEntity(target);
                        }
                        spriteCont = 0;
                        type = 0;
                        animation = null;
                    }
                }

            }
        }
    }

    public void draw(Graphics2D g2) {

        if (animation != null) {
            if (target instanceof Character) {

                Character character = (Character) target;
                if (type == 0) {
                    g2.drawImage(animation[spriteCont], character.x - gp.tileSize, character.y - gp.tileSize,
                            animation[spriteCont].getWidth(), animation[spriteCont].getHeight(), null);
                }

                if (type == 1) {
                    g2.drawImage(animation[spriteCont], character.x, character.y,
                            character.sizeWidth + 10, character.sizeHeight + 10, null);

                }

            } else {
                if (type == 0) {
                    g2.drawImage(animation[spriteCont], target.defaultX, target.defaultY,
                            animation[spriteCont].getWidth(),
                            animation[spriteCont].getHeight(), null);
                }

                if (type == 2) {
                    int frameWidth = 36 * 3;
                    int frameHeight = 48 * 3;
                    int numFrames = animation.length;

                    for (int i = 0; i < 3; i++) {
                        int frameIndex = spriteCont - i;
                        if (frameIndex >= 0 && frameIndex < numFrames) {
                            int xOffset = frameWidth * (1 - i);
                            int xPos = target.defaultX + xOffset;
                            g2.drawImage(animation[frameIndex], xPos, target.defaultY, frameWidth, frameHeight, null);
                        }
                    }
                }

                if (type == 3) {
                    g2.drawImage(animation[spriteCont], target.defaultX,
                            target.defaultY - (animation[spriteCont].getHeight() / 2),
                            animation[spriteCont].getWidth() * 2, animation[spriteCont].getHeight() * 2, null);

                }

                if (type == 4) {
                    g2.drawImage(animation[spriteCont], target.defaultX,
                            target.defaultY - gp.tileSize * 2,
                            animation[spriteCont].getWidth() * 2, animation[spriteCont].getHeight() * 2, null);

                }

            }

        }
    }

}
