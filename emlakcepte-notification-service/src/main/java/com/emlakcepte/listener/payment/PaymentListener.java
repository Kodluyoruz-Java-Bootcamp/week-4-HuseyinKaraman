package com.emlakcepte.listener.payment;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.emlakcepte.model.Order;

@Component
public class PaymentListener {
	
	
	@RabbitListener(queues = "emlakcepte.notification.order")
	public void realtyListener(Order order) {
		System.out.println("order: "+order);
		
	}
	
}
