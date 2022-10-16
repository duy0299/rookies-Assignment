package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.OrderItem} entity
 */
@Data
@NoArgsConstructor
public class OrderItemDtoFlat{
    private  int id;
    @Min(value = 1)
    private  int quantity;
    private  Timestamp timeCreate;

    public OrderItemDtoFlat(OrderItem item){
        id = item.getId();
        quantity = item.getQuantity();
        timeCreate = item.getTimeCreate();
    }
}