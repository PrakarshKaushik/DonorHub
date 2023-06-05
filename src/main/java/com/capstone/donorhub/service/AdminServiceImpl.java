package com.capstone.donorhub.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.dto.ItemDTO;
import com.capstone.donorhub.dto.UserDTO;
import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class AdminServiceImpl {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	//---------------------------------------------
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	//---------------------------------------------
	public User saveUser(UserDTO userDTO) {
		
		User userEntity = new User();
		
		BeanUtils.copyProperties(userDTO, userEntity);
		userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
		return userRepository.save(userEntity);
	}
	

	
	public List<Items> getAllItem() {
		return itemRepository.findAll();
	}

	
	
	public User getSingleUser(int id) {
		
		User c=null;
		java.util.Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}
		else
		{
	
			throw new RuntimeException("User not found for id: " + id);
		}

	}
	
	//----------------------------------------------------------

	public String deleteUser(int id) {
		java.util.Optional<User> userOptional = userRepository.findById(id);
		
		if (userOptional.isPresent()) {
		userRepository.deleteById(id);
		return "user deleted";
		}
		else
		{
			return "no such user is present";
		}
		

	}
	
	//-------------------------------------------------------

	public String updateUser(int id, UserDTO userDTO) {
		Optional<User> oldUser = userRepository.findById(id);
		
		if (oldUser.isPresent()) {

			
			BeanUtils.copyProperties(userDTO, oldUser.get());
			oldUser.get().setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
			
			 userRepository.save(oldUser.get());
			 return "Item Updated";
		}
		else
		{
			return "Item not found";
		}
	}
			
			 
	


	//------------------------------------------------------
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
	
	//----------------------------------------------------------

	public User updatUser(User user) {
		return userRepository.save(user);
	}
	
	//----------------------------------------------------------

	public List<User> getUserByName(String name) {
		
		List<User> dummy = new ArrayList<User>();
		
		List<User> user = userRepository.findByName(name);
		
		if(user.isEmpty())
		{
			return dummy;
		}
		else
		{
			return userRepository.findByName(name);

		}

	}
	
	//-----------------------------------------------------------
	
	
	
	 public User updateUserAccountStatus(int userId, String accountStatus) {
	        Optional<User> optionalUser = userRepository.findById(userId);
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();
	            user.setAccount_status(accountStatus);
	            userRepository.save(user);
	            return user;
	        } else {
	            throw new RuntimeException("User not found with ID: " + userId);
	        }

}
	 //--------------------------------------------------------------------
}
