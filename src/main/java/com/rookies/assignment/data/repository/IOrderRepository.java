package com.rookies.assignment.data.repository;

import com.rookies.assignment.data.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.Order;

import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order, UUID>{
    Page<Order> findAll(Pageable pageable);
}
