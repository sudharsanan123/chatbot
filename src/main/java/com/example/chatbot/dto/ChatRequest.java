package com.example.chatbot.dto;

import java.util.List;

import com.example.chatbot.model.Message;

public class ChatRequest {
	private Long playerId;
	private Long modelId;
	private Long chatId;
	private List<Message> messages;
	
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public Long getChatId() {
		return chatId;
	}
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	@Override
	public String toString() {
		return "ChatRequest [playerId=" + playerId + ", modelId=" + modelId + ", messages="
				+ messages + ", chatId=" + chatId + "]";
	}
}
