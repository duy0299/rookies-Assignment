package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Product;
import com.rookies.assignment.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.entity.ProductModel} entity
 */
@Data
@NoArgsConstructor
public class ProductModelDtoFlat{
    private  UUID id;
    private  String name;
    private  BigDecimal price_root;
    private  String description;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public ProductModelDtoFlat(ProductModel model) {
        id = model.getId();
        name = model.getName();
        price_root = model.getPrice_root();
        description = model.getDescription();
        status = model.isStatus();
        time_create = model.getTime_create();
        time_update = model.getTime_update();
    }
}