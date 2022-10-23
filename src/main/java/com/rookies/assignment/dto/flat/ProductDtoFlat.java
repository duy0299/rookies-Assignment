package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Product;
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
 * A DTO for the {@link com.rookies.assignment.data.entity.Product} entity
 */
@Data
@NoArgsConstructor
public class ProductDtoFlat{
    private  UUID id;
    private  UUID modelID;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private  String name;
    private  String avatar;

    @NotNull
    @NotEmpty
    private  String saleType;
    @NotNull
    @NotEmpty
    @Min(value = 0)
    private  BigDecimal priceSale;
    @NotNull
    @NotEmpty
    @Min(value = 0)
    private  int quantity;

    private  int soldProductQuantity;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    private  BigDecimal currentPrice;

    public ProductDtoFlat(Product product) {
        id = product.getId();
        modelID = product.getModel().getId();
        name = product.getName();
        avatar = product.getAvatar();
        saleType = product.getSaleType();
        priceSale = product.getPriceSale();
        quantity = product.getQuantity();
        soldProductQuantity = product.getSoldProductQuantity();
        status = product.isStatus();
        timeCreate = product.getTimeCreate();
        timeUpdate = product.getTimeUpdate();

        setCurrentPrice(product.getModel().getPriceRoot());
    }

    public void setCurrentPrice(BigDecimal priceRoot) {
        int price = 0;
        int priceSaleEX = getPriceSale().intValue();
        int priceRootEx = priceRoot.intValue();
        if(getSaleType().toLowerCase().equals("percent")) {
            price =  (int)Math.round((priceRootEx * (100-priceSaleEX))/100);
        }else if(getSaleType().toLowerCase().equals("direct")) {
            price = priceRootEx - (int)Math.round(priceSaleEX);
        }else if(getSaleType().toLowerCase().equals("add")) {
            price = priceRootEx+ (int)Math.round(priceSaleEX);
        }else {
            price = priceRootEx;
        }
        currentPrice = new BigDecimal(price);
        currentPrice = currentPrice.setScale(2);
    }
}