package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Wishlist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Wishlist} entity
 */
@Data
@NoArgsConstructor
public class WishlistDtoFlat{
    private  int id;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public  WishlistDtoFlat(Wishlist  wishlist){
        id  =   wishlist.getId();
        status  =   wishlist.isStatus();
        time_create  =   wishlist.getTime_create();
        time_update  =   wishlist.getTime_update();
    }
}