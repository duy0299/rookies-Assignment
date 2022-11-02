package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
public class RatingRequestUpdateDto {
    private  int id;
    private  String content;
    @Min(1)
    @Max(5)
    private  int rating;
    @Min(1)
    @Max(3)
    private  short status;



//    only for users to update
    public Rating changeToRatingUpdate(Rating ratingOld){
        Rating rating = ratingOld;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        rating.setId(getId());
        rating.setContent(getContent());
        rating.setRating(getRating());
        rating.setStatus((short) 1);
        rating.setTimeUpdate(now);


        return rating;
    }

//only for Admin to update
    public Rating changeToRatingUpdateStatus(Rating ratingOld){
        Rating rating = new Rating();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        rating.setId(getId());
        rating.setContent(getContent());
        rating.setRating(getRating());
        rating.setUserInfo(ratingOld.getUserInfo());
        rating.setModel(ratingOld.getModel());
        rating.setStatus(getStatus());
        rating.setTimeUpdate(now);
        rating.setTimeCreate(ratingOld.getTimeCreate());

        return rating;
    }
}