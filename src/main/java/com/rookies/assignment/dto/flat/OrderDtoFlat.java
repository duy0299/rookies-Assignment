package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Order} entity
 */
@Data
@NoArgsConstructor
public class OrderDtoFlat{
    private  UUID id;
    @NotNull
    @NotEmpty
    private  String address;
    private  String note;
    private  short status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public OrderDtoFlat(Order order){
        id = order.getId();
        address = order.getAddress();
        note = order.getNote();
        status = order.getStatus();
        timeCreate = order.getTimeCreate();
        timeUpdate = order.getTimeUpdate();
    }
}