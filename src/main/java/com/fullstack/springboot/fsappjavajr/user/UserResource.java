package com.fullstack.springboot.fsappjavajr.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
	private UserRepository userRepository;

	public UserResource(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("users")
	public List<User> getUsers() {
		return userRepository.getUsers();
	}

	@GetMapping("user/{user}")
	public User getUserByName(@PathVariable String user) {
		return userRepository.getUserByName(user);
	}

}
