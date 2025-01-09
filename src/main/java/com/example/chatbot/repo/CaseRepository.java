package com.example.chatbot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.Case;

public interface CaseRepository extends JpaRepository<Case, Long> {

	Optional<Case> findById(Long id);
	
	Optional<Case> findByChatId(Long chatId);
}
