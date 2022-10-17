package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.LoginRequestDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private IUserInfoRepository repository;



    @Override
    public UserInfoResponseDto insert(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfoResponseDto update(UserInfo userInfo) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void updateStatus(UUID id, short ststus) {

    }

    @Override
    public ResponseDto<UserInfoResponseDto> getById(UUID id) {
        Optional<UserInfo> user = Optional.ofNullable(repository.getById(id));

        if(user.isEmpty()){
            return new ResponseDto<UserInfoResponseDto>("01", "Mật khẩu hoặc Email Không đúng", null);
        }
        return new ResponseDto<UserInfoResponseDto>("00",
                "thành công",
                new UserInfoResponseDto(user.get()));
    }

    @Override
    public List<UserInfoResponseDto> listAll() {
        return null;
    }

    @Override
    public UserInfoResponseDto updatePassword() {
        return null;
    }


    @Override
    public UserInfoResponseDto register() {
        return null;
    }


}
