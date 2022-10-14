package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.ModelImage;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link com.rookies.assignment.entity.ModelImage} entity
 */
@Data
@NoArgsConstructor
public class ModelImageDtoFlat {
    private int id;
    private String name;

    public  ModelImageDtoFlat(ModelImage image){
        id = image.getId();
        name = image.getName();
    }
}