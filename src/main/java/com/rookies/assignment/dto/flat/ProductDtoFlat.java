package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Product} entity
 */
@Data
@NoArgsConstructor
public class ProductDtoFlat{
    private  UUID id;
    private  String name;
    private  String avatar;
    private  String saleType;
    @Min(value = 0)
    private  BigDecimal priceSale;
    @Min(value = 0)
    private  int quantity;
    private  int soldProductQuantity;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public ProductDtoFlat(Product product) {
        id = product.getId();
        name = product.getName();
        avatar = product.getAvatar();
        saleType = product.getSaleType();
        priceSale = product.getPriceSale();
        quantity = product.getQuantity();
        soldProductQuantity = product.getSoldProductQuantity();
        status = product.isStatus();
        timeCreate = product.getTimeCreate();
        timeUpdate = product.getTimeUpdate();
    }
}