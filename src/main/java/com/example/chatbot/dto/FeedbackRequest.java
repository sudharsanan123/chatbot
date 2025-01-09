package com.example.chatbot.dto;

import java.util.List;

public class FeedbackRequest {
	private String playerFeedbackComments;
	private Boolean issueResolved;
	private Boolean satisfiedWithSupport;
	private List<Long> scores;
	private List<QuestionAndAnswerReq> questionAndAnswer;
	
	public static class QuestionAndAnswerReq {
		private String question;
		private String Answer;

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return Answer;
		}

		public void setAnswer(String answer) {
			Answer = answer;
		}

		@Override
		public String toString() {
			return "QuestionAndAnswerReq [question=" + question + ", Answer=" + Answer + "]";
		}
	}

	public String getPlayerFeedbackComments() {
		return playerFeedbackComments;
	}

	public void setPlayerFeedbackComments(String playerFeedbackComments) {
		this.playerFeedbackComments = playerFeedbackComments;
	}

	public Boolean getIssueResolved() {
		return issueResolved;
	}

	public void setIssueResolved(Boolean issueResolved) {
		this.issueResolved = issueResolved;
	}

	public Boolean getSatisfiedWithSupport() {
		return satisfiedWithSupport;
	}

	public void setSatisfiedWithSupport(Boolean satisfiedWithSupport) {
		this.satisfiedWithSupport = satisfiedWithSupport;
	}

	public List<Long> getScores() {
		return scores;
	}

	public void setScores(List<Long> scores) {
		this.scores = scores;
	}

	public List<QuestionAndAnswerReq> getQuestionAndAnswer() {
		return questionAndAnswer;
	}

	public void setQuestionAndAnswer(List<QuestionAndAnswerReq> questionAndAnswer) {
		this.questionAndAnswer = questionAndAnswer;
	}

	@Override
	public String toString() {
		return "FeedbackRequest [playerFeedbackComments=" + playerFeedbackComments + ", issueResolved=" + issueResolved
				+ ", satisfiedWithSupport=" + satisfiedWithSupport + ", scores=" + scores + ", questionAndAnswer="
				+ questionAndAnswer + "]";
	}
}
