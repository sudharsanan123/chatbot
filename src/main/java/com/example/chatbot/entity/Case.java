package com.example.chatbot.entity;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "case_audit")
@JsonInclude(Include.NON_NULL)
public class Case {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long chatId;
	private Long userId;
	private String feedback;
	private Instant createdOn;
	@Enumerated(EnumType.STRING)
	private CaseStatus status;
	private String caseType;
	private Instant completedOn;
	private String userName;
	private String gameName;
	private Instant startedOn;
	private Integer estimationDays;

	public enum CaseStatus {
		OPEN, RESOLVED, IN_PROGRESS
	}

	public Case() {
	}

	public Case(Long userId, Long chatId, String caseType, Instant completedOn, String userName, String gameName) {
		this.userId = userId;
		this.chatId = chatId;
		this.caseType = caseType;
		this.completedOn = completedOn;
		this.userName = userName;
		this.gameName = gameName;
		this.createdOn = Instant.now();
		this.status = CaseStatus.OPEN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public CaseStatus getStatus() {
		return status;
	}

	public void setStatus(CaseStatus status) {
		this.status = status;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public Instant getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Instant completedOn) {
		this.completedOn = completedOn;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Instant getStartedOn() {
		return startedOn;
	}

	public void setStartedOn(Instant startedOn) {
		this.startedOn = startedOn;
	}

	public Integer getEstimationDays() {
		return estimationDays;
	}

	public void setEstimationDays(Integer estimationDays) {
		this.estimationDays = estimationDays;
	}

	@Override
	public String toString() {
		return "Case [id=" + id + ", chatId=" + chatId + ", userId=" + userId + ", feedback=" + feedback
				+ ", createdOn=" + createdOn + ", status=" + status + ", caseType=" + caseType + ", completedOn="
				+ completedOn + ", userName=" + userName + ", gameName=" + gameName + ", startedOn=" + startedOn
				+ ", estimationDays=" + estimationDays + "]";
	}
}
