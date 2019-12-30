package com.sb.demo.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.sb.demo.entity.Product;

@Repository
public interface ProductRepository extends CassandraRepository<Product, String> {

	@Override
	List<Product> findAll();
}
