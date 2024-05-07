package com.example;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public class Main {
    public static void main(String[] args) {
        

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("hola");
        
        GamePanel gamePanel = new GamePanel();
       
        window.add(gamePanel);
      
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setUpGame();
        gamePanel.startGameThread();
    }
}
