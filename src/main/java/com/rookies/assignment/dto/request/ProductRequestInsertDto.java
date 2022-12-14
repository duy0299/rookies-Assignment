package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.exceptions.ResourceFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestInsertDto{
    private  UUID modelID;
    @NotNull
    @NotEmpty
    @javax.validation.constraints.Size(min = 1, max = 50)
    private  String name;
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

    @NotNull
    @NotEmpty
    private int sizeID;
    @NotNull
    @NotEmpty
    private MultipartFile fileAvatar;

    public  Product changeToProduct(Size size, ProductModel model ,String urlAvatar){
        Product product = new Product();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        product.setSize(size);
        product.setModel(model);
        product.setName(name);
        product.setAvatar(urlAvatar);
        product.setSaleType(saleType);
        product.setPriceSale(priceSale);
        product.setQuantity(quantity);
        product.setSoldProductQuantity(0);
        product.setStatus(true);
        product.setTimeCreate(now);
        product.setTimeUpdate(now);

        return product;
    }

    public  Product changeProductToInsertModel(String urlAvatar){
        Product product = new Product();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        product.setAvatar(urlAvatar);

        product.setName(getName());
        product.setSaleType(getSaleType());
        product.setPriceSale(getPriceSale());
        product.setQuantity(getQuantity());
        product.setSoldProductQuantity(0);
        product.setStatus(true);
        product.setTimeCreate(now);
        product.setTimeUpdate(now);

        return product;
    }

    public  void checkPriceSale(BigDecimal priceRoot){
        switch (getSaleType().toLowerCase()){
            case "percent":{
                if(getPriceSale().floatValue() < 0 ||  getPriceSale().floatValue() >= 100){
                    throw new ResourceFoundException("t??? l??? gi???m sai, h??y ch???n t??? 0-100");
                }
                break;
            }
            case "direct subtract":{
                if(getPriceSale().floatValue() < 0 ||  getPriceSale().compareTo(priceRoot) == 1){
                    throw new ResourceFoundException("t??? l??? gi???m sai, kh??ng ???????c gi???m nhi???u h??n gi?? g???c");
                }
                break;
            }
            case "add directly":{
                if(getPriceSale().floatValue() < 0){
                    throw new ResourceFoundException("t??? l??? t??ng  sai, h??y ch???n l???i");
                }
                break;
            }

        }

    }

}