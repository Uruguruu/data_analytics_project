package com.example.demo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.sql_functions.*;

@RestController
public class MainController {
	/**
	 * This Function has 2 purposes
	 *	1. By giving an ID as a URL parameter a specific List can be displayed.
	 *	2. If no ID is given so the parameter is undefined or null all the ID's of the existing List are getting displayed.
	 */
	@GetMapping("/products")
	public String getList(@RequestParam(value = "id", required = false) String idParam) {
		if (StringUtils.isBlank(idParam)) {
			/* Handle the case where the id parameter is not present */
			return get_list_of_shopping_list();
		} else {
			try {
				/* getProducts_of_shopping_list call for getting specific list */
				return getProducts_of_shopping_list(Integer.parseInt(idParam)); // getProducts_of_shopping_list expects integer that's why parseInt
			} catch (NumberFormatException e) {
				/* Handle the case where the id parameter is not a number */
				return "Invalid ID: " + idParam;
			}
		}
	}



	/**
	 *	Here you can add a List, and It will return the List ID.
	 */
	@PostMapping("/products")
	public String addList(@RequestBody String name) {
		return create_shopping_list(name);
	}

	/**
	 *	Here you can delete a List with the list's ID.
	 */
	@DeleteMapping("/proucts")
	public String deleteList(@RequestBody Integer id) {
		return delete_shopping_list(id);
	}

	/**
	 *	Here you can add a product to a List.
	 */
	@PostMapping("/product")
	public String addProduct(@RequestBody ProductForm productForm) {
		return create_product(productForm.getProductname(), productForm.getProductListId(), productForm.getAmount());
	}
	/**
	 *	Here you can edit a product from a List.
	 */
	@PutMapping("/product")
		public String editProduct(@RequestBody ProductForm productForm) {
			return update_product(productForm.getProductname(), productForm.get_product_id(), productForm.getAmount());
	}
	/**
	 *	Here you can delete a product from a List.
	 */
	@DeleteMapping("/product")
	public String deleteProduct(@RequestBody Integer id) {
		return delete_product(id);
	}
}
