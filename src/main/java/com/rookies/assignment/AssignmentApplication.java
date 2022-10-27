package com.rookies.assignment;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.service.IRoleService;
import com.rookies.assignment.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AssignmentApplication {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserInfoService userService;

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@PostConstruct
	public void init() {
//		ResponseDto users =  userService.listAll();
//		List<Role> roleList =  roleService.listAll();
//
//		if (roleList.isEmpty() || roleList == null) {
//			Role roleAdmin = new Role();
//			roleAdmin.setId(1L);
//			roleAdmin.setName("ROLE_ADMIN");
//			roleService.save(roleAdmin);
//			Role roleUser = new Role();
//			roleUser.setId(2L);
//			roleUser.setName("ROLE_USER");
//			roleService.save(roleUser);
//		}

	}

}
