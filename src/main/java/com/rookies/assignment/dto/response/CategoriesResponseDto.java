package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Categories} entity
 */
@Data
@NoArgsConstructor
public class CategoriesResponseDto extends CategoriesDtoFlat {

    private List<ProductModelDtoFlat> listModel;
    private CategoriesDtoFlat childrenCategories;

    public CategoriesResponseDto(Categories categories, Categories children){
        super(categories);
        childrenCategories = new CategoriesDtoFlat(children);
        listModel = setlistProductModelFlat(categories.getListModel());
    }

    public CategoriesResponseDto(Categories categories){
        super(categories);
        listModel = setlistProductModelFlat(categories.getListModel());
    }

    private List<ProductModelDtoFlat> setlistProductModelFlat(List<ProductModel> list){
        List<ProductModelDtoFlat> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(ProductModel model : list){
            result.add(new ProductModelDtoFlat(model));
        }
        return result;
    }

}