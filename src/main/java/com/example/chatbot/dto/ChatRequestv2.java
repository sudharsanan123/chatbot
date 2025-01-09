package com.example.chatbot.dto;

import com.example.chatbot.model.Message;

public class ChatRequestv2 {
	private Long playerId;
	private Long chatId;
	private Message message;
	
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Long getChatId() {
		return chatId;
	}
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ChatRequestv2 [playerId=" + playerId + ", chatId=" + chatId + ", message=" + message + "]";
	}
	
}
