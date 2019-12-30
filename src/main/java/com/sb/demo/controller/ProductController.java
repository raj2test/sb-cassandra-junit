package com.sb.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.demo.entity.Product;
import com.sb.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping
	public List<Product> getProducts() {
		
		return service.getProducts();
	}	
	
	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		
		return service.saveProduct(product);
		
	}
}
