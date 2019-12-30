package com.sb.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.demo.entity.Product;
import com.sb.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> getProducts() {
		
		return repository.findAll();
	}

	public Product saveProduct(Product product) {
		
		if (null == product.getCreateDateTime()) {
			product.setCreateDateTime(LocalDateTime.now());
			product.setUpdatedDateTime(LocalDateTime.now());
		}
		return repository.save(product);
	}

}
