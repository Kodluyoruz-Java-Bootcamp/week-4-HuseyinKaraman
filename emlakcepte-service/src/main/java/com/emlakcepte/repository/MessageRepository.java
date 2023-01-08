package com.emlakcepte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.emlakcepte.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
		
	@Modifying
	@Query("update Message m set m.title = ?1, m.content = ?2 where m.id = ?3")
	void setMessageById(String title, String content, Integer id);
}
