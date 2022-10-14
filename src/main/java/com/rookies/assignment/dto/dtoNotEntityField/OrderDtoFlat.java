package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Order} entity
 */
@Data
@NoArgsConstructor
public class OrderDtoFlat{
    private  UUID id;
    private  String first_name;
    private  String last_name;
    private  String phone_number;
    private  String email;
    private  String address;
    private  String note;
    private  short status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public OrderDtoFlat(Order order){
        id = order.getId();
        first_name = order.getFirst_name();
        last_name = order.getLast_name();
        phone_number = order.getPhone_number();
        email = order.getEmail();
        address = order.getAddress();
        note = order.getNote();
        status = order.getStatus();
        time_create = order.getTime_create();
        time_update = order.getTime_update();
    }
}