package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.RoleDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.data.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.UserInfo} entity
 */
@Data
@NoArgsConstructor
public class UserInfoResponseDto extends UserInfoDtoFlat {
    private List<RatingResponseDto> listRatings;
    private List<FeedbackDtoFlat> listFeedbacks;
    private List<WishlistDto> listWishlists;
    private List<RoleDtoFlat> listRole;

    public UserInfoResponseDto(UserInfo user){
        super(user);
        listRatings = setlistRatings(user.getListRatings());
        listFeedbacks = setFeedbacks(user.getListFeedbacks());
        listWishlists = setWishlists(user.getListWishlists());
        listRole = setRoles(user.getListRole());
    }

    private List<RatingResponseDto> setlistRatings(List<Rating> list){
        List<RatingResponseDto> result = new ArrayList<>();
        for(Rating rating : list){
            result.add(new RatingResponseDto(rating));
        }
        return result;
    }

    private List<FeedbackDtoFlat> setFeedbacks(List<Feedback> list){
        List<FeedbackDtoFlat> result = new ArrayList<>();
        for(Feedback feedback : list){
            result.add(new FeedbackDtoFlat(feedback));
        }
        return result;
    }

    private List<WishlistDto> setWishlists(List<Wishlist> list){
        List<WishlistDto> result = new ArrayList<>();
        for(Wishlist wishlist : list){
            result.add(new WishlistDto(wishlist));
        }
        return result;
    }

    private List<RoleDtoFlat> setRoles(List<Role> list){
        List<RoleDtoFlat> result = new ArrayList<>();
        for(Role role : list){
            result.add(new RoleDtoFlat(role));
        }
        return result;
    }
}