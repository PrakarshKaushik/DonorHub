package com.capstone.donorhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capstone.donorhub.entity.CustomUserDetail;
import com.capstone.donorhub.entity.User;
import com.capstone.donorhub.respository.UserRepository;


	@Service
	public class CustomUserDetailService implements UserDetailsService {

		@Autowired
		private UserRepository userRepository;
		
		@Override
		public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
			User user = this.userRepository.findByEmail(email);
			if(user==null) {
				throw new UsernameNotFoundException("No user found");
			}
			return new CustomUserDetail(user);
		}
}
