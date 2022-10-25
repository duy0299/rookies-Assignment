package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.data.entity.Rating;
import com.rookies.assignment.dto.flat.OrderDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class OrderResponseDto extends OrderDtoFlat {
    private List<OrderItemResponseDto> listItems;
    private UserInfoDtoFlat user;

    public OrderResponseDto(Order order) {
        super(order);
        user = new UserInfoDtoFlat(order.getUser());
        listItems = setListItems(order.getListItems());
    }


    private List<OrderItemResponseDto> setListItems(List<OrderItem> list){
        List<OrderItemResponseDto> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(OrderItem item : list){
            result.add(new OrderItemResponseDto(item));
        }
        return result;
    }
}