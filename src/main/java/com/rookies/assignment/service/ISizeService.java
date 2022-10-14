package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.Size;

@Component
public interface ISizeService {

	public Size insert(Size size);
	
	public Size update(Size size);
	
	public void delete(UUID id);
	
	public Size getById(UUID id);

	public List<Size> listAll();
	
}
