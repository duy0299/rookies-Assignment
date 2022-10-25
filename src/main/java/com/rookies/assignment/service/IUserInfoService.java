package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import com.rookies.assignment.dto.request.*;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface IUserInfoService {

	public ResponseDto<UserInfoResponseDto> update(UserInfoDtoFlat dto);
	
	public ResponseDto<UserInfoResponseDto> delete(UUID id);

	public ResponseDto<UserInfoResponseDto> updateStatus(UUID id, boolean status);
	
	public ResponseDto<UserInfoResponseDto> getById(UUID id);

	public ResponseDto<List<UserInfoResponseDto>> listAll();

	public ResponseDto<UserInfoResponseDto> updatePassword(UserRequestUpdatePasswordDto dto);

	//    admin add or remove Role for User
	ResponseDto<UserInfoResponseDto> updateRole(UserRequestUpdateRoleDto dto);

	public ResponseDto<UserInfoResponseDto> updateAvatar(UserRequestUpdateAvatarDto dto);

}
