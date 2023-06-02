package com.capstone.donorhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor

@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer"})

@Entity
@Table(name = "Items")
public @Data class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int itemId;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	@NotNull
	@Column(name = "item_name")
	private String itemName;

	@NotNull
	@Column(name = "category")
	private String category;
	
	@NotNull
	@Column(name = "Offered_quantity")
	private int ofrQuantity;

	@NotNull
	@Column(name = "remaining_quantity")
	private int quantity;

	@NotNull
	@Column(name = "delivery_mode")
	private String deliveryMode;

}
