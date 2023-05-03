package com.example.demo;

import org.json.JSONObject;

import java.sql.*;

public class sql_functions {

    public String getProducts_of_list(Integer id_list) {
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
}
