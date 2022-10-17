package com.rookies.assignment.data.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="size")
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
	private List<Product> listProduct;
	

	
//	------------------------------------------------------
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="status", nullable = false)
	private boolean status;

	@Column(name="time_create")
	private Timestamp timeCreate;

	@Column(name="time_update")
	private Timestamp timeUpdate;
	
}