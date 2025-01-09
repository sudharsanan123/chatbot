package com.example.chatbot.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Message {
	private String content;
	private Long contentId;
	private String contentType;
	private Source source;
	private Instant timestamp;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Instant  getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant  timestamp) {
		this.timestamp = timestamp;
	}
	
	public enum Source {
		BOT, 
		USER, 
		PLAYER
	}

	@Override
	public String toString() {
		return "Message [contentId=" + contentId + ", contentType=" + contentType + ", source=" + source
				+ ", timestamp=" + timestamp + "]";
	}
	

}
