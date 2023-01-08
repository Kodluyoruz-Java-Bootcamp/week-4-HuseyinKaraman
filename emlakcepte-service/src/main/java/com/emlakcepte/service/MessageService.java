package com.emlakcepte.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emlakcepte.model.Message;
import com.emlakcepte.model.User;
import com.emlakcepte.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	private UserService userService;

	/**
	 * @Note: mesaj mesaj listesine ve gönderen ile alan user'in mesaj listesine kayıt edilir.
	 * @param fromId
	 * @param toId
	 * @param message
	 * @return 
	 * @return
	 */
	public boolean save(Integer sender, Integer receiver, Message message) {
		User senderUser = userService.getById(sender);
		User receiverUser = userService.getById(receiver);
		/** Kullanıcı kontrolü */
		if (!userService.checkUser(senderUser) && Objects.isNull(receiverUser)) {
			return  false;
		}
		message.setSender(senderUser);
		message.setReceiver(receiverUser);
		return Objects.nonNull(messageRepository.save(message));
	}

	/**
	 * @Note: butun mesajları verir.
	 * @return
	 */
	public List<Message> getAll() {
		return messageRepository.findAll();
	}

	/**
	 * @Note: Eger verilen id'e ait Message varsa yeni gelen message'in degerleri
	 *        ona atanır
	 * @param message
	 * @return
	 */
	public Message update(Message message, Integer id) {
		boolean existsById = messageRepository.existsById(id);
		
		if (!existsById) {
			return null;
		}
		
		Message byId = getById(id);
		byId.setContent(message.getContent());
		byId.setTitle(message.getTitle());
		return messageRepository.save(byId);	
	}
	

	/**
	 * @Note: Id'si girilen user'in mesaj listesinden mesaj silinir. 
	 * @param userId
	 * @param messageId
	 * @return 
	 * @return
	 */
	public Boolean delete(Integer userId, Integer messageId) {
		boolean checkMessage = checkMessage(messageId);
		boolean checkUser = Objects.isNull(userService.getById(userId));
		
		if (checkUser && !checkMessage) {
			return false;
		}
		Message message = getById(messageId);
		messageRepository.delete(message);
		return true;
	}

	/**
	 * @Note: id'si girilen mesaj bulunur
	 * @param id
	 * @return
	 */
	public Message getById(Integer id) {
		Optional<Message> byId = messageRepository.findById(id);
		return byId.isPresent() ? byId.get() : null;
	}

	/**
	 *  @Note: 'girilen Id'ye ait mesaj varmı kontrolu yapılır.
	 * @param message
	 * @return
	 */
	public boolean checkMessage(Integer messageId) {
		return messageRepository.existsById(messageId);
	}
}
