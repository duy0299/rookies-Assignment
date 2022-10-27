package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Wishlist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
public class WishlistDtoFlat{
    private  int id;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public  WishlistDtoFlat(Wishlist  wishlist){
        id  =   wishlist.getId();
        status  =   wishlist.isStatus();
        timeCreate  =   wishlist.getTimeCreate();
        timeUpdate  =   wishlist.getTimeUpdate();
    }
}