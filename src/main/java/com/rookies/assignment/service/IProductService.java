package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Product;

@Component
public interface IProductService {

	public Product insert(Product product);
	
	public Product update(Product product);
	
	public void delete(UUID id);
	
	public Product getById(UUID id);

	public List<Product> listAll();
	
	public List<Product> listByProductModel(UUID productModelId);
	
}
