package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CartRequestDto {
    @Min(1)
    @NotNull
    @NotEmpty
    private Integer quantity;

    @NotNull
    @NotEmpty
    private UUID productId;

    public CartRequestDto(Product product, Integer quantity){
        
    }
}
