package com.rookies.assignment.dto.request.user;

import com.rookies.assignment.data.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestUpdateAvatarDto {
    @NotNull
    @NotEmpty
    private UUID userID;

    @NotNull
    @NotEmpty
    private MultipartFile fileAvatar;

    public UserInfo changeToProduct(UserInfo oldUser, String urlAvatar){
        UserInfo user = oldUser;
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        user.setAvatar(urlAvatar);
        user.setTimeUpdate(now);
        return user;
    }



}