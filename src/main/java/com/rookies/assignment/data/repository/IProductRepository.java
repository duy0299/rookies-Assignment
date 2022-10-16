package com.rookies.assignment.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>{

}
