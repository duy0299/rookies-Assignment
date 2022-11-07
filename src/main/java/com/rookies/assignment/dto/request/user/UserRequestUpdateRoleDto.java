package com.rookies.assignment.dto.request.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateRoleDto {
    private UUID userID;
    @NotNull
    @NotEmpty
    private List<String> listRole;

}