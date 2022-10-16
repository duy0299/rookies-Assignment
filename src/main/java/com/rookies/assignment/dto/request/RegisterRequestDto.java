package com.rookies.assignment.dto.request;


import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto extends UserInfoDtoFlat {
    private  String password;


}