package com.emlakcepte.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emlakcepte.client.PaymentServiceClient;
import com.emlakcepte.client.model.Order;
import com.emlakcepte.configuration.EmlakceptePaymentQueue;
import com.emlakcepte.dto.user.mapper.UserMapper;
import com.emlakcepte.dto.user.request.UserLogin;
import com.emlakcepte.dto.user.request.UserRequest;
import com.emlakcepte.model.User;
import com.emlakcepte.model.enums.Connectivity;
import com.emlakcepte.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentServiceClient paymentServiceClient;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private EmlakceptePaymentQueue paymentQueue;

	@Autowired
	private UserMapper userMapper;
	
	/** @Note:Oluşturulan user kayıt edilir. */
	public User createUser(UserRequest userRequest) {
		return userRepository.save(userMapper.convert(userRequest));
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	/** @Note: kayıtlı user'ları verir */
	public List<User> getAll() {
		return userRepository.findAll();
	}

	/** @Note: Girilen userId'e ait user'i doner  */
	public User getById(Integer userId) {
		Optional<User> byId = userRepository.findById(userId);
		return byId.isPresent() ? byId.get() : null;
	}
	
	/** @Note: Girilen userId'e ait user'i doner  */
	public void removeUser(Integer userId) {
		try {
			userRepository.deleteById(userId);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**  @Note: istenilen tür paket alınacak! */
	public boolean buyPackage(Integer userId,Integer quantity) {
		User user = getById(userId);
		// kullanıcı varmı ve giriş yapmışmı, veya paket sayısı 1 ve 10'dan farklımı  ??
		if (!checkUser(user) && !(quantity==1 || quantity==10)) return false;  
		Order order = new Order(user.getId(),user.getName(),user.getEmail(),quantity);
		HttpStatus statusCode = paymentServiceClient.saveOrder(order).getStatusCode();
		if (!HttpStatus.OK.equals(statusCode)) return false;
		rabbitTemplate.convertAndSend(paymentQueue.getQueueName(), order);	
		return true;
	}

	public HashMap<String, String> getProduct() {
		HashMap<String, String> packages = new HashMap<>();
		packages.put("parametre:1", "tekli paket 100$");
		packages.put("parametre:10", "10'lu paket 1000$");
		return packages;
	}
	
	public List<Order> getMyPackages(Integer userId) {
		User byId = getById(userId);
		return !checkUser(byId) ?  paymentServiceClient.getOrderById(userId) : null;
	}
	
	/** @Note: Kullanıcı giriş yapar */
	public User logIn(UserLogin login) {
		Optional<User> optional = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword());
		if(optional.isPresent()) {
			User user = optional.get();
			user.setConnectivity(Connectivity.LOGGEDIN);
			return userRepository.save(user);
		}
		return null;
	}
	
	/** @Note:  Kullanıcı çıkış yapar  */
	public User logOut(UserLogin login) {
		Optional<User> optional = userRepository.findByEmailAndPassword(login.getEmail(),login.getPassword());
		if(optional.isPresent()) {
			User user = optional.get();
			System.out.println(user);
			user.setConnectivity(Connectivity.LOGGEDOUT);
			return userRepository.save(user); 
		}
		return null;
	}
	
	/** @Note: User null kontrolü ve giriş yapmışmı kontrolu yapılır */
	public boolean checkUser(User user) {
		return Objects.nonNull(user) ? Connectivity.LOGGEDIN.equals(user.getConnectivity()) : false;
	}
	

	
}
