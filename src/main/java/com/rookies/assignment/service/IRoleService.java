package com.rookies.assignment.service;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface IRoleService {

	public Role getByName(String name);

	public Role getById(UUID id);

	public List<Role> listAll();


}
