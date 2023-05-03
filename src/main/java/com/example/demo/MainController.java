package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
public class MainController {
	@GetMapping("/check/hallo")
	public String hello() {
		return "running...";
	}
	@GetMapping("/products")
	public String getProducts() {
		StringBuilder result = new StringBuilder();
		// Verbindung zur SQLite-Datenbank herstellen
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:db.db")) {
			// SELECT-Befehl ausführen
			try (Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery("SELECT * FROM products");
				while (rs.next()) {
					result.append(rs.getString("mycolumn")).append("\n");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result.toString();
	}
}