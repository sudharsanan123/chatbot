package com.example.chatbot.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.example.chatbot.model.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat")
@JsonInclude(Include.NON_NULL)
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long playerId;
	
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb") 
    private List<Message> messages;
	
    @Column(columnDefinition = "text")
	private String description;
    
	@Enumerated(EnumType.STRING)
	private ChatStatus status;
	
	private Instant createdOn;
	private Instant updatedOn;
	
	public Chat(Long playerId) {
	    this.playerId = playerId;
	    this.status = ChatStatus.IN_PROGRESS;
	    this.messages = new ArrayList<>();
	    this.createdOn = Instant.now();
	}

	public enum ChatStatus {
		IN_PROGRESS, CASE_CREATED, COMPLETE
	}
	
	public Chat() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ChatStatus getStatus() {
		return status;
	}

	public void setStatus(ChatStatus status) {
		this.status = status;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public Instant getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Instant updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", playerId=" + playerId + ", messages=" + messages + ", description=" + description
				+ ", status=" + status + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

    
	
}
