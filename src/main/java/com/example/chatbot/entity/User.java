package com.example.chatbot.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private List<Long> preferredLanguage;
	private List<Long> platform;
	private List<Long> title;
	private Long groupId;
	private Boolean enabled;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Long> getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(List<Long> preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public List<Long> getPlatform() {
		return platform;
	}

	public void setPlatform(List<Long> platform) {
		this.platform = platform;
	}

	public List<Long> getTitle() {
		return title;
	}

	public void setTitle(List<Long> title) {
		this.title = title;
	}
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", preferredLanguage="
				+ preferredLanguage + ", platform=" + platform + ", title=" + title + ", groupId=" + groupId
				+ ", enabled=" + enabled + "]";
	}

}
