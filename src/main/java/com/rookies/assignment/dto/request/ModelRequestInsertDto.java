package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ModelImage;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
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
public class ModelRequestInsertDto extends ProductModelDtoFlat {
    private List<ProductRequestInsertDto> listProduct;
    private List<MultipartFile> listImages;
    private List<Integer> listCategoriesID;


    public ProductModel changeToProductModel(List<Categories> listCategories, List<Product> listProduct, List<ModelImage> listImage){
        ProductModel model = new ProductModel();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        model.setName(getName());
        model.setPriceRoot(getPriceRoot());
        model.setDescription(getDescription());
        model.setStatus(true);
        model.setTimeCreate(now);
        model.setTimeUpdate(now);

        model.setListCategories(listCategories);
        model.setListImages(listImage);
        model.setListProduct(listProduct);

        return model;
    }




}