package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.ProductModel;

@Component
public interface IProductModelService {

	public ProductModel insert(ProductModel model);
	
	public ProductModel update(ProductModel model);
	
	public void delete(UUID id);
	
	public ProductModel getById(UUID id);

	public List<ProductModel> listAll();
}
