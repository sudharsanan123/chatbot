package com.example.chatbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatbot.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    
}
