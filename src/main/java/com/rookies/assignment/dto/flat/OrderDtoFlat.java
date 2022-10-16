package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Order} entity
 */
@Data
@NoArgsConstructor
public class OrderDtoFlat{
    private  UUID id;
    private  String firstName;
    private  String lastName;
    private  String phoneNumber;
    private  String email;
    private  String address;
    private  String note;
    private  short status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public OrderDtoFlat(Order order){
        id = order.getId();
        firstName = order.getFirstName();
        lastName = order.getLastName();
        phoneNumber = order.getPhoneNumber();
        email = order.getEmail();
        address = order.getAddress();
        note = order.getNote();
        status = order.getStatus();
        timeCreate = order.getTimeCreate();
        timeUpdate = order.getTimeUpdate();
    }
}