package com.capstone.donorhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.respository.ItemRepository;

@Service
public class DonorServiceImpl {
	
	@Autowired
    private ItemRepository itemRepository;


    public List<Items> getAllItem() {
        return itemRepository.findAll();
    }

    public Items saveItem(Items itemEntity) {

        return itemRepository.save(itemEntity);
    }

    public Items getSingleItem(int id) {
        java.util.Optional<Items> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new RuntimeException("User not found for id: "+id);

    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);

    }
 
    public Items updateItem(Items item) {
        return itemRepository.save(item);
    }

    public List<Items> getItemsByName(String name) {

        return itemRepository.findByItemName(name);
    }

}
