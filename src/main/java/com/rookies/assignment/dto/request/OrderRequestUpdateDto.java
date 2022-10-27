package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.dto.flat.OrderDtoFlat;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class OrderRequestUpdateDto extends OrderDtoFlat {

    @NotNull
    @NotEmpty
    private UUID order_id;
    @NotNull
    @NotEmpty
    @Size(min = 0, max = 6)
    private short status ;


    public Order changeToOrderUpdateStatus(Order oleOrder){
        Order order = oleOrder;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        order.setId(order_id);
        order.setStatus(status);
        order.setTimeUpdate(now);

        return order;
    }

    public  List<OrderItem> setListOrderItem(List<CartDto> listCart){
        List<OrderItem> listItem = new ArrayList<>();
        if(listCart == null){
            throw new ResourceFoundException("không có sản phẩm nào trong giỏ hàng");
        }
        for (CartDto cart:listCart) {
            listItem.add(cart.changeToOrderItem());
        }
        return listItem;
    }
}