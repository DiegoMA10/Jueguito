package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databases {
    GamePanel gp;
    Connection con;
    public Databases(GamePanel gp){
        this.gp=gp;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juego", "root", "123");
           // con = DriverManager.getConnection("jdbc:mysql://localhost:33006/juego", "root", "dbrootpass");
        } catch (SQLException e) {
         e.printStackTrace();
        }
    }
}
