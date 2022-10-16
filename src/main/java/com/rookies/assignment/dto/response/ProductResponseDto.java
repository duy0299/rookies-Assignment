package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.ProductDtoFlat;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.data.entity.Product;
import lombok.Data;

import java.math.BigDecimal;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Product} entity
 */
@Data
public class ProductResponseDto extends ProductDtoFlat {

    private SizeDtoFlat size;
    private BigDecimal currentPrice;


    public ProductResponseDto(Product product) {
        super(product);
        size = new SizeDtoFlat(product.getSize());
        setCurrentPrice(product.getModel().getPriceRoot());
    }

    public void setCurrentPrice(BigDecimal priceRoot) {
        int price = 0;
        int priceSaleEX = getPriceSale().intValue();
        int priceRootEx = priceRoot.intValue();
        if(getSaleType().toLowerCase().equals("percent")) {
            price =  (int)Math.round((priceRootEx * (100-priceSaleEX))/100);
        }else if(getSaleType().toLowerCase().equals("direct")) {
            price = priceRoot.intValue() - (int)Math.round(priceSaleEX);
        }else {
            price = priceRoot.intValue();
        }
        currentPrice = new BigDecimal(price);
        currentPrice = currentPrice.setScale(2);
    }
}