package com.example.demo;

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
        JSONObject results = new JSONObject();
        // Verbindung zur SQLite-Datenbank herstellen
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM products");
                while (rs.next()) {
                    results.put("ID", rs.getInt("ID"));
                    results.put("name", rs.getString("Name"));
                    results.put("Anzahl", rs.getString("Anzahl"));
                }
            }
        } catch (SQLException e) {
        }
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

    //_________________________________________________________________________________________________________________________________________
    // code for temporary tests. Will be removed before final code
    //_________________________________________________________________________________________________________________________________________

    public static void main(String[] args)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
            // SELECT-Befehl ausführen

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM products");
                stmt.executeUpdate("DELETE FROM shopping_list");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        create_shopping_list("Migros einkauf");
        System.out.println(get_list_of_shopping_list());
        System.out.println(getProducts_of_shopping_list(1));
        System.out.println(create_product("Milllch", 1, 5));
        System.out.println(getProducts_of_shopping_list(1));
        System.out.println(update_product("Millch", 1, 1000));
        System.out.println(getProducts_of_shopping_list(1));
        System.out.println(create_product("Kuh", 1, 5));
        System.out.println(delete_product(1));
        System.out.println(getProducts_of_shopping_list(1));
        System.out.println(delete_shopping_list(1));

    }

}