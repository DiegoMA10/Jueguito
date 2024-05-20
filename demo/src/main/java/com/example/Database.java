package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.example.Items.Item;
import com.example.entity.Aerith;
import com.example.entity.Character;
import com.example.entity.Cloud;
import com.example.entity.Tifa;

public class Database {
    GamePanel gp;
    Connection con;

    public Database(GamePanel gp) {
        this.gp = gp;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/juego", "root", "123");
            // con = DriverManager.getConnection("jdbc:mysql://localhost:33006/juego",
            // "root", "dbrootpass");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveData(int partyID) {
        saveParty(partyID);
        saveCharacters(partyID);
        saveInventory(partyID);
        saveGame(partyID, partyID);
    }

    private void saveParty(int partyID) {
        String sql = "INSERT INTO party (partyID, gil) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, partyID);
            preparedStatement.setInt(2, gp.party.getGil());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveCharacters(int partyID) {
        String sql = "INSERT INTO character_party (partyID, characterID, party_index, level, exp, hp, mp) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            for (Character character : gp.party.getParty()) {
                preparedStatement.setInt(1, partyID);
                preparedStatement.setInt(2, character.getCharacterID());
                preparedStatement.setInt(3, character.getIndexGroup());
                preparedStatement.setInt(4, character.getLevel());
                preparedStatement.setInt(5, character.getExp());
                preparedStatement.setInt(6, character.getHp());
                preparedStatement.setInt(7, character.getMp());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveInventory(int partyID) {
        String sql = "INSERT INTO inventory (partyID, itemID, amount) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {

            for (Item item : gp.party.getInventory()) {
                preparedStatement.setInt(1, partyID);
                preparedStatement.setInt(2, item.idItem);
                preparedStatement.setInt(3, item.getAmount());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveGame(int gameID, int partyID) {
        String sql = "INSERT INTO games (gameID, playTime, partyID) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, gameID);
            preparedStatement.setDouble(2, gp.ui.getPlayTimer());
            preparedStatement.setInt(3, partyID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkSave(int i) {

        String sql = "SELECT COUNT(*) FROM games WHERE partyID = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, i);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int rowCount = resultSet.getInt(1);
                    return rowCount > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getPlayTime(int gameID) {
        String sql = "SELECT playTime FROM games WHERE gameID = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, gameID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("playTime");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; 
    }

    public ArrayList<Character> getCharactersByGame(int gameID) {
        ArrayList<Character> characters = new ArrayList<>();
        String sql = "SELECT c.*,cp.level,cp.exp, cp.party_index, cp.hp, cp.mp FROM character_party cp INNER JOIN characters c ON cp.characterID = c.characterID WHERE cp.partyID = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, gameID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int characterID = resultSet.getInt("characterID");
                    String name = resultSet.getString("name");
                    int level = resultSet.getInt("level");
                    int exp = resultSet.getInt("exp");
                    int partyIndex = resultSet.getInt("party_index");
                    int hp = resultSet.getInt("hp");
                    int mp = resultSet.getInt("mp");

                   switch (characterID) {
                    case 1: Aerith aerith = new Aerith(this.gp, level, exp, partyIndex, hp, mp); characters.add(aerith);break;
                    case 2: Tifa tifa = new Tifa(this.gp, level, exp, partyIndex, hp, mp); characters.add(tifa);break;
                    case 3: Cloud cloud = new Cloud(gp, level, exp, partyIndex, hp, mp); characters.add(cloud);break;
                   
                   }
                   
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return characters;
    }
    
    

}
