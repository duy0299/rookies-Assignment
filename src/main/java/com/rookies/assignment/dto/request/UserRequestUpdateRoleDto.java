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
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateRoleDto {
    private UUID userID;
    private List<Short> listRoleLevel;

}