package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ModelImage;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.flat.ModelImageDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.response.ProductResponseDto;
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
public class ModelRequestUpdateDto extends ProductModelDtoFlat {

    private List<Integer> listCategoriesID;

    public ProductModel changeToProductModel(List<Categories> listCategories, ProductModel modelOld){
        ProductModel model = modelOld;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        model.setName(getName());
        model.setPriceRoot(getPriceRoot());
        model.setDescription(getDescription());
        model.setStatus(true);
        model.setTimeUpdate(now);
        model.setListCategories(listCategories);

        return model;
    }
}