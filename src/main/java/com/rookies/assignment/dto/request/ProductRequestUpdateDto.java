package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.dto.flat.ProductDtoFlat;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestUpdateDto extends ProductDtoFlat {

    @NotNull
    @NotEmpty
    private int sizeID;

    @NotNull
    @NotEmpty
    private UUID modelID;

    public  Product changeToProduct(Size newSize, ProductModel newModel, Product oldProduct){
        Product product = oldProduct;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        product.setSize(newSize);
        product.setModel(newModel);
        product.setName(getName());
        product.setSaleType(getSaleType());
        product.setPriceSale(getPriceSale());
        product.setQuantity(getQuantity());
        product.setTimeUpdate(now);

        return product;
    }

    public  void isPriceSale(BigDecimal priceRoot){
        switch (getSaleType().toLowerCase()){
            case "percent":{
                if(getPriceSale().floatValue() < 0 ||  getPriceSale().floatValue() >= 100){
                    throw new ResourceFoundException("tỷ lệ giảm sai, hãy chọn từ 0-100");
                }
                break;
            }
            case "direct subtract":{
                if(getPriceSale().floatValue() < 0 ||  getPriceSale().compareTo(priceRoot) == 1){
                    throw new ResourceFoundException("tỷ lệ giảm sai, không được giảm nhiều hơn giá gốc");
                }
                break;
            }
            case "add directly":{
                if(getPriceSale().floatValue() < 0){
                    throw new ResourceFoundException("tỷ lệ tăng  sai, hãy chọn lại");
                }
                break;
            }

        }

    }

}