package com.rookies.assignment.data.entity;

import java.sql.Timestamp;
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
@Table(name="userInfo")
public class UserInfo {
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private List<UserRole> listUserRole;
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private List<Rating> listRatings;
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private List<Feedback> listFeedbacks;
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private List<Wishlist> listWishlists;
	
	@ManyToMany
	@JoinTable(
			name = "userRole",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId"))
	private List<Role> listRole;
	
	
	
//	---------------------------------------------------------
	@Column(name="firstName", nullable = false, length = 20)
	private String firstName;

	@Column(name="lastName", nullable = false, length = 20)
	private String lastName;

	@Column(name="phoneNumber", nullable = true, length = 15)
	private String phoneNumber;

	@Column(name="gender", nullable = false)
	private boolean gender;

	@Column(name="email", nullable = false, length = 50, unique=true)
	private String email;

	@Column(name="avatar", nullable = true, length = 35)
	private String avatar;

	@Column(name="password", nullable = false, length = 30)
	private String password;

	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	
	
}
