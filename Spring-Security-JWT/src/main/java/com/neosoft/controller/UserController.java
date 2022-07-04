package com.neosoft.controller;

import java.util.List;

//import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.Exception.InvalidRequestException;
import com.neosoft.entity.User;
import com.neosoft.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostConstruct
//	public void initRoleAndUser() {
//		userService.initRoleAndUser();
//	}
	
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
	
	@GetMapping("/user/{id}")
	@PreAuthorize("hasAnyRole('admin','user')")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/user/{id}")
	@PreAuthorize("hasAnyRole('admin','user')")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {
		User savedUser = userService.getUserById(id);
		
		savedUser.setName(user.getName());
		savedUser.setSurname(user.getSurname());
		savedUser.setUsername(user.getUsername());
		savedUser.setPassword(user.getPassword());
		savedUser.setPincode(user.getPincode());
		savedUser.setDob(user.getDob());
		savedUser.setJoiningDate(user.getJoiningDate());
		
		return userService.saveUser(savedUser);
		
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/users/delete")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getAllSoftDeletedUsers() {
		return userService.getAllSoftDeletedUsers();
	}
	
	@PutMapping("/user/soft/delete/{id}")
	@PreAuthorize("hasAnyRole('admin')")
	public void softDelete(@PathVariable Long id) {
		if(!userService.findUserById(id).isPresent()) {
			throw new InvalidRequestException("User with Id "+id+ " not found.");
		}
		userService.softDelete(id);
	}
	
	@DeleteMapping("/user/hard/delete/{id}")
	@PreAuthorize("hasAnyRole('admin')")
	public void hardDelete(@PathVariable Long id) {
		if(!userService.findUserById(id).isPresent()) {
			throw new InvalidRequestException("User with Id "+id+ " not found.");
		}
		userService.deleteById(id);
	}
	
	@GetMapping("/users/sort/dob")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getUsersSortByDob() {
		List<User> users = userService.getAllUsers();
		return userService.getUsersSortedByDob(users);
	}
	
	@GetMapping("/users/sort/joiningdate")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getUsersSortByJoiningDate() {
		List<User> users = userService.getAllUsers();
		return userService.getUsersSortedByJoiningDate(users);
	}
	
	@PutMapping("/user/lock/{id}")
	@PreAuthorize("hasAnyRole('admin')")
	public void lockUser(@PathVariable Long id) {
		if(!userService.findUserById(id).isPresent()) {
			throw new InvalidRequestException("User with Id "+id+ " not found.");
		}
		userService.lockUser(id);
	}
	
	@PutMapping("/user/unlock/{id}")
	@PreAuthorize("hasAnyRole('admin')")
	public void unlockUser(@PathVariable Long id) {
		if(!userService.findUserById(id).isPresent()) {
			throw new InvalidRequestException("User with Id "+id+ " not found.");
		}
		userService.unlockUser(id);
	}
	
	@GetMapping("/user/name/{name}")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getUserByName(@PathVariable String name) {
		return userService.findByName(name);
	}
	
	@GetMapping("/user/surname/{surname}")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getUserBySurname(@PathVariable String surname) {
		return userService.findBySurname(surname);
	}
	
	@GetMapping("/user/pincode/{pincode}")
	@PreAuthorize("hasAnyRole('admin')")
	public List<User> getUserByPincode(@PathVariable String pincode) {
		return userService.findByPincode(pincode);
	}

}
