package com.example.chatbot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.chatbot.entity.FeedbackContent;

public interface FeedbackContentRepository extends JpaRepository<FeedbackContent, Long>{

	@Query(value = """
			SELECT u.id, u.content, u.content_type, u.model_id, u.parent_id, u.score FROM feedback_content u
			WHERE ?1 = ANY(u.parent_id)
			""", nativeQuery = true)
	List<FeedbackContent> findAnswerByParentId(@Param("parentId") Long parentId);

	@Query(value = """
			SELECT u.id, u.content, u.content_type, u.model_id, u.parent_id, u.score FROM feedback_content u
			 WHERE u.content_type = :contentType
			""", nativeQuery = true)
	List<FeedbackContent> findByContentType(String contentType);
}
