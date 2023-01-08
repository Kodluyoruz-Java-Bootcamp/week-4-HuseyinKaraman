package com.emlakcepte.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emlakcepte.service.UserService;
import com.emlakcepte.client.model.Order;
import com.emlakcepte.dto.user.request.UserLogin;
import com.emlakcepte.dto.user.request.UserRequest;
import com.emlakcepte.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	/** @Note: Kayıtlı kullanıcıları verecektir */
	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}

	/** @Note: yeni kullanıcı oluşturur */
	@PostMapping
	public ResponseEntity<User> create(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
	}

	/** @Note: Id'si girilen kullanıcıyı verir */
	@GetMapping(value = "/{userId}")
	public User findUserById(@PathVariable Integer userId) {
		return userService.getById(userId);
	}

	/** @Note: Id'si girilen kullanıcıyı database'den siler */
	@PostMapping(value = "/{userId}")
	public void deleteUserById(@PathVariable Integer userId) {
		userService.removeUser(userId);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<User> logIn(@RequestBody UserLogin login) {
		User user = userService.logIn(login);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(value = "/logout")
	public ResponseEntity<User> logOut(@RequestBody UserLogin logout) {
		User user = userService.logOut(logout);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/** @Note: Satılan productları verir */
	@GetMapping(value = "/products")
	public HashMap<String, String> getProducts() {
		return userService.getProduct();
	}

	/** @Note:Girilen userId'e göre user'in aldıgı order'ları geri döner */
	@PostMapping(value = "/products/{userId}")
	public ResponseEntity<List<Order>> getMyPackage(Integer userId) {
		return new ResponseEntity<>(userService.getMyPackages(userId),HttpStatus.OK);
	}
	
	/** @Note: userId/1 veya userId/10  yazılarak user 1 veya 10 tane paket satın alabilir */
	@PostMapping(value = "/{userId}/{quantity}")
	public ResponseEntity<HttpStatus> buyPackages(@PathVariable Integer userId, @PathVariable Integer quantity) {
		return userService.buyPackage(userId, quantity) ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
