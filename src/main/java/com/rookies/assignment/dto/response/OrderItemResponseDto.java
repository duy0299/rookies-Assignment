package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.dto.flat.ProductDtoFlat;
import lombok.Data;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * A DTO for the {@link OrderItem} entity
 */
@Data
public class OrderItemResponseDto{
    private int id;
    private ProductDtoFlat product;
    @Min(value = 1)
    private int quantity;
    private Timestamp timeCreate;

    public OrderItemResponseDto(OrderItem item) {
        id = item.getId();
        product = new ProductDtoFlat(item.getProduct());
        quantity =  item.getQuantity();
        timeCreate = item.getTimeCreate();
    }
}