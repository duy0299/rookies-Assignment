package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.UserInfo;

@Component
public interface IUserInfoService {

	public UserInfo insert(UserInfo userInfo);
	
	public UserInfo update(UserInfo userInfo);
	
	public void delete(UUID id);
	
	public UserInfo getById(UUID id);

	public List<UserInfo> listAll();

	public UserInfo updatePassword();
}
