package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SizeDtoFlat {
    private  int id;
    @NotEmpty
    private  String name;

    private  boolean status;
    private  Timestamp timeUpdate;

    public SizeDtoFlat(Size size) {
        id = size.getId();
        name = size.getName();
        status = size.isStatus();
        timeUpdate = size.getTimeUpdate();
    }

    public Size changeToSizeUpdate(List<Product> listProduct){
        Size size = new Size();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        size.setListProduct(listProduct);
        size.setId(id);
        size.setStatus(status);
        size.setName(name);
        size.setTimeUpdate(now);
        return size;
    }

    public Size changeToSizeInsert(){
        Size size = new Size();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        size.setListProduct(new ArrayList<>());
        size.setId(id);
        size.setStatus(status);
        size.setName(name);
        size.setTimeUpdate(now);
        size.setTimeCreate(now);
        return size;
    }

}