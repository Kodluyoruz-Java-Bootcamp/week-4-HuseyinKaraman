package com.emlakcepte.dto.user.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.emlakcepte.dto.user.request.UserRequest;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.Connectivity;
import com.emlakcepte.model.enums.UserType;

@Component
public class UserMapper {

	public UserMapper() {}
	
	public User convert(UserRequest userRequest ) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		user.setType(UserType.INDIVIDUAL);
		user.setCreateDate( LocalDateTime.now());
		user.setPackages(10);
		user.setConnectivity(Connectivity.LOGGEDIN);
		return user;
	}
	
}
