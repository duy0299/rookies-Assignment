package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.WishlistDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class WishlistRequestDto extends WishlistDtoFlat {
    private UUID modelId;
    private UUID user_id;

    public Wishlist changeToWishlistInsert(){
        Wishlist wishlist = new Wishlist();
        UserInfo userInfo = new UserInfo();
        ProductModel model = new ProductModel();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        userInfo.setId(user_id);
        model.setId(modelId);

        wishlist.setUserInfo(userInfo);
        wishlist.setModel(model);
        wishlist.setStatus(true);
        wishlist.setTimeCreate(now);
        wishlist.setTimeUpdate(now);
        return wishlist;
    }

    public Wishlist changeToWishlistUpdateStatus(){
        Wishlist wishlist = new Wishlist();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        wishlist.setStatus(true);
        wishlist.setTimeUpdate(now);
        return wishlist;
    }
}