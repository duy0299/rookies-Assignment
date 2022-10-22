package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.RoleDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class UserInfoResponseDto extends UserInfoDtoFlat {
    private List<RatingResponseDto> listRatings;
    private List<FeedbackDtoFlat> listFeedbacks;
    private List<WishlistResponseDto> listWishlists;
    private List<RoleDtoFlat> listRole;
    private List<OrderResponseDto> listOrder;

    public UserInfoResponseDto(UserInfo user){
        super(user);
        listRatings = setListRatings(user.getListRatings());
        listFeedbacks = setListFeedbacks(user.getListFeedbacks());
        listWishlists = setListWishlists(user.getListWishlists());
        listRole = setListRoles(user.getListRole());
        listOrder = setListOrder(user.getListOrder());
    }

    private List<OrderResponseDto> setListOrder(List<Order> list){
        List<OrderResponseDto> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Order order : list){
            result.add(new OrderResponseDto(order));
        }
        return result;
    }
    private List<RatingResponseDto> setListRatings(List<Rating> list){
        List<RatingResponseDto> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Rating rating : list){
            result.add(new RatingResponseDto(rating));
        }
        return result;
    }

    private List<FeedbackDtoFlat> setListFeedbacks(List<Feedback> list){
        List<FeedbackDtoFlat> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Feedback feedback : list){
            result.add(new FeedbackDtoFlat(feedback));
        }
        return result;
    }

    private List<WishlistResponseDto> setListWishlists(List<Wishlist> list){
        List<WishlistResponseDto> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Wishlist wishlist : list){
            result.add(new WishlistResponseDto(wishlist));
        }
        return result;
    }

    private List<RoleDtoFlat> setListRoles(List<Role> list){
        List<RoleDtoFlat> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Role role : list){
            result.add(new RoleDtoFlat(role));
        }
        return result;
    }

    public boolean isOrderThisProductModel(UUID modelID){
//        xét xem User đã Order món này chưa
//        LỌC qua từng Order
        for (OrderResponseDto order : listOrder) {
//            lọc qua từng product trong Order
            for (OrderItemResponseDto item : order.getListItems()) {
                if(modelID.equals(item.getProduct().getModelId())){
                   return true;
                }
            }
        }
        return false;
    }
}