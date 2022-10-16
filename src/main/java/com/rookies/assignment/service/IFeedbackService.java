package com.rookies.assignment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Feedback;

@Component
public interface IFeedbackService {

	public Feedback insert(Feedback feedback);
	
	public Feedback update(Feedback feedback);
	
	public void delete(Integer id);
	
	public Feedback getById(Integer id);

	public List<Feedback> listAll();
	
}
