package com.example.chatbot.repo;
import com.example.chatbot.entity.Notification;
import com.example.chatbot.entity.Notification.NotificationSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Fetch notifications where the source is in the provided list of strings
    List<Notification> findBySourceIn(List<String> sources);
    
    // Fetch notifications by playerId only
    List<Notification> findByPlayerId(Long playerId);

    // Fetch notifications by source, playerId, and sentCount condition
    List<Notification> findAllBySourceIn(List<NotificationSource> sources);

    // Find notifications by sources and playerId
    List<Notification> findByPlayerIdAndSourceIn(Long playerId, List<NotificationSource> sources);


}
