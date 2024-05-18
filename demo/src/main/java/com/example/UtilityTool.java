package com.example;
import com.example.*;
import com.example.entity.Character;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UtilityTool {
    public BufferedImage imageScale(BufferedImage image, int width, int height) {
        BufferedImage imageScale = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = imageScale.createGraphics();
        g2.drawImage(image,0,0,width,height,null);
        g2.dispose();
        return imageScale;
    }
   
     public static void sortByIndexGroup(ArrayList<Character> characters) {
        Collections.sort(characters, new CharacterIndexGroupComparator());
    }

     private static class CharacterIndexGroupComparator implements Comparator<com.example.entity.Character> {
        @Override
        public int compare(com.example.entity.Character c1, com.example.entity.Character c2) {
            return Integer.compare(c1.getIndexGroup(), c2.getIndexGroup());
        }
    }
}
