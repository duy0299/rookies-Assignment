package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Rating;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.RatingDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Rating} entity
 */
@Data
@NoArgsConstructor
public class RatingResponseDto extends RatingDtoFlat {
    private UserInfoDtoFlat userInfo;
    private ProductModelDtoFlat model;

    public RatingResponseDto(Rating rating){
        super(rating);
        userInfo = new UserInfoDtoFlat(rating.getUserInfo());
        model = new ProductModelDtoFlat(rating.getModel());
    }

}