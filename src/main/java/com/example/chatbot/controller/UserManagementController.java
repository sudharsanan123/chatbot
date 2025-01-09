package com.example.chatbot.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.chatbot.entity.Group;
import com.example.chatbot.entity.User;
import com.example.chatbot.repo.GroupRepository;
import com.example.chatbot.repo.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserManagementController {

	private static final Logger LOG = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	GroupRepository groupRepo;

	@PostMapping("/user")
	public User addUser(@RequestBody User user) throws Exception {
		try {
			User savedUser = userRepo.save(user);
	    	LOG.info("Api.addUser() => {}", savedUser);
	    	return savedUser;
		} catch (Exception e) {
			LOG.error("Api.addUser({}) => error!!!", user, e);
			throw e;
		}
	}
	
	@PutMapping("/user")
	public User editUser(@RequestBody User user) throws Exception {
		try {
			Optional<User> mayBeUser = userRepo.findById(user.getId());
			if (mayBeUser.isPresent()) {
				User existingUser = mayBeUser.get();
				if (null != user.getFirstName()) {
					existingUser.setFirstName(user.getFirstName());
				}
				if (null != user.getLastName()) {
					existingUser.setLastName(user.getLastName());
				}
				if (null != user.getPlatform()) {
					existingUser.setPlatform(user.getPlatform());
				}
				if (null != user.getPlatform()) {
					existingUser.setPreferredLanguage(user.getPreferredLanguage());
				}
				if (null != user.getGroupId()) {
					existingUser.setGroupId(user.getGroupId());
				}
				if (null != user.getTitle()) {
					existingUser.setTitle(user.getTitle());
				}
				User savedUser = userRepo.save(existingUser);
				LOG.info("Api.addUser() => {}", savedUser);
				return savedUser;
			} else
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, " user is invalid");
		} catch (Exception e) {
			LOG.error("Api.addUser({}) => error!!!", user, e);
			throw e;
		}
	}

	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable Long userId) throws Exception {
		try {
			Optional<User> mayBeUser = userRepo.findById(userId);
			if(mayBeUser.isPresent()) {
		    	LOG.info("Api.getUser() => {}", mayBeUser.get());
		    	return mayBeUser.get();
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND, " user is invalid");
		} catch (Exception e) {
			LOG.error("Api.getUser({}) => error!!!", userId, e);
			throw e;
		}
	}
	
	@DeleteMapping("/user/{userId}")
	public User disableUser(@PathVariable Long userId) throws Exception {
		try {
			Optional<User> mayBeUser = userRepo.findById(userId);
			if(mayBeUser.isPresent()) {
				User existingUser = mayBeUser.get();
				existingUser.setEnabled(false);
				User savedUser = userRepo.save(existingUser);
		    	LOG.info("Api.disableUser() => {}", savedUser);
		    	return savedUser;
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND, " user is invalid");
		} catch (Exception e) {
			LOG.error("Api.disableUser({}) => error!!!", userId, e);
			throw e;
		}
	}
	
	@PostMapping("/group")
	public Group addGroup(@RequestBody Group group) throws Exception {
		try {
			Group savedGroup = groupRepo.save(group);
	    	LOG.info("Api.addGroup() => {}", savedGroup);
	    	return savedGroup;
		} catch (Exception e) {
			LOG.error("Api.addGroup({}) => error!!!", e);
			throw e;
		}
		
	}
	
	@PutMapping("/group")
	public Group editGroup(@RequestBody Group group) throws Exception {
		try {
			Optional<Group> mayBeGroup = groupRepo.findById(group.getId());
			if(mayBeGroup.isPresent()) {
				Group existingGroup = mayBeGroup.get();
				existingGroup.setName(group.getName());
				Group savedGroup = groupRepo.save(existingGroup);
		    	LOG.info("Api.editGroup() => {}", savedGroup);
		    	return savedGroup;
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND, " group is invalid");
		} catch (Exception e) {
			LOG.error("Api.editGroup({}) => error!!!", group, e);
			throw e;
		}
		
	}

	@GetMapping("/group/{groupId}")
	public Group getGroup(@PathVariable Long groupId) throws Exception {
		try {
			Optional<Group> mayBeGroup = groupRepo.findById(groupId);
			if(mayBeGroup.isPresent()) {
		    	LOG.info("Api.getGroup() => {}", mayBeGroup.get());
		    	return mayBeGroup.get();
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND, " group is invalid");
		} catch (Exception e) {
			LOG.error("Api.getGroup({}) => error!!!", groupId, e);
			throw e;
		}
	}
	
	@DeleteMapping("/group/{groupId}")
	public Group disableGroup(@PathVariable Long groupId) throws Exception {
		try {
			Optional<Group> mayBeGroup = groupRepo.findById(groupId);
			if(mayBeGroup.isPresent()) {
				Group existingGroup = mayBeGroup.get();
				existingGroup.setEnabled(false);
				Group savedGroup = groupRepo.save(existingGroup);
		    	LOG.info("Api.disableUser() => {}", savedGroup);
		    	return savedGroup;
			}
			else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "group is invalid");
		} catch (Exception e) {
			LOG.error("Api.disableUser({}) => error!!!", groupId, e);
			throw e;
		}
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		try {
			List<User> response = userRepo.findAll();
			LOG.info("Api.getUsers() => {}", response);
			return response;
		} catch (Exception e) {
			LOG.error("Api.getUsers() => error!!!", e);
			throw e;
		}
	}	
}
