package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.ModelImage;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ModelRequestUpdateImageDto extends ProductModelDtoFlat {
    private UUID productModelID;
    private List<MultipartFile> listImages;


    public ProductModel changeToProductModel(List<ModelImage> listImage, ProductModel oldModel){
        ProductModel model = oldModel;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        model.setTimeUpdate(now);
        model.setListImages(listImage);
        return model;
    }




}