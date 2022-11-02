package com.rookies.assignment.dto.request.productmodel;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ModelRequestUpdateDto{
    @NotNull
    @NotEmpty
    private UUID id;
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

    private Integer categoriesID;

    public ProductModel changeToProductModel(Categories categories, ProductModel modelOld){
        ProductModel model = modelOld;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        model.setName(name);
        model.setPriceRoot(priceRoot);
        model.setDescription(description);
        model.setStatus(true);
        model.setTimeUpdate(now);
        model.setCategories(categories);

        return model;
    }
}