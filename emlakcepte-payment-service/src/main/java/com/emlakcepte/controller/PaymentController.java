package com.emlakcepte.controller;

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

import com.emlakcepte.model.Order;
import com.emlakcepte.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService; 
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveOrder(@RequestBody Order order) {
		paymentService.saveOrder(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(name = "/{userId}")
	public List<Order> getOrderById(@PathVariable Integer userId) {
		return paymentService.getPackageByUserId(userId);
	}
	
}
