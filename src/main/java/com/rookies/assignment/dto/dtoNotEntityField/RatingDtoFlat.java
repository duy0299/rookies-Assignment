package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Product;
import com.rookies.assignment.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Rating} entity
 */
@Data
@NoArgsConstructor
public class RatingDtoFlat{
    private  int id;
    private  int content;
    private  int rating;
    private  short status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public RatingDtoFlat(Rating ratingI) {
        id = ratingI.getId();
        content = ratingI.getContent();
        rating = ratingI.getRating();
        status = ratingI.getStatus();
        time_create = ratingI.getTime_create();
        time_update = ratingI.getTime_update();
    }
}