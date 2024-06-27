package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.User;
import com.javaexpress.repository.UserRepository;
import com.javaexpress.service.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		log.info("UserService :: createUser {}", user.getUsername());
		userRepository.save(user);
		log.info("User Successfully saved in DB");
	}

	public List<User> fetchAllUsers() {
		log.info("UserService :: fetchAllUsers ");
		List<User> userList = userRepository.findAll();
		var result = userList.stream().sorted(Comparator.comparing(User::getUsername)).toList();
		return result;
	}

	public User findUserById(Long userId) {
		log.info("UserService :: findUserById ");
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
	}

	public void updateUser(Long userId , User inputUser) {
		log.info("UserService :: updateUser ");
		User dbUser = findUserById(userId);
		dbUser.setUsername(inputUser.getUsername());
		dbUser.setEmail(inputUser.getEmail());
		dbUser.setPassword(inputUser.getPassword());
		
		userRepository.save(dbUser);
	}

	//Hard Delete
	public void deleteUser(Long userId) {
		log.info("UserService :: deleteUser ");
		if (userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		} else {
			throw new ResourceNotFoundException("User is not Exist in DB");
		}
	}
	//Hard Delete
	public void deleteUser_another(Long userId) {
		log.info("UserService :: deleteUser_another ");
		User dbUser = findUserById(userId);
		userRepository.delete(dbUser);
	}

	public User fetchUserData(String username, String password) {
		log.info("UserService :: fetchUserData ");
		return userRepository.findByUsernameAndPassword(username, password);
		
	}

	public User fetchUserByName(String username) {
		log.info("UserService :: fetchUserByName ");
		return userRepository.findByUsername(username);
	}

	public User fetchUserByEmail(String email) {
		log.info("UserService :: fetchUserByEmail ");
		return userRepository.findByEmail(email);
	}

}
