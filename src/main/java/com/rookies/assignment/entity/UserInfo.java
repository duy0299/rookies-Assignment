package com.rookies.assignment.entity;

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
@Table(name="user_info")
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
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> listRole;
	
	
	
//	---------------------------------------------------------
	@Column(name="first_name", nullable = false, length = 20)
	private String first_name;

	@Column(name="last_name", nullable = false, length = 20)
	private String last_name;

	@Column(name="phone_number", nullable = true, length = 15)
	private String phone_number;

	@Column(name="gender", nullable = false, length = 10)
	private String gender;

	@Column(name="email", nullable = false, length = 50)
	private String email;

	@Column(name="avatar", nullable = true, length = 35)
	private String avatar;

	@Column(name="password", nullable = false, length = 30)
	private String password;

	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp time_create;

	@Column(name="time_update")
	private Timestamp time_update;
	
	
}
