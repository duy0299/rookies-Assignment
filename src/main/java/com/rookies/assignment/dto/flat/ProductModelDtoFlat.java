package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.ProductModel} entity
 */
@Data
@NoArgsConstructor
public class ProductModelDtoFlat{
    private  UUID id;
    private  String name;
    private  BigDecimal priceRoot;
    private  String description;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public ProductModelDtoFlat(ProductModel model) {
        id = model.getId();
        name = model.getName();
        priceRoot = model.getPriceRoot();
        description = model.getDescription();
        status = model.isStatus();
        timeCreate = model.getTimeCreate();
        timeUpdate = model.getTimeUpdate();
    }
}