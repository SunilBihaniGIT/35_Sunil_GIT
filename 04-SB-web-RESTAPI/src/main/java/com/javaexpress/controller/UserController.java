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

	// http://localhost:8080/api/v1/user/Bihani/Test@123
	@GetMapping("/login/{username}/{password}")
	public User fetchUser(@PathVariable String username, @PathVariable String password) {
		log.info("UserController :: fetchUser {} {}", username , password);
		return userService.fetchUserData(username, password);
	}

	// http://localhost:8080/api/v1/user/Bihani
	@GetMapping("/login/{username}")
	public User fetchUserByName(@PathVariable String username) {
		log.info("UserController :: fetchUserByName {}", username);
		return userService.fetchUserByName(username);
	}

	// http://localhost:8080/api/v1/user/login/email/sunil@gmail.com
	@GetMapping("/login/email/{email}")
	public User fetchUserByEmail(@PathVariable String email) {
		log.info("UserController :: fetchUserByEmail {}", email);
		return userService.fetchUserByEmail(email);
	}

}
