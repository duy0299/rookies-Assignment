package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

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
    public UserInfoResponseDto getById(UUID id) {
        Optional<UserInfo> checkOp;

        return null;
    }

    @Override
    public List<UserInfoResponseDto> listAll() {
        return null;
    }

    @Override
    public UserInfoResponseDto updatePassword() {
        return null;
    }
}
