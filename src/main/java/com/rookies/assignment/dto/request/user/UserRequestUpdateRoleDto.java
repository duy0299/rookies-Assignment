package com.rookies.assignment.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateRoleDto {
    private UUID userID;
    private List<String> ListRole;

}