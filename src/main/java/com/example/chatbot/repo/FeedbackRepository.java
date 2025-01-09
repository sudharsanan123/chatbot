package com.example.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
