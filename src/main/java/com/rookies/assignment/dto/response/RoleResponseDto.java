package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.RoleDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Role} entity
 */
@Data
@NoArgsConstructor
public class RoleResponseDto extends RoleDtoFlat {
    private List<UserInfoDtoFlat> listUser;

    public RoleResponseDto(Role role){
        super(role);
        listUser = setlistUser(role.getListUser());
    }

    private List<UserInfoDtoFlat> setlistUser(List<UserInfo> list){
        List<UserInfoDtoFlat> result = new ArrayList<>();
        for(UserInfo user : list){
            result.add(new UserInfoDtoFlat(user));
        }
        return result;
    }
}