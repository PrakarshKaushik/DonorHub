package com.capstone.donorhub.service;

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HomeServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private HomeServiceImpl homeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testRegisterUser() {

		Mockito.mock(User.class);
		User user = new User();
		user.setEmail("john@gl.com");
		user.setPassword("password");

		String encodedPassword = "encodedPassword";
		when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

		User savedUser = new User();
		savedUser.setEmail(user.getEmail());
		savedUser.setPassword(encodedPassword);
		when(userRepository.save(user)).thenReturn(savedUser);

		User registeredUser = homeService.registerUser(user);

		verify(userRepository).save(user);
		assertEquals(savedUser, registeredUser);
	}
}
