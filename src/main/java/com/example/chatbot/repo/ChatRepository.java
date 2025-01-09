package com.example.chatbot.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.chatbot.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
	@Query("""
			SELECT c FROM Chat c WHERE c.playerId = :playerId AND c.id = :id AND c.status = 'IN_PROGRESS'
					""")
	Chat getExistingChatInProgress(Long playerId, Long id);
	@Query("""
			SELECT c FROM Chat c WHERE c.playerId = :playerId AND c.id = :id 
					""")
	Chat getExistingChat(Long playerId, Long id);
	@Query("""
			SELECT c FROM Chat c WHERE c.playerId = :playerId ORDER BY c.updatedOn 
					""")
    Page<Chat>findAllByPlayerId(Long playerId, Pageable pageable);

}