package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Rating} entity
 */
@Data
@NoArgsConstructor
public class RatingDtoFlat{
    private  int id;
    private  int content;
    private  int rating;
    private  short status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public RatingDtoFlat(Rating ratingI) {
        id = ratingI.getId();
        content = ratingI.getContent();
        rating = ratingI.getRating();
        status = ratingI.getStatus();
        timeCreate = ratingI.getTimeCreate();
        timeUpdate = ratingI.getTimeUpdate();
    }
}