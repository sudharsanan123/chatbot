package com.example.chatbot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chatbot.entity.ChatContent;

public interface ChatContentRepository extends JpaRepository<ChatContent, Long> {
	
	@Query(value = "WITH Q as(\r\n"
			+ "			SELECT *\r\n"
			+ "			FROM chat_content\r\n"
			+ "			WHERE parent_id IS NULL AND model_id = ?1)\r\n"
			+ "			SELECT A.id, A.content, A.content_type, A.parent_id, A.model_id\r\n"
			+ "			FROM chat_content as A, Q\r\n"
			+ "			WHERE A.parent_id = Q.id AND A.model_id = ?1\r\n"
			+ "			UNION\r\n"
			+ "			SELECT Q.id, Q.content, Q.content_type, Q.parent_id, Q.model_id\r\n"
			+ "			FROM  Q\r\n"
			+ "			ORDER BY id", nativeQuery = true) 
	List<ChatContent> firstSetOfContent(Long modelId);
	
	@Query(value = "WITH Q as(\r\n"
			+ "			SELECT *\r\n"
			+ "			FROM chat_content\r\n"
			+ "			WHERE parent_id = ?1 AND model_id = ?2)\r\n"
			+ "			SELECT A.id, A.content, A.content_type, A.parent_id, A.model_id\r\n"
			+ "			FROM chat_content as A, Q\r\n"
			+ "			WHERE A.parent_id = Q.id AND A.model_id = ?2\r\n"
			+ "			UNION\r\n"
			+ "			SELECT Q.id, Q.content, Q.content_type, Q.parent_id, Q.model_id\r\n"
			+ "			FROM  Q\r\n"
			+ "			ORDER BY id", nativeQuery = true) 
	List<ChatContent> nextSetOfContent(Long id, Long modelId);
	

	@Query(value = "SELECT * \r\n"
			+ "FROM chat_content\r\n"
			+ "WHERE model_id = ?1\r\n"
			+ "ORDER BY id", nativeQuery = true)
	List<ChatContent> findAllByModelId(Long srcModelId);
	
	@Query(value = "SELECT *\r\n"
			+ "FROM chat_content\r\n"
			+ "WHERE parent_id = ?1 AND content_type = 'Question'\r\n"
			+ "AND model_id = ?2", nativeQuery = true)
	List<ChatContent> checkIfQuestionExistsForAnswer(Long contentId, Long modelId);
	
}
