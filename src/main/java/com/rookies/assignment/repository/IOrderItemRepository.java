package com.rookies.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rookies.assignment.entity.OrderItem;

@Repository
public interface IOrderItemRepository  extends JpaRepository<OrderItem, Integer>{

}