package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

	@GetMapping("/products")
	public String getProducts() {
		return "Hello World";
	}

}
