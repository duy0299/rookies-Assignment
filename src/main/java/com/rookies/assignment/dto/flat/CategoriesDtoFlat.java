package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Categories;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Categories} entity
 */
@Data
@NoArgsConstructor
public class CategoriesDtoFlat {
    private  int id;
    private  int parentCategoriesId;
    private  String name;
    private  String description;
    private  boolean status;
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
}