package com.capstone.donorhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.capstone.donorhub.controller.AdminController;
import com.capstone.donorhub.dto.UserDTO;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.ItemRepository;
import com.capstone.donorhub.respository.UserRepository;

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

	// Test - Test Get All Users
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

	/*
	 * // Test - AddUser
	 * 
	 * @Test public void testSaveUser() {
	 * 
	 * User savedUser = new User(); savedUser.setUserId(1);
	 * savedUser.setEmail("testuser"); savedUser.setPassword("encodedpassword");
	 * 
	 * UserRepository userRepository = mock(UserRepository.class);
	 * when(userRepository.save(any(User.class))).thenReturn(savedUser);
	 * 
	 * PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
	 * when(passwordEncoder.encode("testpassword")).thenReturn("encodedpassword");
	 * 
	 * AdminController controller = new AdminController();
	 * 
	 * UserDTO userDTO = new UserDTO(); userDTO.setEmail("testuser");
	 * userDTO.setPassword("testpassword");
	 * 
	 * User result = controller.saveUser(userDTO);
	 * 
	 * assertEquals(savedUser.getUserId(), result.getUserId());
	 * assertEquals(savedUser.getEmail(), result.getEmail());
	 * assertEquals(savedUser.getPassword(), result.getPassword());
	 * verify(userRepository, times(1)).save(any(User.class));
	 * verify(passwordEncoder, times(1)).encode("testpassword"); }
	 */
//Test Getting Single User - where user is present
	@Test
	void testGetSingleUser_UserExists() {

		Mockito.mock(User.class);
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

	// Test - DeleteUser - if present
	@Test
	void testDeleteUser_UserPresent() {
		int userId = 4;

		User user = new User();
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		String result = adminService.deleteUser(userId);

		Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);

		assertEquals("user deleted", result, "The deleteUser method should return 'user deleted'.");
	}

	// Test - DeleteUser - if not present
	@Test
	void testDeleteUser_UserNotPresent() {
		int userId = 4;

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		String result = adminService.deleteUser(userId);

		Mockito.verify(userRepository, Mockito.never()).deleteById(anyInt());

		assertEquals("no such user is present", result,
				"The deleteUser method should return 'no such user is present'.");
	}

	/*
	 * //Test - Update User - if exists
	 * 
	 * @Test public void testUpdateUser_WhenUserExists() { int userId = 1; UserDTO
	 * userDTO = new UserDTO(); userDTO.setAddress("New Address");
	 * userDTO.setPassword("newPassword");
	 * 
	 * User existingUser = new User(); existingUser.setUserId(userId);
	 * existingUser.setAddress("Old Address");
	 * existingUser.setPassword("oldPassword");
	 * 
	 * when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
	 * when(passwordEncoder.encode(userDTO.getPassword())).thenReturn(
	 * "encodedPassword");
	 * when(userRepository.save(existingUser)).thenReturn(existingUser);
	 * 
	 * String result = adminService.updateUser(userId, userDTO);
	 * 
	 * assertEquals("User Updated", result); assertEquals(userDTO.getAddress(),
	 * existingUser.getAddress()); assertEquals("encodedPassword",
	 * existingUser.getPassword()); }
	 */

//test - Update user - if does not exists
	@Test
	public void testUpdateUser_WhenUserDoesNotExist() {
		int userId = 1;
		UserDTO userDTO = new UserDTO();
		userDTO.setName("John Doe");
		userDTO.setEmail("john@example.com");
		userDTO.setPassword("password123");

		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		String result = adminService.updateUser(userId, userDTO);

		verify(userRepository, times(1)).findById(userId);
		verify(userRepository, never()).save(ArgumentMatchers.any(User.class));

		assertEquals("User not found", result);
	}

	// Test - DeleteItem
	@Test
	void testDeleteItem() {
		int itemId = 1;
		adminService.deleteItem(itemId);
	}

	// Test - GetUserByName
	@Test
	void testGetUserByName() {
		String name = "John";

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

	// Test - UpdateAccountStatus - user exists
	@Test
	void testUpdateUserAccountStatus_UserExists() {
		int userId = 1;
		String accountStatus = "active";

		Mockito.mock(User.class);
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

	// Test - UpdateAccountStatus - user does not exists

	@Test
	void testUpdateUserAccountStatus_UserNotExists() {
		int userId = 1;
		String accountStatus = "active";

		when(userRepository.findById(userId)).thenReturn(null);

		assertThrows(NullPointerException.class, () -> adminService.updateUserAccountStatus(userId, accountStatus));
	}
}
