package com.rookies.assignment.dto.request.productmodel;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ModelImage;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ModelAndProductRequestInsertDto{
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @NotEmpty
    @Min(value = 0)
    private BigDecimal priceRoot;
    @NotNull
    @NotEmpty
    private String description;

    private List<ProductRequestInsertDto> listProduct;
    private List<MultipartFile> listImages;
    private Integer categoriesID;


    public ProductModel changeToProductModel(Categories categories, List<Product> listProduct, List<ModelImage> listImage){
        ProductModel model = new ProductModel();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        model.setName(name);
        model.setPriceRoot(priceRoot);
        model.setDescription(description);
        model.setStatus(true);
        model.setTimeCreate(now);
        model.setTimeUpdate(now);
        model.setCategories(categories);
        for (Product product : listProduct) {
            product.setModel(model);
        }
        for (ModelImage image : listImage) {
            image.setModel(model);
        }
        model.setListImages(listImage);
        model.setListProduct(listProduct);
        return model;
    }




}