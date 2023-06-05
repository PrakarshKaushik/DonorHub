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
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstone.donorhub.controller.AdminController;
import com.capstone.donorhub.dto.UserDTO;
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
	    public void testSaveUser() {
	        
	        UserDTO userDTO = new UserDTO();
	        userDTO.setEmail("testuser");
	        userDTO.setPassword("testpassword");

	        User savedUser = new User();
	        savedUser.setUserId(1);
	        savedUser.setEmail("testuser");
	        savedUser.setPassword("encodedpassword");

	        UserRepository userRepository = mock(UserRepository.class);
	        when(userRepository.save(any(User.class))).thenReturn(savedUser);

	        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	        when(passwordEncoder.encode("testpassword")).thenReturn("encodedpassword");

	        AdminServiceImpl adminServiceImpl = mock(AdminServiceImpl.class);
	        when(adminServiceImpl.saveUser(any(UserDTO.class))).thenReturn(savedUser);

	        AdminController controller = new AdminController();
	        setField(controller, "userRepository", userRepository);
	        setField(controller, "passwordEncoder", passwordEncoder);
	        setField(controller, "adminServiceImpl", adminServiceImpl);

	        // Act
	        User result = controller.saveUser(userDTO);

	        // Assert
	        assertEquals(savedUser.getUserId(), result.getUserId());
	        assertEquals(savedUser.getEmail(), result.getEmail());
	        assertEquals(savedUser.getPassword(), result.getPassword());
	        verify(userRepository, times(1)).save(any(User.class));
	        verify(passwordEncoder, times(1)).encode("testpassword");
	        verify(adminServiceImpl, times(1)).saveUser(any(UserDTO.class));
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
	        
	        
	        User user = new User();
	        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
	        
	   
	        String result = adminService.deleteUser(userId);
	        
	        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
	        
	    
	        assertEquals("user deleted", result, "The deleteUser method should return 'user deleted'.");
	    }
	 
	// DeleteUser - if not present   
	    @Test
	    void testDeleteUser_UserNotPresent() {
	        int userId = 4;
	        
	        
	        when(userRepository.findById(userId)).thenReturn(Optional.empty());
	        
	    
	        String result = adminService.deleteUser(userId);
	    
	        Mockito.verify(userRepository, Mockito.never()).deleteById(anyInt());
	        
	        assertEquals("no such user is present", result, "The deleteUser method should return 'no such user is present'.");
	    }
	




	// Test case for updateUser(User user)
	    @Test
	    public void testUpdateUser() {
	        int id = 1;

	        UserDTO userDTO = new UserDTO();
	        userDTO.setEmail("updateduser");
	        userDTO.setPassword("updatedpassword");

	        User oldUser = new User();
	        oldUser.setUserId(id);
	        oldUser.setEmail("olduser");
	        oldUser.setPassword("oldpassword");

	        Optional<User> optionalOldUser = Optional.of(oldUser);

	        UserRepository userRepository = mock(UserRepository.class);
	        when(userRepository.findById(id)).thenReturn(optionalOldUser);
	        when(userRepository.save(oldUser)).thenReturn(oldUser);

	        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	        when(passwordEncoder.encode("updatedpassword")).thenReturn("encodedpassword");

	        AdminController controller = new AdminController();
	        setField(controller, "userRepository", userRepository);
	        setField(controller, "passwordEncoder", passwordEncoder);

	        String result = controller.updateUser(id, userDTO);

	        assertEquals("Item Updated", result);
	        assertEquals("updateduser", oldUser.getEmail());
	        assertEquals("encodedpassword", oldUser.getPassword());
	        verify(userRepository, times(1)).findById(id);
	        verify(passwordEncoder, times(1)).encode("updatedpassword");
	        verify(userRepository, times(1)).save(oldUser);
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
	
	 private void setField(Object targetObject, String fieldName, Object fieldValue) {
	        try {
	            Field field = targetObject.getClass().getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(targetObject, fieldValue);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
