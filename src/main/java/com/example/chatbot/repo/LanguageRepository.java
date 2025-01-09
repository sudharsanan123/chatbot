package com.example.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatbot.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
