package com.example.chatbot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatbot.dto.ContentResponse;
import com.example.chatbot.entity.ChatContent;
import com.example.chatbot.entity.Content;
import com.example.chatbot.service.ChatContentService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatContentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChatContentController.class);

	@Autowired
	private ChatContentService chatContentService;

	@PutMapping("v1/content")
	public ChatContent updateContent(@RequestParam Long contentId, @RequestBody ChatContent chatContent) {
		try {
			ChatContent updatedContent = chatContentService.updateContent(contentId, chatContent);
	    	LOG.info("Api.updateContent({}) => {}", chatContent, updatedContent);
			return updatedContent;
		} catch (Exception e) {
    		LOG.error("Api.updateContent({}) => error!!!", chatContent, e);
			throw e;
		}
	}
	
	@DeleteMapping("v1/content")
	public ChatContent deleteContent(@RequestParam Long contentId) {
		try {
			ChatContent deletedContent = chatContentService.deleteContent(contentId);
	    	LOG.info("Api.deleteContent({}) => {}", contentId, deletedContent);
			return deletedContent;
		} catch (Exception e) {
    		LOG.error("Api.deleteContent({}) => error!!!", contentId, e);
			throw e;
		}
	}
	
	@PostMapping("v1/content")
	public ChatContent createContent(@RequestParam Long contentId, @RequestBody ChatContent chatContent) {
		try {
			ChatContent createdContent = chatContentService.createContent(contentId,chatContent);
	    	LOG.info("Api.createContent({}) => {}", chatContent, createdContent);
			return createdContent;
		} catch (Exception e) {
    		LOG.error("Api.createContent({}) => error!!!", chatContent, e);
			throw e;
		}
	}
	
	@PostMapping("v1/content/copy")
	public boolean copyContent(@RequestParam Long modelId) {
		try {
			boolean createdContent = chatContentService.copyContent(modelId);
	    	LOG.info("Api.copyContent({}) => {}", modelId);
			return createdContent;
		} catch (Exception e) {
    		LOG.error("Api.copyContent() => error!!!", e);
			throw e;
		}
	}
	
	@PutMapping("v2/content")
	public Content updateContentv2(@RequestParam Long contentId, @RequestParam String name, @RequestBody com.example.chatbot.model.Content chatContent) {
		try {
			Content updatedContent = chatContentService.updateContentv2(contentId, chatContent, name);
	    	LOG.info("Api.updateContentv2({}, {}) => {}", contentId, chatContent, updatedContent);
			return updatedContent;
		} catch (Exception e) {
    		LOG.error("Api.updateContentv2({}, {}) => error!!!", contentId, chatContent);
			throw e;
		}
	}
	
	@DeleteMapping("v2/content")
	public Content deleteContentv2(@RequestParam Long contentId) {
		try {
			Content deletedContent = chatContentService.deleteContentv2(contentId);
	    	LOG.info("Api.deleteContentv2({}) => {}", contentId, deletedContent);
			return deletedContent;
		} catch (Exception e) {
    		LOG.error("Api.deleteContentv2({}) => error!!!", contentId);
			throw e;
		}
	}
	
	@PostMapping("v2/content")
	public Content createContentv2(@RequestParam Long languageId, @RequestParam String name, @RequestBody com.example.chatbot.model.Content chatContent) {
		try {
			Content createdContent = chatContentService.createContentv2(chatContent,languageId, name);
	    	LOG.info("Api.createContentv2({}, {}) => {}", languageId, chatContent, createdContent);
			return createdContent;
		} catch (Exception e) {
    		LOG.error("Api.createContentv2({}, {}) => error!!!", languageId, chatContent);
			throw e;
		}
	}
	
	@PostMapping("v2/content/copy")
	public Content copyContentv2(@RequestParam Long srcContentId, @RequestParam String name) {
		try {
			Content createdContent = chatContentService.copyContentv2(srcContentId, name);
	    	LOG.info("Api.copyContentv2({}) => {}", srcContentId, createdContent);
			return createdContent;
		} catch (Exception e) {
    		LOG.error("Api.copyContentv2({}) => error!!!", srcContentId);
			throw e;
		}
	}
	
	@GetMapping("v2/content")
	public Content getContentv2(@RequestParam Long contentId) {
		try {
			Content content = chatContentService.getContentv2(contentId);
	    	LOG.info("Api.getContentv2({}) => {}", contentId, content);
			return content;
		} catch (Exception e) {
    		LOG.error("Api.getContentv2({}) => error!!!", contentId);
			throw e;
		}
	}
	
	@GetMapping("v2/contents")
	public List<ContentResponse> getContents() {
		try {
			List<ContentResponse> contents = chatContentService.getContents();
	    	LOG.info("Api.getContents() => {}",contents);
			return contents;
		} catch (Exception e) {
    		LOG.error("Api.getContents() => error!!!");
			throw e;
		}
	}
}
