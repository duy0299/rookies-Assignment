package com.rookies.assignment.data.entity;


import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@ManyToMany(mappedBy = "listRole")
	private List<UserInfo> listUser;
	
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserRole> listUserRole;
	
	
//	-------------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;

	@Column(name="level", nullable = false)
	private short level;

	@Column(name="description", length = 200)
	private String description;

	@Column(name="status", nullable = false)
	private short status;
	
}
