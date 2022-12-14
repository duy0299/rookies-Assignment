package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class CategoriesResponseDto extends CategoriesDtoFlat {

    private List<ProductModelResponseDto> listModel;
    private List<CategoriesDtoFlat> listChildren;

    public CategoriesResponseDto(Categories categories, List<Categories> all){
        super(categories);
        listChildren = setlistListChildrent(all);
        listModel = setlistProductModel(categories.getListModel(), all);
    }

    public CategoriesResponseDto(Categories categories){
        super(categories);
        listModel = new ArrayList<>();
        listChildren = new ArrayList<>();
    }

    private List<ProductModelResponseDto> setlistProductModel(List<ProductModel> list, List<Categories> allCategories){
        List<ProductModelResponseDto> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(ProductModel model : list){
            result.add(new ProductModelResponseDto(model));
        }

        for (Categories categories: allCategories) {
            if(categories.getParentCategoriesId() == getId()){
                for(ProductModel model : categories.getListModel()){
                    result.add(new ProductModelResponseDto(model));
                }
            }
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