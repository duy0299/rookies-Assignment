package com.rookies.assignment.data.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name="tborder")
public class Order {
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> listItems;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserInfo user;
	
//	----------------------------------------------------------

	@Column(name="address", nullable = false, length = 100)
	private String address;
	
	@Column(name="note", nullable = false, length = 100)
	private String note;

	@Column(name="status", nullable = false)
	private short status;

	@Column(name="timeCreate")
	private Timestamp timeCreate;

	@Column(name="timeUpdate")
	private Timestamp timeUpdate;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}


}
