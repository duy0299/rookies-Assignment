package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Product} entity
 */
@Data
@NoArgsConstructor
public class ProductDtoFlat{
    private  UUID id;
    private  String name;
    private  String avatar;
    private  String sale_type;
    @Min(value = 0)
    private  BigDecimal price_sale;
    @Min(value = 0)
    private  int quantity;
    private  int sold_product_quantity;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public ProductDtoFlat(Product product) {
        id = product.getId();
        name = product.getName();
        avatar = product.getAvatar();
        sale_type = product.getSale_type();
        price_sale = product.getPrice_sale();
        quantity = product.getQuantity();
        sold_product_quantity = product.getSold_product_quantity();
        status = product.isStatus();
        time_create = product.getTime_create();
        time_update = product.getTime_update();
    }
}