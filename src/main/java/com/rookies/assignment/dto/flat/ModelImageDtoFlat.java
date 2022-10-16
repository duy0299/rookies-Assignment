package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.ModelImage;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.ModelImage} entity
 */
@Data
@NoArgsConstructor
public class ModelImageDtoFlat {
    private int id;
    private String urlImage;

    public  ModelImageDtoFlat(ModelImage image){
        id = image.getId();
        urlImage = image.getUrlImage();
    }
}