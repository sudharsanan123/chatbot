package com.example.chatbot.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
		
	Optional<ChatMessage> findById(Long id);

	List<ChatMessage> findByChatId(Long chatId);

}
