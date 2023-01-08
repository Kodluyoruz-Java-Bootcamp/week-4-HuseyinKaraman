package com.emlakcepte.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.loadbalancer.FeignLoadBalancerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.emlakcepte.client.model.Order;


@FeignClient(value = "emlakcepte-payment", url = "http://localhost:9090/payments")
public interface PaymentServiceClient {
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveOrder(@RequestBody Order order);
	
	@GetMapping(value = "/{userId}")
	public List<Order> getOrderById(@PathVariable Integer userId);

}
