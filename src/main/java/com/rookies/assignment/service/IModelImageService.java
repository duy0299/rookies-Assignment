package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;


import com.rookies.assignment.data.entity.ModelImage;

@Component
public interface IModelImageService {

	public ModelImage insert(ModelImage image);
	
	public ModelImage update(ModelImage image);
	
	public void delete(Integer id);
	
	public ModelImage getById(Integer id);

	public List<ModelImage> listAll();
	
	public List<ModelImage> listByProductModel(UUID poductModelId);
}
