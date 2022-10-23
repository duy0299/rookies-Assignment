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
public class ProductRequestUpdateAvatarDto{
    @NotNull
    @NotEmpty
    private UUID productID;

    @NotNull
    @NotEmpty
    private MultipartFile fileAvatar;

    public  Product changeToProduct(Product oldProduct, String urlAvatar){
        Product product = oldProduct;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        product.setAvatar(urlAvatar);
        product.setTimeUpdate(now);
        return product;
    }



}