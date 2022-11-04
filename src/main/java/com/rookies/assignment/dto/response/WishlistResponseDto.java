package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.flat.WishlistDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@NoArgsConstructor
public class WishlistResponseDto extends WishlistDtoFlat {
    private ProductModelResponseDto model;
    private UserInfoDtoFlat userInfo;

    public WishlistResponseDto(Wishlist wishlist){
        super(wishlist);
        model = new ProductModelResponseDto(wishlist.getModel());
        userInfo = new UserInfoDtoFlat(wishlist.getUserInfo());

    }
}