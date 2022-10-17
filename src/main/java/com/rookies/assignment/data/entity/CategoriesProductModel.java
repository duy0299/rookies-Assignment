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
@Table(name="categoriesProductModel")
public class CategoriesProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="categoriesId")
	private Categories categories;
	
	@ManyToOne
	@JoinColumn(name="productModelId")
	private ProductModel model;
	
	
//	-------------------------------------
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	
}
