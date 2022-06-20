package com.neosoft.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.entity.User;
import com.neosoft.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRoleAndUser() {
		userService.initRoleAndUser();
	}
	
	@PostMapping("/registeruser")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('admin')")
	public String forAdmin() {
		return "This URL is only accessible to Admin";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasAnyRole('admin','user')")
	public String forUser() {
		return "This URL is only accessible to User";
	}

}
