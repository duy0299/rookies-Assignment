package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestRegisterDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String email;
    private String avatar;
    private String password;
    private String passwordConfirmation;

    public UserInfo changeToUserInfo(Role role){
        UserInfo user = new UserInfo();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        List<Role> listRole = new ArrayList<>();
        listRole.add(role);

        boolean isGender = true;
        if(gender.equals("female")){
            isGender = false;
        }

        user.setStatus(true);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setGender(isGender);
        user.setEmail(email);
        user.setAvatar(avatar);
        user.setPassword(password);
        user.setTimeCreate(now);
        user.setTimeUpdate(now);
        user.setListRole(listRole);

        return user;
    }

}