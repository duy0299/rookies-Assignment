package com.rookies.assignment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.rookies.assignment.data.entity.Role;

@Component
public interface IRoleService {
	
	public Role update(Role role);
	
	public void delete(UUID id);
	
	public Role getById(UUID id);

	public List<Role> listAll();
	
}
