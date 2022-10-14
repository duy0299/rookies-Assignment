package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.Categories;

@Component
public interface ICategoriesService {

	public Categories insert(Categories categories);
	
	public Categories update(Categories categories);
	
	public void delete(UUID id);
	
	public Categories getById(UUID id);

	public List<Categories> listAll();
}
