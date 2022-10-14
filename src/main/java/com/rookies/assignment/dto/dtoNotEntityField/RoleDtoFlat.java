package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Rating;
import com.rookies.assignment.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Role} entity
 */
@Data
@NoArgsConstructor
public class RoleDtoFlat{
    private  UUID id;
    private  String name;
    private  String description;
    private  short status;

    private  short level;

    public RoleDtoFlat(Role role) {
        id = role.getId();
        name = role.getName();
        level = role.getLevel();
        description = role.getDescription();
        status = role.getStatus();
    }
}