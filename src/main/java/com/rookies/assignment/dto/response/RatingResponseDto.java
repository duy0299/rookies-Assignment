package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.RatingDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.data.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Rating} entity
 */
@Data
@NoArgsConstructor
public class RatingResponseDto extends RatingDtoFlat {
    private UserInfoDtoFlat userInfo;
    private ProductModelDtoFlat modelDtoFlat;

    public RatingResponseDto(Rating rating){
        super(rating);
        userInfo = new UserInfoDtoFlat(rating.getUserInfo());
        modelDtoFlat = new ProductModelDtoFlat(rating.getModel());
    }
}