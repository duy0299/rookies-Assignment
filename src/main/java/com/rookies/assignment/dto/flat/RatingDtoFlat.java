package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Rating} entity
 */
@Data
@NoArgsConstructor
public class RatingDtoFlat{
    private  int id;
    private  String content;
    @Min(1)
    @Max(5)
    private  int rating;
    @Min(1)
    @Max(3)
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