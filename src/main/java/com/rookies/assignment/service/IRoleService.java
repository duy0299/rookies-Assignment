package com.rookies.assignment.service;

import com.rookies.assignment.data.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface IRoleService {

	public Role getByName(String name);

	public Role getById(UUID id);

	public List<Role> listAll();


}
