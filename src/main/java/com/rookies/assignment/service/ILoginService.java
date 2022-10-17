package com.rookies.assignment.service;

import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.request.UserRegisterRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public interface ILoginService {

    public ResponseDto<UserInfoResponseDto> login(LoginRequestDto dto, HttpServletRequest req);

    public ResponseDto<String> logout(HttpServletRequest req);

    public ResponseDto<UserInfoResponseDto> register(UserRegisterRequestDto dto,  HttpServletRequest req);



}
