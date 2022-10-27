package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.dto.flat.ProductDtoFlat;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class SizeResponseDto extends SizeDtoFlat {
    private List<ProductDtoFlat> productDto;

    public SizeResponseDto(Size size, List<Product> product){
        super(size);
        productDto = setListProduct(product);
    }


    private List<ProductDtoFlat> setListProduct(List<Product> list){
        List<ProductDtoFlat> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Product product : list){
            result.add(new ProductDtoFlat(product));
        }
        return result;
    }
}