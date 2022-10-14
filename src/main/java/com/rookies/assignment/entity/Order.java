package com.rookies.assignment.entity;

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
	private String first_name;

	@Column(name="last_name", nullable = false, length = 20)
	private String last_name;

	@Column(name="phone_number", nullable = false, length = 15)
	private String phone_number;

	@Column(name="email", nullable = true, length = 50)
	private String email;

	@Column(name="address", nullable = false, length = 100)
	private String address;
	
	@Column(name="note", nullable = false, length = 100)
	private String note;

	@Column(name="status", nullable = false)
	private short status;

	@Column(name="time_create")
	private Timestamp time_create;

	@Column(name="time_update")
	private Timestamp time_update;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public Timestamp getTime_create() {
		return time_create;
	}

	public void setTime_create(Timestamp time_create) {
		this.time_create = time_create;
	}

	public Timestamp getTime_update() {
		return time_update;
	}

	public void setTime_update(Timestamp time_update) {
		this.time_update = time_update;
	}
	
	
}
