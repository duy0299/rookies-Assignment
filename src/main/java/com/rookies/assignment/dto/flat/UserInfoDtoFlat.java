package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
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
    private  String gender;
    private  String email;
    private  String avatar;
    private  boolean status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public UserInfoDtoFlat( String firstName, String lastName, String phoneNumber, String gender, String email, boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.avatar = avatar;
        this.status = status;
    }

    public UserInfoDtoFlat(UserInfo user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        phoneNumber = user.getPhoneNumber();
        gender = setGenderDto(user.isGender());
        email = user.getEmail();
        avatar = user.getAvatar();
        status = user.isStatus();
        timeCreate = user.getTimeCreate();
        timeUpdate = user.getTimeUpdate();
    }

    public UserInfo  changeToUserInfoUpdate(UserInfo oldUserInfo){
        UserInfo user = oldUserInfo;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(setGenderUser(gender));
        user.setTimeUpdate(now);
        return user;
    }

    public boolean setGenderUser(String gender){
        if(gender.toLowerCase().equals("male") || gender.toLowerCase().equals("nam")){
            return true;
        }
        return false;
    }

    public String setGenderDto(boolean gender){
        if(gender == true){
            return "Nam";
        }
        return "Nữ";
    }
}