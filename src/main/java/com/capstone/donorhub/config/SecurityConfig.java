package com.capstone.donorhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.capstone.donorhub.exception.CustomBasicAuthenticationEntryPoint;
import com.capstone.donorhub.service.CustomUserDetailService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	CustomUserDetailService customUserDetailService;

	@Autowired
	private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers("/register", "/guest/**").permitAll()
				.requestMatchers("/admin/**").hasRole("admin").requestMatchers("/donor/**").hasRole("donor")
				.requestMatchers("/ngo/**").hasRole("ngo").anyRequest().authenticated().and().httpBasic()
				.authenticationEntryPoint(customBasicAuthenticationEntryPoint);

		return httpSecurity.build();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(bCryptPasswordEncoder());
	}

}
