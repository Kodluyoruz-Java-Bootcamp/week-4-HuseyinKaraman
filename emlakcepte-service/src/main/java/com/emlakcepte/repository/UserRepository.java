package com.emlakcepte.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlakcepte.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);   
	
	Optional<User> findByEmailAndPassword(String email,String password);

}
