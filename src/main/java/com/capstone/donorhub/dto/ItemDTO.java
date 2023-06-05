package com.capstone.donorhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

	private String itemName;
	private String category;
	private int quantity;
	private int userId;
	private String deliveryMode;

}
