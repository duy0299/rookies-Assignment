package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Rating;
import com.rookies.assignment.data.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
public class RatingRequestInsertDto {
    private  String content;
    @Min(1)
    @Max(5)
    private  int rating;
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


}