package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.flat.WishlistDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Wishlist} entity
 */
@Data
@NoArgsConstructor
public class WishlistDto extends WishlistDtoFlat {
    private UserInfoDtoFlat userInfo;
    private ProductModelDtoFlat modelDtoFlat;
    public WishlistDto(Wishlist wishlist){
        super(wishlist);
        userInfo = new UserInfoDtoFlat(wishlist.getUserInfo());
        modelDtoFlat = new ProductModelDtoFlat(wishlist.getModel());
    }
}