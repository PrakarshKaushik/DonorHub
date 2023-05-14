package com.capstone.donorhub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.UserRepository;

@Service
public class AdminServiceImpl {

//    @Autowired
//    private UserRepository userRepository;
    
    @Autowired
    private ItemRepository itemRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

//<<<<<<< HEAD
//    public User saveUser(User userEntity) {
//
//        return userRepository.save(userEntity);
//    }
    
    public List<Items> getAllItem() {
        return itemRepository.findAll();
    }

//    public User getSingleUser(int id) {
//        java.util.Optional<User> userOptional = userRepository.findById(id);
//        if(userOptional.isPresent()) {
//            return userOptional.get();
//        }
//        throw new RuntimeException("User not found for id: "+id);
//=======
    
    
	public User getSingleUser(int id) {
		java.util.Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			return userOptional.get();
		}
		throw new RuntimeException("User not found for id: " + id);

	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);

	}

	public User updateUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

//	public List<User> getUserByName(String name) {
//
//<<<<<<< HEAD
//    public void deleteUser(int id) {
//        userRepository.deleteById(id);
//
//    }
    
    public void deleteItem(int id) {
        itemRepository.deleteById(id);

    }
 
    public User updatUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUserByName(String name) {

        return userRepository.findByName(name);
    }
//=======
//		return userRepository.findByName(name);
//	}
	
//>>>>>>> 1d3fb7c801784f69637756ff5d2549eeda092ed4
}
