package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Size} entity
 */
@Data
@NoArgsConstructor
public class SizeDtoFlat {
    private  int id;
    private  String name;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public SizeDtoFlat(Size size) {
        id = size.getId();
        name = size.getName();
        status = size.isStatus();
        timeCreate = size.getTimeCreate();
        timeUpdate = size.getTimeUpdate();
    }

}