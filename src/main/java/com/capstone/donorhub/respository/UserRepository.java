package com.capstone.donorhub.respository;

import com.capstone.donorhub.entity.User;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
<<<<<<< HEAD
	List<User> findByName(String name);
=======
	List<User> findByName(String displayName);

	User findByEmail(String email);
>>>>>>> 572be39fe4e3a85649a90dec4babb67e934808a2
}
