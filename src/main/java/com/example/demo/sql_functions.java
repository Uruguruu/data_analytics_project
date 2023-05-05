package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;

import java.sql.*;

public class sql_functions {


    //_________________________________________________________________________________________________________________________________________
    // all function for the shopping list
    //_________________________________________________________________________________________________________________________________________
    public static String get_list_of_shopping_list() {
        JSONObject results = new JSONObject();
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM shopping_list");
                while (rs.next()) {
                    results.put("ID", rs.getInt("ID"));
                    results.put("Name", rs.getString("name"));
                }
            }
        } catch (SQLException e) {
        }
        return results.toString();
    }

    public static String create_shopping_list(String name) {
        String feedback = "delay_error";
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "INSERT INTO shopping_list(name) VALUES(?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Werte für die Platzhalter setzen
                pstmt.setString(1, name);
                // INSERT-Befehl ausführen
                pstmt.executeUpdate();
                feedback = "success";
            }
        } catch (SQLException e) {
            feedback="error";
        }
        return feedback;
    }

    public static String delete_shopping_list(Integer ID) {
        String feedback = "delay_error";
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "DELETE FROM shopping_list WHERE ID = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Werte für die Platzhalter setzen
                pstmt.setInt(1, ID);
                // INSERT-Befehl ausführen
                pstmt.executeUpdate();
                feedback = "success";
            }
        } catch (SQLException e) {
            feedback="error";
        }
        return feedback;
    }



    //_________________________________________________________________________________________________________________________________________
    // all function for the products
    //_________________________________________________________________________________________________________________________________________
    public static String getProducts_of_shopping_list(Integer id_list) {
        System.out.println(id_list);
        JSONArray results = new JSONArray();
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "SELECT * FROM products WHERE FK_shopping_list = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id_list);
                ResultSet rs =  pstmt.executeQuery();
                while (rs.next()) {
                    JSONObject row = new JSONObject();
                    row.put("ID", rs.getInt("ID"));
                    row.put("name", rs.getString("Name"));
                    row.put("Anzahl", rs.getString("Anzahl"));
                    results.put(row);
                }
            }
        } catch (SQLException e) {
            System.out.println(id_list);
        }
        System.out.println(results.toString());
        return results.toString();
    }
    public static String create_product(String name, Integer ID_of_shopping_list, Integer Amount) {
        String feedback = "delay_error";
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "INSERT INTO products(name, FK_shopping_list, Anzahl) VALUES(?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Werte für die Platzhalter setzen
                pstmt.setString(1, name);
                pstmt.setInt(2, ID_of_shopping_list);
                pstmt.setInt(3, Amount);
                // INSERT-Befehl ausführen
                pstmt.executeUpdate();
                feedback = "success";
            }
        } catch (SQLException e) {
            System.out.println(e);
            feedback="error";
        }
        return feedback;
    }

    public static String delete_product(Integer ID) {
        String feedback = "delay_error";
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "DELETE FROM products WHERE ID = ?;";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Werte für die Platzhalter setzen
                pstmt.setInt(1, ID);
                // INSERT-Befehl ausführen
                pstmt.executeUpdate();
                feedback = "success";
            }
        } catch (SQLException e) {
            feedback="error";
        }
        return feedback;
    }

    public static String update_product(String name, Integer ID_of_product, Integer Amount) {
        String feedback = "delay_error";
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen
            String sql = "UPDATE products SET Name = ?, Anzahl = ? WHERE ID = ?; ";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Werte für die Platzhalter setzen
                pstmt.setString(1, name);
                pstmt.setInt(2, Amount);
                pstmt.setInt(3, ID_of_product);
                // INSERT-Befehl ausführen
                pstmt.executeUpdate();
                feedback = "success";
            }
        } catch (SQLException e) {
            System.out.println(e);
            feedback="error";
        }
        return feedback;
    }
}