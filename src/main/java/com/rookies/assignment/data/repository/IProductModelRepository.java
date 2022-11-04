package com.rookies.assignment.data.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.rookies.assignment.data.entity.ProductModel;

@Repository
public interface IProductModelRepository  extends JpaRepository<ProductModel, UUID>{
    Page<ProductModel> findByStatus(boolean status, Pageable pageable);

    Page<ProductModel> findAll(Pageable pageable);
    Page<ProductModel> findByNameAndStatus(String name, boolean status, Pageable pageable);

    Page<ProductModel> findByCategoriesIdAndStatus(int categoriesID, boolean status, Pageable pageable);

    Page<ProductModel> findByCategoriesIdInAndStatusTrue(List<Integer> categoriesID, Pageable pageable);

//    @Query("SELECT * FROM product_model WHERE price_root > ?1 AND price_root < ?2  AND status = true")
//    Page<ProductModel> findByPriceFromTo(BigDecimal priceFrom, BigDecimal priceTo);
}
