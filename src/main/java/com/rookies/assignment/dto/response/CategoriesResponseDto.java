package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
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
    private CategoriesDtoFlat parentCategories;

    public CategoriesResponseDto(Categories categories, Categories parent){
        super(categories);
        parentCategories = new CategoriesDtoFlat(parent);
        listModel = setlistProductModelFlat(categories.getListModel());
    }

    private List<ProductModelDtoFlat> setlistProductModelFlat(List<ProductModel> list){
        List<ProductModelDtoFlat> result = new ArrayList<>();
        for(ProductModel model : list){
            result.add(new ProductModelDtoFlat(model));
        }
        return result;
    }
}