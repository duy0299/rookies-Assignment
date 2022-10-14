package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Role;
import com.rookies.assignment.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.entity.UserInfo} entity
 */
@Data
@NoArgsConstructor
public class UserInfoDtoFlat {
    private  UUID id;
    private  String first_name;
    private  String last_name;
    private  String phone_number;
    private  boolean gender;
    private  String email;
    private  String avatar;
    private  boolean status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public UserInfoDtoFlat(UserInfo user) {
        id = user.getId();
        first_name = user.getFirst_name();
        last_name = user.getLast_name();
        phone_number = user.getPhone_number();
        gender = user.isGender();
        email = user.getEmail();
        avatar = user.getAvatar();
        status = user.isStatus();
        time_create = user.getTime_create();
        time_update = user.getTime_update();
    }
}