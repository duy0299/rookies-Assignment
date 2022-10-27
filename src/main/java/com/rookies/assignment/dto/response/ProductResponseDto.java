package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.dto.flat.ProductDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import lombok.Data;


@Data
public class ProductResponseDto extends ProductDtoFlat {

    private SizeDtoFlat size;
    private ProductModelDtoFlat model;

    public ProductResponseDto(Product product) {
        super(product);
        size = new SizeDtoFlat(product.getSize());
        model = new ProductModelDtoFlat(product.getModel());
    }

    public  Product changeToProduct(){
        Product product = new Product();

        product.setId(getId());
        product.setSize(size.changeToSize());
        product.setModel(model.changeToProductModelFlat());
        product.setName(getName());
        product.setAvatar(getAvatar());
        product.setSaleType(getSaleType());
        product.setPriceSale(getPriceSale());
        product.setQuantity(getQuantity());
        product.setSoldProductQuantity(getSoldProductQuantity());
        product.setStatus(isStatus());
        product.setTimeCreate(getTimeCreate());
        product.setTimeUpdate(getTimeUpdate());

        return product;
    }




}