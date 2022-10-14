package com.rookies.assignment.service;

import java.util.List;

import com.rookies.assignment.entity.CategoriesProductModel;
import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.CategoriesProductModel;

@Component
public interface ICategoriesProductModelService {
	
	public CategoriesProductModel insert(CategoriesProductModel categoriesProductModel);
	
	public CategoriesProductModel update(CategoriesProductModel categoriesProductModel);
	
	public void delete(Integer id);
	
	public CategoriesProductModel getById(Integer id);

	public List<CategoriesProductModel> listAll();
}
