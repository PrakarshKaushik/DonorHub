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

	public List<Items> donorItem(int id) {
		return itemRepository.donorItems(id);
	}
	
	//---------------------------------------------------

	
	public Items saveItem(ItemDTO itemDTO) {
		
		Items itemEntity = new Items();
		
		BeanUtils.copyProperties(itemDTO, itemEntity);
		User user = new User();
		user.setUserId(itemDTO.getUserId());
		
		itemEntity.setUser(user);
		
		int c=itemEntity.getQuantity();
		itemEntity.setOfrQuantity(c);
//		itemRepository.save(itemEntity);
		

		return itemRepository.save(itemEntity);
	} 
	
//	public Items saveItem(Items itemEntity) {
//		
//		int c=itemEntity.getQuantity();
//		itemEntity.setOfrQuantity(c);
////		itemRepository.save(itemEntity);
//		
//
//		return itemRepository.save(itemEntity);
//	}
	
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
	public String updateItem(int id, ItemDTO itemDTO, int userId) {
		Optional<Items> oldItem = itemRepository.findById(id);
		
		int userIdItem= oldItem.get().getUser().getUserId();
		
		if (oldItem.isPresent() && userId==userIdItem) {
			
			oldItem.get().setOfrQuantity(itemDTO.getQuantity());
			BeanUtils.copyProperties(itemDTO, oldItem.get());
//			oldItem.get().setQuantity(item.getQuantity());
//			oldItem.get().setCategory(item.getCategory());
//			oldItem.get().setItemName(item.getItemName());
//			oldItem.get().setDeliveryMode(item.getDeliveryMode());
			
			
			
			 itemRepository.save(oldItem.get());
			 return "Item updated";
			 


//		return itemRepository.save(item);
	}
		else
		{
			return "Item not found or does not belog to the user ";
		}
		
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
