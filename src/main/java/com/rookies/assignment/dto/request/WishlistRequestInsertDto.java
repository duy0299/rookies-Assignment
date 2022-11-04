package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.WishlistDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class WishlistRequestInsertDto {
    @NotEmpty
    @NotNull
    private UUID modelId;

    public Wishlist changeToWishlistInsert(UserInfo userInfo, ProductModel model){
        Wishlist wishlist = new Wishlist();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        wishlist.setUserInfo(userInfo);
        wishlist.setStatus(true);
        wishlist.setModel(model);
        wishlist.setTimeCreate(now);
        wishlist.setTimeUpdate(now);
        return wishlist;
    }


}