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
public class WishlistRequestUpdateDto {
    private  int id;
    private  boolean status;


    public Wishlist changeToWishlistUpdateStatus(Wishlist oldWishlist){
        Wishlist wishlist = oldWishlist;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        wishlist.setStatus(status);
        wishlist.setTimeUpdate(now);
        return wishlist;
    }
}