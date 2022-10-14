package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.OrderItem} entity
 */
@Data
@NoArgsConstructor
public class OrderItemDtoFlat{
    private  int id;
    @Min(value = 1)
    private  int quantity;
    private  Timestamp time_create;

    public OrderItemDtoFlat(OrderItem item){
        id = item.getId();
        quantity = item.getQuantity();
        time_create = item.getTime_create();
    }
}