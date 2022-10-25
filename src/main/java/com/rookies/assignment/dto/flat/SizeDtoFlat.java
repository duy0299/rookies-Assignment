package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class SizeDtoFlat {
    private  int id;
    @NotNull
    @NotEmpty
    @javax.validation.constraints.Size(min = 1, max = 50)
    private  String name;

    private  boolean status = true;
    private  Timestamp timeUpdate;

    private  Timestamp timeCreate;

    public SizeDtoFlat(Size size) {
        id = size.getId();
        name = size.getName();
        status = size.isStatus();
        timeUpdate = size.getTimeUpdate();
    }

    public Size changeToSizeUpdate(Size sizeOld){
        Size size = new Size();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        size.setListProduct(sizeOld.getListProduct());
        size.setStatus(sizeOld.isStatus());
        size.setId(id);
        size.setName(name);
        size.setTimeUpdate(now);
        size.setTimeCreate(sizeOld.getTimeCreate());
        return size;
    }

    public Size changeToSize(){
        Size size = new Size();

        size.setStatus(isStatus());
        size.setId(getId());
        size.setName(getName());
        size.setTimeUpdate(getTimeUpdate());
        size.setTimeCreate(getTimeCreate());
        return size;
    }

    public Size changeToSizeInsert(){
        Size size = new Size();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        size.setListProduct(new ArrayList<>());
        size.setStatus(true);
        size.setName(name);
        size.setTimeUpdate(now);
        size.setTimeCreate(now);
        return size;
    }

}