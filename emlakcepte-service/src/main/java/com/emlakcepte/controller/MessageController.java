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

import com.emlakcepte.model.Message;
import com.emlakcepte.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/** @Note: kaydedilen mesajları verir */
	@GetMapping
	public ResponseEntity<List<Message>> getAll() {
		return new ResponseEntity<>(messageService.getAll(), HttpStatus.OK);
	}

	
	/** @Note: id'si girilen mesajı verir */
	@GetMapping(value = "/{messageId}")
	public ResponseEntity<Message> getById(@PathVariable Integer messageId) {
		return messageService.checkMessage(messageId)
				? new ResponseEntity<>(messageService.getById(messageId), HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	/** @Note: fromId'li User'dan toId'li User'a mesaj gönderilir. */
	@PostMapping(value = "/{sender}/{receiver}")
	public ResponseEntity<Message> sendMessage(@PathVariable Integer sender, @PathVariable Integer receiver,
			@RequestBody Message message) {
		return messageService.save(sender,receiver,message)
				? new ResponseEntity<>(message, HttpStatus.CREATED)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Note: girilen Id'ye ait mesaj -> mesaj listesinden ve istegi yapan kullanıcının mesaj listesinden silinir!
	 * @param userId
	 * @param messageId
	 * @return
	 */
	@PostMapping("/delete/{userId}/{messageId}")
	public ResponseEntity<Message> deleteMessage(@PathVariable Integer userId, @PathVariable Integer messageId) {
		return messageService.delete(userId, messageId) ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * @Note: girilen Id'ye ait mesaj -> Güncellenir!
	 * @param messageId
	 * @param message
	 * @return
	 */
	@PostMapping("/{messageId}")
	public ResponseEntity<Message> updateMessage(@PathVariable Integer messageId,@RequestBody Message message) {
		return  messageService.checkMessage(messageId) ? new ResponseEntity<>(messageService.update(message,messageId),HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
