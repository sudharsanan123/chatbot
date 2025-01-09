package com.example.chatbot.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatbot.entity.Language;
import com.example.chatbot.service.LanguageService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LanguageController {
	private static final Logger LOG = LoggerFactory.getLogger(LanguageController.class);
	
	@Autowired
	LanguageService languageService;
	
	@GetMapping("/languages")
	public List<Language> getLanguages() {
		try {
			List<Language> response = languageService.getLanguages();
			LOG.info("Api.getLanguages() => {}", response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.getLanguages() => error!!!", e);
			throw e;
		}
	}
	
}

