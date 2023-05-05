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
				int id = Integer.parseInt(idParam);
				/* Niel Funktion call */
				return getProducts_of_shopping_list(id);
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
	public String deleteList(@RequestParam(value = "id") String id) {
		return delete_shopping_list(Integer.valueOf(id));
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
		public String editProduct(@RequestParam(value = "product_id", required = true) Integer product_id, @RequestParam(value = "product_name", required = true) String product_name, @RequestParam(value = "amount", required = true) Integer amount) {
			return update_product(product_name,product_id, amount);
	}
	/**
	 *	Here you can delete a product from a List.
	 */
	@DeleteMapping("/product")
	public String deleteProduct(@RequestParam(value = "id", required = true) String id) {
		return delete_product(Integer.parseInt(id));
	}

}
