package com.example.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {

}
