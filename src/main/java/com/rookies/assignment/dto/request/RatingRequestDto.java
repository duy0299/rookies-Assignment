package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Rating;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.RatingDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
public class RatingRequestDto extends RatingDtoFlat {
    private UUID user_id;
    private UUID modelId;


    public Rating changeToRatingInsert(UserInfo userInfo, ProductModel model){
        Rating rating = new Rating();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        rating.setContent(getContent());
        rating.setRating(getRating());
        rating.setUserInfo(userInfo);
        rating.setModel(model);
        rating.setStatus((short) 1);
        rating.setTimeCreate(now);
        rating.setTimeUpdate(now);
        return rating;
    }

//    only for users to update
    public Rating changeToRatingUpdate(Rating ratingOld){
        Rating rating = new Rating();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        rating.setId(getId());
        rating.setContent(getContent());
        rating.setRating(getRating());
        rating.setUserInfo(ratingOld.getUserInfo());
        rating.setModel(ratingOld.getModel());
        rating.setStatus((short) 1);
        rating.setTimeUpdate(now);
        rating.setTimeCreate(ratingOld.getTimeCreate());


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