package com.emlakcepte.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Order;
import com.emlakcepte.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;	
	
	public Order saveOrder(Order order) {
		return paymentRepository.save((order));
	}
	
	public Order getPackageById(Integer id) {
		Optional<Order> optional = paymentRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
	
	public List<Order> getPackageByUserId(Integer userId) {
		Optional<List<Order>> optional = paymentRepository.findByUserId(userId);
		return optional.isPresent() ? optional.get() : null;
	}
	
}
