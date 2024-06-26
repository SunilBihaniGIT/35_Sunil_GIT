package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.User;
import com.javaexpress.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	// Json
	@PostMapping
	public void createUser(@RequestBody User user) {
		log.info("UserController :: createUser {}", user.getUsername());
		userService.createUser(user);
	}

	// http://localhost:8080/api/v1/user/20
	@GetMapping("{userId}")
	public User getUserById(@PathVariable Long userId) {
		log.info("UserController :: getUserById {}", userId);
		return userService.findUserById(userId);
	}
    @GetMapping("user/{username}")
	public void fetchUserInformationByUsername(@PathVariable String username) {
    	log.info("UserController :: fetchUserInformationByUsername {}", username);
		
	}
	
	@PutMapping("{userId}")
	public void updateUser(@PathVariable Long userId, @RequestBody User user) {
		log.info("UserController :: updateUser {}", userId);
		userService.updateUser(userId, user);
	}

	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable Long userId) {
		log.info("UserController :: deleteUser {}", userId);
		userService.deleteUser(userId);
	}

}
