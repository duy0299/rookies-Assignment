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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
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
	@JoinColumn(name="sizeId")
	private Size size;

	@ManyToOne
	@JoinColumn(name="productModelId")
	private ProductModel model;
	
	

//	------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;

	@Column(name="avatar", length = 150)
	private String avatar;

	@Column(name="saleType", nullable = false, length = 15)
	private String saleType;

	@ColumnDefault("0")
	@Min(value = 0)
	@Column(name="priceSale", nullable = false, columnDefinition="Decimal(10,2)")
	private BigDecimal priceSale;

	@Min(value = 0)
	@Column(name="quantity", nullable = false)
	private int quantity;

	@ColumnDefault("0")
	@Column(name="soldProductQuantity", nullable = false)
	private int soldProductQuantity;
	
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	

	
	
	
	
}
