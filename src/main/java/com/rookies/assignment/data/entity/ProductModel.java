package com.rookies.assignment.data.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
@AllArgsConstructor
@Entity
@Table(name="productModel")
public class ProductModel {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<CategoriesProductModel> listCategoriesProductModel;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<Product> listProduct;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<ModelImage> listImages;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<Rating> listRatings;
	
	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
	private List<Wishlist> listWishlists;
	
	@ManyToMany(mappedBy = "listModel")
	private List<Categories> listCategories;
	
	
//	------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="priceRoot", nullable = false, columnDefinition = "Decimal(10,2)")
	private BigDecimal priceRoot;
	
	@Column(name="description", nullable = false, length = 200)
	private String description;

	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	
	
	
	public ProductModel() {
		super();
	}
}
