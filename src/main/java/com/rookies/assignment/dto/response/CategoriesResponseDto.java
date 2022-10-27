package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
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
    private List<CategoriesDtoFlat> listChildren;

    public CategoriesResponseDto(Categories categories, List<Categories> all){
        super(categories);
        listChildren = setlistListChildrent(all);
        listModel = setlistProductModelFlat(categories.getListModel());
    }

    public CategoriesResponseDto(Categories categories){
        super(categories);
        listModel = setlistProductModelFlat(categories.getListModel());
        listChildren = new ArrayList<>();
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

    private List<CategoriesDtoFlat> setlistListChildrent(List<Categories> list){
        List<CategoriesDtoFlat> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for (Categories categories: list) {
            if(categories.getParentCategoriesId() == getId()){
                result.add(new CategoriesDtoFlat(categories));
            }
        }
        return result;
    }

}