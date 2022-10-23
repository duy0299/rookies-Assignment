package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.ModelImage;
import com.rookies.assignment.data.entity.ProductModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.PanelUI;

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

    public ModelImage changeToModelImage() {
        ModelImage image = new ModelImage();
        image.setUrlImage(urlImage);
        return image;
    }
}