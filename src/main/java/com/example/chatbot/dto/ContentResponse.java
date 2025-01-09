package com.example.chatbot.dto;

import java.time.Instant;

public interface ContentResponse {
	Long getId();
	String getName();
	Instant getCreatedOn();
	Instant getUpdatedOn();
	String getUpdatedBy();
	String getCreatedBy();
	String getLanguage();
}
