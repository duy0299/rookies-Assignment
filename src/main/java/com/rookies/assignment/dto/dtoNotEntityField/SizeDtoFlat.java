package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Size} entity
 */
@Data
@NoArgsConstructor
public class SizeDtoFlat {
    private  int id;
    private  String name;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public SizeDtoFlat(Size size) {
        id = size.getId();
        name = size.getName();
        status = size.isStatus();
        time_create = size.getTime_create();
        time_update = size.getTime_update();
    }

}