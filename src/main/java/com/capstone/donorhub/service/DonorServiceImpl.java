package com.capstone.donorhub.service;

import java.util.ArrayList;
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
	
	//---------------------------------------------------------

	public Items getSingleItem(int id) {

		Items c= null;
		java.util.Optional<Items> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent()) {
			return itemOptional.get();
		}
		else
		{
			return c;
		}

	}
	
	//----------------------------------------------------

	public String deleteItem(int id) {
		java.util.Optional<Items> itemOptional = itemRepository.findById(id);
		if (itemOptional.isPresent()) {
		itemRepository.deleteById(id);
		return "item deleted";
		}
		
		else
		{
			return "No such item is present";
		}

	}
	

	//------------------------------------------------------
	public Items updateItem(Items item) {
		return itemRepository.save(item);
	}
	
	
	//--------------------------------------------------------

	public List<Items> getItemsByName(String name) {

		List<Items> dummy = new ArrayList<Items>();
		
		List<Items> item = itemRepository.findByItemName(name);
		
		if(item.isEmpty())
		{
			return dummy;
		}
		else
		{
			return itemRepository.findByItemName(name);

		}
		
	}
	//------------------------------------------------------------

}
