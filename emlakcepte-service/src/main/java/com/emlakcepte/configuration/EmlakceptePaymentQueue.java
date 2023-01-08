package com.emlakcepte.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmlakceptePaymentQueue {
	private String queueName = "emlakcepte.notification.order";

	private String exchange = "emlakcepte.notification.order";;

	@Bean
	public Queue orderQueue() {
		return new Queue(queueName, false);
	}

	@Bean
	public DirectExchange orderExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	public Binding binding(Queue queueOrder, DirectExchange orderExchange) {
		return BindingBuilder.bind(queueOrder).to(orderExchange).with("");
	}

	@Bean
	 public MessageConverter jsonMessageConverter() {
	 return new Jackson2JsonMessageConverter();
	 }
	
	public String getQueueName() {
		return queueName;
	}

	public String getExchange() {
		return exchange;
	}

}
