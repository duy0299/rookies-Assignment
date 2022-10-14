package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.Rating;

@Component
public interface IRatingService {

	public Rating insert(Rating rating);
	
	public Rating update(Rating rating);
	
	public void delete(Integer id);
	
	public Rating getById(Integer id);

	public List<Rating> listAll();
	
	public List<Rating> listByProductModel(UUID product_model_id);
}
