package com.rookies.assignment.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookies.assignment.entity.UserRole;

@Component
public interface IUserRoleService {

	public UserRole insert(UserRole userRole);
	
	public UserRole update(UserRole userRole);
	
	public void delete(Integer id);
	
	public UserRole getById(Integer id);

	public List<UserRole> listAll();
}
