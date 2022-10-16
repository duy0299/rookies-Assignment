package com.rookies.assignment.data.entity;


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

//@data = @get + @set
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories_product_model")
public class CategoriesProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="categories_id")
	private Categories categories;
	
	@ManyToOne
	@JoinColumn(name="product_model_id")
	private ProductModel model;
	
	
//	-------------------------------------
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp timeCreate;

	@Column(name="time_update")
	private Timestamp timeUpdate;
	
}
