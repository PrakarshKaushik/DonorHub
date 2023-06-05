package com.capstone.donorhub.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

	@Test
	void testTokenValidation() {

		UserDetails userDetails = mock(UserDetails.class);
		when(userDetails.getUsername()).thenReturn("testuser");

		JwtService jwtService = new JwtService();

		String token = jwtService.generateToken("testuser");

		assertTrue(jwtService.validateToken(token, userDetails));
	}
}
