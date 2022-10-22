package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
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
public class UserRequestDto extends UserInfoDtoFlat {
    private int i;

}