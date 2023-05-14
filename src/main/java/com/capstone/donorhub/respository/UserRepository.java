package com.capstone.donorhub.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.donorhub.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByName(String name);

	User findByEmail(String email);
}
