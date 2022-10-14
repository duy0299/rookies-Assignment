package com.rookies.assignment.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<OrderItem> listItems;
	
	@ManyToOne
	@JoinColumn(name="size_id")
	private Size size;

	@ManyToOne
	@JoinColumn(name="product_model_id")
	private ProductModel model;
	
	

//	------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;

	@Column(name="avatar", length = 50)
	private String avatar;

	@Column(name="sale_type", nullable = false, length = 15)
	private String sale_type;

	@ColumnDefault("0")
	@Min(value = 0)
	@Column(name="price_sale", nullable = false, columnDefinition="Decimal(10,2)")
	private BigDecimal price_sale;

	@Min(value = 0)
	@Column(name="quantity", nullable = false)
	private int quantity;

	@ColumnDefault("0")
	@Column(name="sold_product_quantity", nullable = false)
	private int sold_product_quantity;
	
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp time_create;

	@Column(name="time_update")
	private Timestamp time_update;
	

	
	
	
	
}
