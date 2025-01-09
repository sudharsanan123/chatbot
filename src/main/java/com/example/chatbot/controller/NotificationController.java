package com.example.chatbot.controller;

import com.example.chatbot.dto.NotificationRequest;
import com.example.chatbot.entity.Notification;
import com.example.chatbot.entity.Notification.NotificationSource;
import com.example.chatbot.service.NotificationService;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/add")
    public ResponseEntity<?> addNotification(@RequestBody NotificationRequest notificationRequest) {
        try {
            List<NotificationSource> sources = notificationRequest.getSource();
            List<Notification> savedNotifications = new ArrayList<>();
            for (NotificationSource source : sources) {
                Notification notification = new Notification();
                notification.setContent(notificationRequest.getContent());

                // Directly use the list of sources from the request
                notification.setSource(source); // Store the list of sources directly
                notification.setPlayerId(notificationRequest.getPlayerId());
                // Convert scheduleTime and expireTime to Instant
                Instant scheduleTimeInstant = notificationRequest.getScheduleTime().toInstant(ZoneOffset.UTC);
                Instant expireTimeInstant = notificationRequest.getExpireTime().toInstant(ZoneOffset.UTC);
                notification.setScheduleTime(scheduleTimeInstant);
                notification.setExpireTime(expireTimeInstant);
    
                // notification.setScheduleTime(notificationRequest.getScheduleTime());
                // notification.setExpireTime(notificationRequest.getExpireTime());
    
                // Set notification sentCount and static count
                notification.setSentCount(notificationRequest.getSentCount() != null 
                        ? notificationRequest.getSentCount() 
                        : 1); // Default to 1
                notification.setCount(notificationRequest.getCount());
    
                // Save the notification
                Notification savedNotification = notificationService.addNotification(notification);
    
                // Return success response
                savedNotifications.add(savedNotification);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNotifications);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request: " + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
            }
        }
            
            // Map the NotificationRequest to a Notification entity
          
    @GetMapping("/source")
    public ResponseEntity<?> getNotificationsBySources(
            @RequestParam List<NotificationSource> sources,
            @RequestParam(required = false) Long playerId) {
    
        List<Notification> notifications;
        if (playerId != null) {
            notifications = notificationService.getNotificationsBySourcesAndPlayerId(sources, playerId);
        } else {
            notifications = notificationService.getNotificationsBySources(sources);
        }
    
        Map<String, Object> response = new HashMap<>();
        response.put("notifications", notifications);
    
        return ResponseEntity.ok(response);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
        @PathVariable Long id,
        @RequestBody NotificationRequest notificationRequest) {
    Notification updatedNotification = notificationService.updateNotification(
            id,
            notificationRequest
    );

    return ResponseEntity.ok(updatedNotification);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
