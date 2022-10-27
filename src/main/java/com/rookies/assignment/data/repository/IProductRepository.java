package com.rookies.assignment.data.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookies.assignment.data.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>{
    Page<Product> findByStatus(boolean status, Pageable pageable);
    Page<Product> findByNameAndStatus(String name, boolean status, Pageable pageable);

    List<Product> findBySizeId(Integer sizeID);
}
