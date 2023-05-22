package com.capstone.donorhub.repository;

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

import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

	//Name Found TestCase
	@Test
	void testFindByName() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setName("John");
		users.add(user);
		
		when(userRepository.findByName(Mockito.anyString())).thenReturn(users);
		
		List<User> result = userRepository.findByName("John");
		
		assertNotNull(result);
		
	}
	
	//Name Not Found TestCase
	@Test
	void testFindByName_NotAvailable() {
		Mockito.mock(User.class);
		List<User> users = new ArrayList<>();
		User user = new User();
		user.setName("John");
		users.add(user);
		
		when(userRepository.findByName(Mockito.anyString())).thenReturn(null);
		
		assertThrows(NullPointerException.class, () -> userRepository.findByName(Mockito.anyString()));
		
	}

	//Email Found TestCase
	@Test
	void testFindByEmail() {
		
		Mockito.mock(User.class);
	    User user = new User();
	    user.setEmail("john@example.com");
	    
	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
	   
	    User result = userRepository.findByEmail("john@example.com");
	    
	    assertEquals(user.getEmail(), result.getEmail());
	}
	
	//Email Not Found TestCase
	@Test
	void testFindByEmail_NotAvailable() {
		
		Mockito.mock(User.class);
	    User user = new User();
	    user.setEmail("john@example.com");
	    
	    when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
	  
	  assertThrows(NullPointerException.class, () -> userRepository.findByEmail("john@example.com") );
	}
	}



