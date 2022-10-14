package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Categories;
import com.rookies.assignment.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Categories} entity
 */
@Data
@NoArgsConstructor
public class CategoriesDtoFlat {
    private  int id;
    private  int parent_categories_id;
    private  String name;
    private  String description;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public CategoriesDtoFlat(Categories categories) {
        id = categories.getId();
        parent_categories_id = categories.getParent_categories_id();
        name = categories.getName();
        description = categories.getDescription();
        status = categories.isStatus();
        time_create = categories.getTime_create();
        time_update = categories.getTime_update();
    }
}