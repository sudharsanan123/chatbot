package com.example.chatbot.dto;
import java.util.ArrayList;
import java.util.List;

import com.example.chatbot.entity.ChatContent;

public class ChatResponse {
	private Long chatId;
	private Long caseId;
	private List<ChatContent> options = new ArrayList<>();
	
    
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	public Long getChatId() {
		return chatId;
	}
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	public List<ChatContent> getOptions() {
		return options;
	}
	public void setOptions(List<ChatContent> options) {
		this.options = options;
	}
	
	@Override
	public String toString() {
		return "ChatResponse [chatId=" + chatId + ", options=" + options + ", getChatId()=" + getChatId()
				+ ", getOptions()=" + getOptions() + "]";
	}
	
	
}
