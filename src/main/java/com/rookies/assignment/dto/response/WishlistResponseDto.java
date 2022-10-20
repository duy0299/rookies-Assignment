package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.flat.WishlistDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class WishlistResponseDto extends WishlistDtoFlat {
    private ProductModelDtoFlat model;
    private UserInfoDtoFlat userInfo;

    public WishlistResponseDto(Wishlist wishlist){
        super(wishlist);
        model = new ProductModelResponseDto(wishlist.getModel());
        userInfo = new UserInfoResponseDto(wishlist.getUserInfo());
    }
}