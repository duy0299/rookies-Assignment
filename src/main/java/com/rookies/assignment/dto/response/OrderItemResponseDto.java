package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.OrderItem;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.dto.flat.ProductDtoFlat;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link OrderItem} entity
 */
@Data
public class OrderItemResponseDto{
    private int id;
    private Productitem product;
    @Min(value = 1)
    private int quantity;
    private Timestamp timeCreate;

    public OrderItemResponseDto(OrderItem item) {
        id = item.getId();
        product = new Productitem(item.getProduct());
        quantity =  item.getQuantity();
        timeCreate = item.getTimeCreate();

    }

    @Data
    public class Productitem extends  ProductDtoFlat {
        private SizeDtoFlat size;

        public Productitem(Product product) {
            super(product);
            size = new SizeDtoFlat(product.getSize());
        }
    }
}