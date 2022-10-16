package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Role} entity
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