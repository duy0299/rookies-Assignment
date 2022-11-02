package com.rookies.assignment.data.entity;


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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private List<ProductModel> listModel;


//	@OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
//	private List<CategoriesProductModel> listCategoriesProductModels;
//	@ManyToMany
//	@JoinTable(
//			name = "categoriesProductModel",
//			joinColumns = @JoinColumn(name = "categoriesId"),
//			inverseJoinColumns = @JoinColumn(name = "productModelId"))
//	private List<ProductModel> listModel;
	
	
//	------------------------------------------------------
// parent of categories
	@Column(name="parentCategoriesId", nullable = true)
	private Integer parentCategoriesId;
	
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="description", nullable = false, length = 200)
	private String description;

	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	
	
}
