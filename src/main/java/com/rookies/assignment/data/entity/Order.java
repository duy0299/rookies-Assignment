package com.rookies.assignment.data.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name="order")
public class Order {
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> listItems;
	
//	----------------------------------------------------------
	@Column(name="first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name="last_name", nullable = false, length = 20)
	private String lastName;

	@Column(name="phone_number", nullable = false, length = 15)
	private String phoneNumber;

	@Column(name="email", nullable = true, length = 50)
	private String email;

	@Column(name="address", nullable = false, length = 100)
	private String address;
	
	@Column(name="note", nullable = false, length = 100)
	private String note;

	@Column(name="status", nullable = false)
	private short status;

	@Column(name="time_create")
	private Timestamp timeCreate;

	@Column(name="time_update")
	private Timestamp timeUpdate;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}


}
