package com.rookies.assignment.entity;


import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Categories {
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private List<CategoriesProductModel> listCategoriesProductModels;
	
	@ManyToMany
	@JoinTable(
			name = "categories_product_model",
			joinColumns = @JoinColumn(name = "categories_id"),
			inverseJoinColumns = @JoinColumn(name = "product_model_id"))
	private List<ProductModel> listModel;
	
	
//	------------------------------------------------------
	@Column(name="parent_categories_id", nullable = true)
	private String parent_categories_id;
	
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="description", nullable = false, length = 200)
	private String description;

	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp time_create;

	@Column(name="time_update")
	private Timestamp time_update;
	
	
}
