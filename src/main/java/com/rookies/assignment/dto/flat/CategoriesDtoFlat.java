package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoriesDtoFlat {
    private  Integer id;
    @NotEmpty
    private  Integer parentCategoriesId;
    private  boolean status;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private  String name;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 200)
    private  String description;

    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public CategoriesDtoFlat(Categories categories) {
        id = categories.getId();
        parentCategoriesId = categories.getParentCategoriesId();
        name = categories.getName();
        description = categories.getDescription();
        status = categories.isStatus();
        timeCreate = categories.getTimeCreate();
        timeUpdate = categories.getTimeUpdate();
    }

    public Categories changeToCategoriesInsert(){
        Categories categories = new Categories();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        categories.setName(name);
        categories.setTimeCreate(now);
        categories.setTimeUpdate(now);
        categories.setDescription(description);
        categories.setParentCategoriesId(parentCategoriesId);
        return categories;
    }

    public Categories changeToCategoriesUpdate(Categories oldCategories){
        Categories categories = new Categories();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        categories.setListModel(oldCategories.getListModel());
        categories.setId(id);
        categories.setName(name);
        categories.setTimeUpdate(now);
        categories.setDescription(description);
        categories.setParentCategoriesId(parentCategoriesId);
        categories.setTimeCreate(oldCategories.getTimeCreate());
        return categories;
    }
}