package com.pk.ecommerce.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.ecommerce.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);
}
