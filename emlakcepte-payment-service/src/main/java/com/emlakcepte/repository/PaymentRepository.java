package com.emlakcepte.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlakcepte.model.Order;

public interface PaymentRepository extends JpaRepository<Order, Integer>{

	Optional<List<Order>> findByUserId(Integer userId);
	
}
