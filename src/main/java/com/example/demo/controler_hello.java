package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
@RestController
public class controler_hello {

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}

	@GetMapping("/api/foos")
	public String getFoos(@RequestParam(value="id") String id) {
		return "ID: " + id;
	}

	@PostMapping("/postbody")
	public String postBody(@RequestBody String fullName) {
		return "Hello " + fullName;
	}
}