package com.capstone.donorhub.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_id")
	private Items item;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ngo_id")
	private User user;

	public void setOrderId(int orderId) {
		this.orderId = orderId + 1000;
	}

	private int quantity;

	public void setItem(Items item) {
		this.item = item;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
//sdaw
