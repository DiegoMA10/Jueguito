package com.example;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage imageScale(BufferedImage image, int width, int height) {
        BufferedImage imageScale = new BufferedImage(width, height, image.getType());
        Graphics2D g2 = imageScale.createGraphics();
        g2.drawImage(image,0,0,width,height,null);
        g2.dispose();
        return imageScale;
    }
   
}
