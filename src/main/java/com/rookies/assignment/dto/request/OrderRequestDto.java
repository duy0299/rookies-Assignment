package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.OrderDtoFlat;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class OrderRequestDto extends OrderDtoFlat {
    @NotEmpty
    @NotNull
    private UUID user_id;

    @NotEmpty
    @NotNull
    private String address;

    private String note = "";


    public Order changeToOrderInsert(UserInfo user,List<CartDto> listCart){
        Order order = new Order();
        List<OrderItem> listItem = new ArrayList<>();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        order.setListItems(setListOrderItem(listCart));
        order.setStatus((short) 1);
        order.setUser(user);
        order.setAddress(address);
        order.setNote(note);
        order.setTimeCreate(now);
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