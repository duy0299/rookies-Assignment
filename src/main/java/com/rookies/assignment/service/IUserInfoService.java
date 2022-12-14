package com.rookies.assignment.service;

import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.user.UserRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdatePasswordDto;
import com.rookies.assignment.dto.request.user.UserRequestUpdateRoleDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Component
public interface IUserInfoService {

	public ResponseDto<UserInfoResponseDto> update(UserInfoDtoFlat dto, HttpServletRequest request);
	
	public ResponseDto<UserInfoResponseDto> delete(UUID id);

	public ResponseDto<UserInfoResponseDto> updateStatus(UUID id, boolean status);
	
	public ResponseDto<UserInfoResponseDto> getById(UUID id);

	public ResponseDto<UserInfoResponseDto> getByToken(HttpServletRequest request);

	public ResponseByPageDto<List<UserInfoResponseDto>> listAll(int page, int size);

	public ResponseDto<UserInfoResponseDto> updatePassword(UserRequestUpdatePasswordDto dto, HttpServletRequest request);

	//    admin add or remove Role for User
	public ResponseDto<UserInfoResponseDto> updateRole(UserRequestUpdateRoleDto dto);

	public ResponseDto<UserInfoResponseDto> updateAvatar(UserRequestUpdateAvatarDto dto, HttpServletRequest request);

}
