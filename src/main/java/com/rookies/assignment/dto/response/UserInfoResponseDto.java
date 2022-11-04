package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.RoleDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class UserInfoResponseDto extends UserInfoDtoFlat {
    private List<RatingResponseDto> listRatings;
    private List<FeedbackDtoFlat> listFeedbacks;
    private List<WishlistResponseDto> listWishlists;
    private List<String> listRole;
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

    private List<String> setListRoles(List<Role> list){
        List<String> result = new ArrayList<>();
        if (list == null) {
            return result;
        }
        for(Role role : list){
            result.add(role.getName());
        }
        return result;
    }

    public boolean isOrderThisProductModel(UUID modelID){
//        xét xem User đã Order món này chưa
//        LỌC qua từng Order
        for (OrderResponseDto order : listOrder) {
//            lọc qua từng product trong Order
            for (OrderItemResponseDto item : order.getListItems()) {
                if(modelID.equals(item.getProduct().getModelID())){
                   return true;
                }
            }
        }
        return false;
    }

    @Data
    @NoArgsConstructor
    public class ProductModelDtoFlat{
        private  UUID id;
        @NotNull
        @NotEmpty
        @Size(min = 1, max = 50)
        private String name;
        @NotNull
        @NotEmpty
        @Min(value = 0)
        private BigDecimal priceRoot;
        @NotNull
        @NotEmpty
        private String description;
        private  boolean status;
        private Timestamp timeCreate;
        private  Timestamp timeUpdate;

        public ProductModelDtoFlat(ProductModel model) {
            id = model.getId();
            name = model.getName();
            priceRoot = model.getPriceRoot();
            description = model.getDescription();
            status = model.isStatus();
            timeCreate = model.getTimeCreate();
            timeUpdate = model.getTimeUpdate();
        }

        public ProductModel changeToProductModelFlat() {
            ProductModel model = new ProductModel();
            model.setId(id);
            model.setStatus(status);
            model.setName(name);
            model.setPriceRoot(priceRoot);
            model.setDescription(description);
            model.setTimeCreate(timeCreate);
            model.setTimeUpdate(timeUpdate);
            return model;
        }
    }
}