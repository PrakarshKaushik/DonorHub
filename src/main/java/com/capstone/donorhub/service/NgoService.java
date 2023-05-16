package com.capstone.donorhub.service;

import java.util.List;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;

public interface NgoService {

	List<Items> getAllItems();

	User register(User user);

	Items getSingleItem(int id);

	Items bookItem(int id, int quantity);

	List<Items> getItemByCategory(String Category);

}
