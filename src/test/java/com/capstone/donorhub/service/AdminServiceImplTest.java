package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstone.donorhub.entity.Items;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.UserRepository;
import com.capstone.donorhub.service.AdminServiceImpl;


import com.capstone.donorhub.service.AdminServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
	@Mock
	private ItemRepository itemRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private AdminServiceImpl adminService;


	// Test Get All Users
	@Test
	void testGetAllUsers() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setUserId(1);
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");
		user.setRole("admin");
		user.setAddress("Delhi");

		users.add(user);

		when(userRepository.findAll()).thenReturn(users);

		List<User> result = adminService.getAllUsers();

		assertEquals(users, result);
	}

	// Test Saving User
	@Test
	void testSaveUser() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setUserId(1);
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");
		user.setRole("admin");
		user.setAddress("Delhi");

		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = adminService.saveUser(user);

		assertEquals(user, result);
		verify(passwordEncoder).encode("123");
	}

//Test Getting Single User - where user is present
	@Test
	void testGetSingleUser_UserExists() {

		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		int userId = 1;
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");
		user.setRole("admin");
		user.setAddress("Delhi");

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		User result = adminService.getSingleUser(userId);

		assertEquals(user, result);
	}

	// Test Getting Single User - where user is not present
	@Test
	void testGetSingleUser_UserNotExists() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		int userId = 1;
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");
		user.setRole("admin");
		user.setAddress("Delhi");

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> adminService.getSingleUser(userId));
	}

	// DeleteUser - if present
	 @Test
	    void testDeleteUser_UserPresent() {
	        int userId = 4;
	        
	        // Mocking the behavior of userRepository.findById
	        User user = new User();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        
	        // Perform the deleteUser operation
	        String result = adminService.deleteUser(userId);
	        
	        // Verify that deleteById method is called once with the correct userId
	        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
	        
	        // Add assertions to verify the return value
	        assertEquals("user deleted", result, "The deleteUser method should return 'user deleted'.");
	    }
	 
	// DeleteUser - if not present   
	    @Test
	    void testDeleteUser_UserNotPresent() {
	        int userId = 4;
	        
	        // Mocking the behavior of userRepository.findById
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());
	        
	        // Perform the deleteUser operation
	        String result = adminService.deleteUser(userId);
	        
	        // Verify that deleteById method is not called
	        Mockito.verify(userRepository, Mockito.never()).deleteById(anyInt());
	        
	        // Add assertions to verify the return value
	        assertEquals("no such user is present", result, "The deleteUser method should return 'no such user is present'.");
	    }
	




	// Test case for updateUser(User user)
	@Test
	void testUpdateUser() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");

		when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

		when(userRepository.save(any(User.class))).thenReturn(user);

		User result = adminService.updateUser(user);

		assertEquals(user, result);
		verify(passwordEncoder).encode("123");
	}

	// DeleteItem
	@Test
	void testDeleteItem() {
		int itemId = 1;
		adminService.deleteItem(itemId);
	}

	// GetUserByName
	@Test
	void testGetUserByName() {
		String name = "John";

		List<User> users = new ArrayList<>();
		Mockito.mock(User.class);
		List<User> users1 = new ArrayList<>();
		User user = new User();
		user.setName("John");
		User user2 = new User();
		user2.setName("May");
		User user3 = new User();
		user3.setName("John");

		when(userRepository.findByName(name)).thenReturn(users1);

		List<User> result = adminService.getUserByName(name);

		assertEquals(users1, result);
	}

	// UpdateAccountStatus - user exists
	@Test
	void testUpdateUserAccountStatus_UserExists() {
		int userId = 1;
		String accountStatus = "active";

		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setEmail("j@gl.com");
		user.setPassword("123");
		user.setName("John");

		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);

		User result = adminService.updateUserAccountStatus(userId, accountStatus);

		assertEquals(accountStatus, result.getAccount_status());
		verify(userRepository).save(user);
	}

	// UpdateAccountStatus - user does not exists

	@Test
	void testUpdateUserAccountStatus_UserNotExists() {
		int userId = 1;
		String accountStatus = "active";

		when(userRepository.findById(userId)).thenReturn(null);
		

		assertThrows(NullPointerException.class, () -> adminService.updateUserAccountStatus(userId, accountStatus));
	}
}
