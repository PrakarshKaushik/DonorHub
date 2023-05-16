package com.capstone.donorhub.exception;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		String errorMessage;

		if (authException instanceof LockedException) {
			errorMessage = "Your account is inactive.Wait for sometime or contact the administrator.";
		} else if (authException instanceof DisabledException) {
			errorMessage = "Your account is blocked. Please contact the administrator.";
		} else if (authException instanceof BadCredentialsException) {
			errorMessage = "Invalid username or password.";
		} else {
			errorMessage = "Authentication failed.";
		}

		response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		PrintWriter writer = response.getWriter();
		writer.println(errorMessage);
	}

	@Override
	public void afterPropertiesSet() {
		setRealmName("YourRealmName");
		super.afterPropertiesSet();
	}
}
