package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Order;

@Component
public interface IOrderService {

	public Order insert(Order order);
	
	public Order update(Order order);
	
	public void delete(UUID id);
	
	public Order getById(UUID id);

	public List<Order> listAll();
	
	
}
