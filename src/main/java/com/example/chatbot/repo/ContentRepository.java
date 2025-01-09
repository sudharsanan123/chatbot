package com.example.chatbot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.chatbot.dto.ContentResponse;
import com.example.chatbot.entity.Content;

public interface ContentRepository extends JpaRepository<Content, Long>{
   
	@Query(value = "SELECT * FROM content\r\n"
			+ "WHERE language_id = ?1", nativeQuery = true) 
	List<Content> findAllByLanguageId(Long languageId);

	@Query(value = "select c.id as id, c.name as name, l.name as language, c.updated_on as updatedOn, c.created_on as createdOn, c.updated_by as updatedBy, c.created_by as createdBy\r\n"
			+ "from content c inner join language l on c.language_id = l.id", nativeQuery = true) 
	List<ContentResponse> findAllContents();
}
