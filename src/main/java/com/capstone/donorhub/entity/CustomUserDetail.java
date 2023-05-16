package com.capstone.donorhub.entity;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetail implements UserDetails {
	private User user;

	public CustomUserDetail(User user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();
		set.add(new SimpleGrantedAuthority(this.user.getRole()));
		return set;
	}

	public String getPassword() {
		return this.user.getPassword();
	}

	public String getUsername() {
		return this.user.getEmail();
	}

	public boolean isAccountNonExpired() {
		return this.user.getAccount_status().equals("active");
	}

	public boolean isAccountNonLocked() {
		return this.user.getAccount_status().equals("active");

	}

	public boolean isCredentialsNonExpired() {
		return true;

	}

	public boolean isEnabled() {
		return true;
	}
}
