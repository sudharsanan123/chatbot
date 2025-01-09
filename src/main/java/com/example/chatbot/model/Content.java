package com.example.chatbot.model;

import java.util.List;

public class Content {
	private Questionare questionare;

	public Questionare getQuestionare() {
		return questionare;
	}

	public void setQuestionare(Questionare questionare) {
		this.questionare = questionare;
	}

	@Override
	public String toString() {
		return "Content [questionare=" + questionare + "]";
	}



	public static class Questionare {
		private Questions questions;

		public Questions getQuestions() {
			return questions;
		}

		public void setQuestions(Questions questions) {
			this.questions = questions;
		}

		@Override
		public String toString() {
			return "Questionare [questions=" + questions + "]";
		}

	}

	public static class Questions {
		private String question;
		private List<Answer> answers;

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public List<Answer> getAnswers() {
			return answers;
		}

		public void setAnswers(List<Answer> answers) {
			this.answers = answers;
		}

		@Override
		public String toString() {
			return "Questions [question=" + question + ", answers=" + answers + "]";
		}

	}

	public static class Answer {
		private String answer;
		private Questions questions;
		private String solution;

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public Questions getQuestions() {
			return questions;
		}

		public void setQuestions(Questions questions) {
			this.questions = questions;
		}

		public String getSolution() {
			return solution;
		}

		public void setSolution(String solution) {
			this.solution = solution;
		}

		@Override
		public String toString() {
			return "Answer [answer=" + answer + ", questions=" + questions + ", solution=" + solution + "]";
		}

	}

}
