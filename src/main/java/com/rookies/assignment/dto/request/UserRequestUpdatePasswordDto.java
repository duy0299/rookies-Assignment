package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdatePasswordDto  {

    @NotEmpty
    @NotNull
    private UUID user_id;
    @NotEmpty
    @NotNull
    private String password;
    @NotEmpty
    @NotNull
    private String newPassword;
    @NotEmpty
    @NotNull
    private String passwordConfirmation;


    public UserInfo  changeToUserInfo(UserInfo oldUserInfo){
        UserInfo user = oldUserInfo;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        user.setPassword(newPassword);
        user.setTimeUpdate(now);

        return user;
    }

}