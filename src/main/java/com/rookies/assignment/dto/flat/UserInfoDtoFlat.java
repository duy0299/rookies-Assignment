package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.UserInfo} entity
 */
@Data
@NoArgsConstructor
public class UserInfoDtoFlat {
    private  UUID id;
    private  String firstName;
    private  String lastName;
    private  String phoneNumber;
    private  boolean gender;
    private  String email;
    private  String avatar;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public UserInfoDtoFlat(UserInfo user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phoneNumber = user.getPhoneNumber();
        gender = user.isGender();
        email = user.getEmail();
        avatar = user.getAvatar();
        status = user.isStatus();
        timeCreate = user.getTimeCreate();
        timeUpdate = user.getTimeUpdate();
    }
}