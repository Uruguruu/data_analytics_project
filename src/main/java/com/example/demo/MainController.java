package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
	/**
	 * This Function has 2 purposes
	 *	1. By giving an ID as a URL parameter a specific List can be displayed.
	 *	2. If no ID is given so the parameter is undefined or null all the ID's of the existing List are getting displayed.
	 */
	@GetMapping("/products")
	public String getList() {
		return "Hello GET";
	}

	/**
	 *	Here you can add a List, and It will return the List ID.
	 */
	@PostMapping("/products")
	public String addList() {
		return "Hello GET";
	}

	/**
	 *	Here you can delete a List with the list's ID.
	 */
	@DeleteMapping("/proucts")
	public String deleteList() {
		return "Hello GET";
	}

	/**
	 *	Here you can add a product to a List.
	 */
	@PostMapping("/product")
	public String addProduct() {
		return "Hello ADD";
	}
	/**
	 *	Here you can edit a product from a List.
	 */
	@PutMapping("/product")
	public String editProduct() {
		return "Hello DELETE";
	}
	/**
	 *	Here you can delete a product from a List.
	 */
	@DeleteMapping("/product")
	public String deleteProduct() {
		return "Hello EDIT";
	}

}
