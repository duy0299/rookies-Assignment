package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.UserInfo;

@Component
public interface IUserInfoService {

	public UserInfoResponseDto insert(UserInfo userInfo);
	
	public UserInfoResponseDto update(UserInfo userInfo);
	
	public void delete(UUID id);

	public void updateStatus(UUID id, short ststus);
	
	public ResponseDto<UserInfoResponseDto> getById(UUID id);

	public List<UserInfoResponseDto> listAll();

	public UserInfoResponseDto updatePassword();


	public UserInfoResponseDto register();
}
