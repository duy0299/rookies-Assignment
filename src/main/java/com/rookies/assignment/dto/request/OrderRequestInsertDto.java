package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.OrderDtoFlat;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class OrderRequestInsertDto {
    @NotEmpty
    @NotNull
    private String address;

    private String note = "";

    private List<CartRequestDto> listProduct;


    public Order changeToOrderInsert(UserInfo user,List<CartDto> listCart){
        Order order = new Order();
        List<OrderItem> listItem = new ArrayList<>();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        order.setListItems(setListOrderItem(listCart, order));
        order.setStatus((short) 1);
        order.setUser(user);
        order.setAddress(address);
        order.setNote(note);
        order.setTimeCreate(now);
        order.setTimeUpdate(now);

        return order;
    }

    public  List<OrderItem> setListOrderItem(List<CartDto> listCart, Order order){
        List<OrderItem> listItem = new ArrayList<>();
        if(listCart == null){
            throw new ResourceFoundException("không có sản phẩm nào trong giỏ hàng");
        }
        for (CartDto cart:listCart) {
            OrderItem item = cart.changeToOrderItem();
            item.setOrder(order);
            listItem.add(item);
        }
        return listItem;
    }
}