package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.data.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class CartDto {
    private Integer quantity;
    private ProductResponseDto product;
    private BigDecimal total;

    public CartDto(Product product, Integer quantity){
        this.quantity = quantity;
        this.product = new ProductResponseDto(product);
        setTotal();
    }

    public void setTotal(){
        total = new BigDecimal(this.quantity.intValue() * this.product.getCurrentPrice().intValue());
    }

    public OrderItem changeToOrderItem(){
        OrderItem item = new OrderItem();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        item.setProduct(product.changeToProduct());
        item.setQuantity(getQuantity());
        item.setTimeCreate(now);

        return item;
    }

    public boolean isValidQuantity(){
        if(quantity > product.getQuantity()){
            return false;
        }
        if(quantity < 0){
            return false;
        }
        return true;
    }

}
