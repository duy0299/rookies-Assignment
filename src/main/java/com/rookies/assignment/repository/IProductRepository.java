package com.rookies.assignment.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>{

}
