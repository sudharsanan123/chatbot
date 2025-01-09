package com.example.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.chatbot.dto.PlayerUserResponse;
import com.example.chatbot.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
   
	@Query(value = """
			SELECT u.id AS userId,
			   	u.first_name AS userFirstName,
			   	u.last_name AS userLastName,
			   	u.preferred_language AS userLanguage,
			   	u.platform AS userPlatform,
			   	u.title AS userTitle,
			   	t.name AS gameName
			FROM player p
			JOIN title t ON p.title = t.id
			JOIN users u ON p.preferred_language = ANY(u.preferred_language)
			AND p.platform = ANY(u.platform)
			AND p.title = ANY(u.title)
			WHERE p.id = :playerId limit 1
	        """, nativeQuery = true)
	        PlayerUserResponse fetchUserByLanguageAndPlatformAndTitle(@Param("playerId") Long playerId);
}
