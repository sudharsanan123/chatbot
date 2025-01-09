package com.example.chatbot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chatbot.dto.ChatMessageRequest;
import com.example.chatbot.dto.ChatRequestv2;
import com.example.chatbot.dto.ChatResponsev2;
import com.example.chatbot.dto.FeedbackRequest;
import com.example.chatbot.dto.FeedbackResp;
import com.example.chatbot.entity.Case;
import com.example.chatbot.entity.Chat;
import com.example.chatbot.entity.ChatMessage;
import com.example.chatbot.entity.Feedback;
import com.example.chatbot.entity.Feedback.FeedbackCategory;
import com.example.chatbot.entity.User;
import com.example.chatbot.repo.CaseRepository;
import com.example.chatbot.repo.ChatRepository;
import com.example.chatbot.repo.UserRepository;
import com.example.chatbot.service.ChatService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChatController {

	private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

	@Autowired
	private ChatService chatService;

	@Autowired
	CaseRepository caseRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ChatRepository chatRepository;

	@PostMapping("v2/chat")
	public ChatResponsev2 performChatv2(@RequestBody ChatRequestv2 request) throws Exception {
		try {
			ChatResponsev2 chatMessages = chatService.performChatv2(request);
			LOG.info("Api.performChatv2({}) => {}", request, chatMessages);
			return chatMessages;
		} catch (Exception e) {
			LOG.error("Api.performChatv2({}) => error!!!", request);
			throw e;
		}
	}

	@GetMapping("/chat/history/{playerId}")
	public List<Chat> getChatHistory(@PathVariable Long playerId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) throws Exception {
		try {
			Page<Chat> chatMessages = chatService.getChatHistory(playerId, page, size);
			List<Chat> result = new ArrayList<>();
			if (chatMessages != null) {
				result = chatMessages.getContent();
			}
			LOG.info("Api.getChatHistory({}) => Result size : {}", playerId, result.size());
			return result;
		} catch (Exception e) {
			LOG.error("Api.getChatHistory({}) => error!!!", playerId, e);
			throw e;
		}
	}
	
	@GetMapping("v2/chat/{chatId}")
	public Chat getChat(@PathVariable Long chatId) {
		try{
			Chat chat = chatService.getChat(chatId);
			LOG.info("Api.getChat({}) => {}", chatId, chat);
		    return chat;
		}
		catch(Exception e) {
			LOG.error("Api.getChat({}, {}) => error!!!", chatId, e);
			throw e;
		}
	}

	@PutMapping("/case/re-assign")
	public Case updateTicket(@RequestBody Case input) {
		try {
			Optional<Case> caseResp = caseRepository.findById(input.getId());
			Optional<User> userResp = userRepository.findById(input.getUserId());
			if (caseResp.isPresent() && userResp.isPresent()) {
				Case existingCase = caseResp.get();

				// Update the existing case with the new values
				existingCase.setUserId(input.getUserId());
				existingCase.setCaseType(input.getCaseType());
				existingCase.setCompletedOn(input.getCompletedOn());
				existingCase.setStatus(input.getStatus());
				existingCase.setStartedOn(input.getStartedOn());
				existingCase.setEstimationDays(input.getEstimationDays());
				existingCase.setUserName(input.getUserName());
				existingCase.setGameName(input.getGameName());

				// Save the updated case
				LOG.info("Api.updateTicket({}, {}) => {}", input, existingCase);
				return caseRepository.save(existingCase); // Save the updated case object
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Either caseId or userId is invalid");
			}

		} catch (Exception e) {
			LOG.error("Api.updateTicket({}, {}) => error!!!", input, e);
			throw e;
		}
	}

	@PostMapping("/chat/conversation/{chatId}")
	public ChatMessage addChatMessage(@PathVariable Long chatId, @RequestBody ChatMessageRequest message) {
		try {
			ChatMessage response = chatService.addChatMessage(chatId, message);
			LOG.info("Api.addChatMessage({}, {}) => {}", message, response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.addChatMessage({}, {}) => error!!!", message, e);
			throw e;
		}
	}

	@GetMapping("/chat/conversation/{chatId}")
	public List<ChatMessage> getChatMessages(@PathVariable Long chatId) {
		try {
			List<ChatMessage> response = chatService.getChatMessages(chatId);
			LOG.info("Api.addChatMessage({}, {}) => {}", chatId, response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.addChatMessage({}, {}) => error!!!", chatId, e);
			throw e;
		}
	}

	@PostMapping("/{contentType}/feedback/{contentId}")
	public Feedback saveFeedback(@PathVariable Long contentId, @PathVariable String contentType,
			@RequestBody FeedbackRequest request) throws Exception {
		try {
			Optional<Case> caseResp = Optional.empty();
			Optional<Chat> chatResp = Optional.empty();
			Feedback response = null;
			if (FeedbackCategory.valueOf(contentType) == FeedbackCategory.CASE) {
				caseResp = caseRepository.findById(contentId);
				if (caseResp.isEmpty()) {
					LOG.warn("Case not found for contentId: {}", contentId);
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Case not found");
				}
			} else {
				chatResp = chatRepository.findById(contentId);
				if (chatResp.isEmpty()) {
					LOG.warn("Chat not found for contentId: {}", contentId);
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
				}
			}
			response = chatService.providePostResolutionFeedback(request, caseResp.orElse(null), chatResp.orElse(null));
			LOG.info("Api.saveFeedback({}, {}, {}) => {}", contentId, contentType, request, response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.saveFeedback({}, {}, {}) => error!!!", contentId, contentType, request, e);
			throw e;
		}
	}

	@PostMapping("/case/{contentId}/{description}")
	public Case createCase(@PathVariable Long contentId, @PathVariable String description) throws Exception {
		try {
			Case response = chatService.createSupportCaseByChatId(contentId, description);
			LOG.info("Api.createCase({}, {}, {}) => {}", contentId, description, response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.createCase({}, {}, {}) => error!!!", contentId, description, e);
			throw e;
		}
	}

	@GetMapping("/case")
	public List<Case> getCaseDataByCaseId(@RequestParam(required = false) Long caseId) {
		try {
			List<Case> response = caseRepository.findById(caseId).stream().toList();
			LOG.info("Api.getCaseDataByCaseId() => {}", response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.getCaseDataByCaseId() => error!!!", e);
			throw e;
		}
	}

	@GetMapping("/feedback/questions")
	public List<FeedbackResp> getFeedbackQuestionsAndAnswers() {
		try {
			List<FeedbackResp> response = chatService.getQuestionsAndAnswers();
			LOG.info("Api.getFeedbackQuestionsAndAnswers() => {}", response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.getFeedbackQuestionsAndAnswers() => error!!!", e);
			throw e;
		}
	}

	@GetMapping("/allCases")
	public List<Case> getAllCases() {
		try {
			List<Case> response = caseRepository.findAll();
			LOG.info("Api.getAllCases() => {}", response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.getAllCases() => error!!!", e);
			throw e;
		}
	}
}
