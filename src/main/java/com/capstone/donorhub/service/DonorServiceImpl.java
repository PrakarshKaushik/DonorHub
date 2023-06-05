package com.capstone.donorhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.dto.ItemDTO;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;

@Service
public class DonorServiceImpl {

	@Autowired
	private ItemRepository itemRepository;

	// All Items Listed by Donor
	public List<Items> donorItem(int id) {
		return itemRepository.donorItems(id);
	}

	// Add Item by Donor
	public Items saveItem(ItemDTO itemDTO) {

		Items itemEntity = new Items();

		BeanUtils.copyProperties(itemDTO, itemEntity);
		User user = new User();
		user.setUserId(itemDTO.getUserId());

		itemEntity.setUser(user);

		int c = itemEntity.getQuantity();
		itemEntity.setOfrQuantity(c);

		return itemRepository.save(itemEntity);
	}

	// Get Single Item
	public Items getSingleItem(int id) {

		Items c = null;
		java.util.Optional<Items> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent()) {
			return itemOptional.get();
		} else {
			return c;
		}

	}

	// Delete Item Added by Donor

	public String deleteItem(int id) {
		java.util.Optional<Items> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent()) {
			itemRepository.deleteById(id);
			return "item deleted";
		}

		else {
			return "No such item is present";
		}

	}

	// Update Item by Donor
	public String updateItem(int id, ItemDTO itemDTO, int userId) {
		Optional<Items> oldItem = itemRepository.findById(id);

		int userIdItem = oldItem.get().getUser().getUserId();

		if (oldItem.isPresent() && userId == userIdItem) {

			oldItem.get().setOfrQuantity(itemDTO.getQuantity());
			BeanUtils.copyProperties(itemDTO, oldItem.get());

			itemRepository.save(oldItem.get());
			return "Item updated";

		} else {
			return "Item not found or does not belog to the user ";
		}

	}

	// Find Item by Name

	public List<Items> getItemsByName(String name) {

		List<Items> dummy = new ArrayList<Items>();

		List<Items> item = itemRepository.findByItemName(name);

		if (item.isEmpty()) {
			return dummy;
		} else {
			return itemRepository.findByItemName(name);

		}

	}

}
