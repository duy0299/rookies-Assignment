package com.rookies.assignment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.OrderItem;

@Component
public interface IOrderItemService {

	public OrderItem insert(OrderItem item);
	
	public OrderItem update(OrderItem item);
	
	public void delete(Integer id);
	
	public OrderItem getById(Integer id);

	public List<OrderItem> listAll();
}
