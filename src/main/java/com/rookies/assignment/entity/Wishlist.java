package com.rookies.assignment.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="wishlist")
public class Wishlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="product_model_id")
	private ProductModel model;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
	
	
//	----------------------------------------
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp time_create;

	@Column(name="time_update")
	private Timestamp time_update;
}
