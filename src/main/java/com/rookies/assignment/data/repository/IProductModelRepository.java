package com.rookies.assignment.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rookies.assignment.data.entity.ProductModel;

@Repository
public interface IProductModelRepository  extends JpaRepository<ProductModel, UUID>{

}
