package com.rookies.assignment.data.entity;


import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToMany(mappedBy = "listRole", fetch = FetchType.EAGER)
	private List<UserInfo> listUser;
	

	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UserRole> listUserRole;
	
	
//	-------------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;

	@Column(name="description", length = 200)
	private String description;

	@Column(name="status", nullable = false)
	private short status;
	
}
