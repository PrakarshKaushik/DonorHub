package com.capstone.donorhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.Orders;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.OrderRepository;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class NgoServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderServiceImpl orderServiceImpl;

	
	//---------------------------------------------
	public List<Items> getAllItems() {
		return itemRepository.findAll();
	}

	//---------------------------------------------
	
	public List<Orders> getAllOrders(int id) {

		return orderRepository.ngoOrders(id);
	}
//	public List<Orders> getAllOrders() {
//
//		return orderRepository.findAll();
//	}
	
	//----------------------------------------------

	public User register(User user) {

		return userRepository.save(user);
	}

	//--------------------------------------------------------------------------------
	
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
//		throw new RuntimeException("Item not found for id: " + id);

	}


//-----------------------------------------------------------------------------------------
	
	public String bookItem(int itemId, int quantity, int ngoId) {
		itemRepository.findById(itemId);
		Optional<Items> itemOptional = itemRepository.findById(itemId);
		Items bookedItem = null;
		Items orederedItem = null;
		
		String str= " ";

		if (itemOptional.isPresent()) {
			bookedItem = itemOptional.get();
			int oldQuantity = bookedItem.getQuantity();

			if(oldQuantity >= quantity)
			{
			bookedItem.setQuantity(quantity);
			orderServiceImpl.placeOrder(bookedItem, ngoId);
			bookedItem.setQuantity(oldQuantity - quantity);
			itemRepository.save(bookedItem);

			bookedItem.setQuantity(quantity);
			
			str ="Item booked and will be delivered on time";
			
			}
			else
			{
				
				str =" The available quantity is " + oldQuantity+ " Please order under available quantity or equal to the available quantity";
			}

		}
		else
		{
			str ="No such item is present";
		}
		return str ;

	}
	
	//------------------------------------------------------------------------------

	public List<Items> getItemByName(String name) {
		
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
	
	//-------------------------------------------------------------------------------
	public String cancelOrder(int orderId) {
		Optional<Orders> itemOptional = orderRepository.findById(orderId);
		
		if (itemOptional.isPresent()) {
			
		Orders dummyorder = itemOptional.get();
		Items item = dummyorder.getItem();
		int bookedQnt = dummyorder.getQuantity();
		System.out.println("bookedQnt ="+bookedQnt);
		int itemID = item.getItemId();
		
		Optional<Items> itemOptional2 = itemRepository.findById(itemID);
		Items itemMain = itemOptional2.get();
		int qnt = itemMain.getQuantity();
		int c = qnt + bookedQnt;
		itemMain.setQuantity(c);
		itemRepository.save(itemMain);

		
		orderRepository.deleteById(orderId);
		
		return "Deleted";
		}
		
		else
		{
			return "order not present";
		}

	}
	//------------------------------------------------------------------------------------

}
