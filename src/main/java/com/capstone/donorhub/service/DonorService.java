package com.capstone.donorhub.service;

import java.util.List;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;

public interface DonorService {

	List<Items> getAllItems();
    Items saveItem(Items item);
    Items getSingleItem(int id);
    void deleteItem(int id);
    Items updateItem (Items items);

    List<Items> getItemByCategory(String Category);
}
