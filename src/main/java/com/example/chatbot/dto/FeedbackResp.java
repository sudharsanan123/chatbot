package com.example.chatbot.dto;

import java.util.List;

public class FeedbackResp {

	private Long id;
	private String content;
	private String contentType;
	private Long modelId;
	private List<Answer> answers;

	public static class Answer {

		private Long id;
		private String content;
		private Long score;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Long getScore() {
			return score;
		}

		public void setScore(Long score) {
			this.score = score;
		}

		@Override
		public String toString() {
			return "Answer [id=" + id + ", content=" + content + ", score=" + score + "]";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "FeedbackResp [id=" + id + ", content=" + content + ", contentType=" + contentType + ", modelId="
				+ modelId + ", answers=" + answers + "]";
	}
}
