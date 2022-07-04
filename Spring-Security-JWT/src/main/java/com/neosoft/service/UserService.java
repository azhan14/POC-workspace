package com.neosoft.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.neosoft.entity.Role;
import com.neosoft.entity.User;
import com.neosoft.repository.RoleRepository;
import com.neosoft.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public User registerUser(User user) {
		Role role = roleRepository.findById("user").get();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public void initRoleAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("admin");
		adminRole.setRoleDescription("Admin Role");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("user");
		userRole.setRoleDescription("Default role for users");
		roleRepository.save(userRole);
		
		User adminUser = new User();
		adminUser.setId(1L);
		adminUser.setUsername("admin123");
		adminUser.setPassword(getEncodedPassword("admin@pass"));
		Set<Role> adminRoles = new HashSet<Role>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userRepository.save(adminUser);
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public Optional<User> findUserById(Long id){
		return userRepository.findById(id);
	}

	public User saveUser(User user) {
		Role role = roleRepository.findById("user").get();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		List<User> users = userRepository.getAllUsers();
		users.removeIf(u -> u.getUsername().equalsIgnoreCase("admin123"));
		return users;
	}
	
	public List<User> getAllSoftDeletedUsers(){
		return userRepository.getAllSoftDeletedUsers();
	}
	
	public void softDelete(Long id) {
		userRepository.softDelete(id);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public List<User> getUsersSortedByDob(List<User> users) {
		Comparator<User> byDobComparator = Comparator.comparing(User::getDob);
		return users.stream().sorted(byDobComparator).collect(Collectors.toList());
	}
	
	public List<User> getUsersSortedByJoiningDate(List<User> users) {
		Comparator<User> byJoiningDateComparator = Comparator.comparing(User::getJoiningDate);
		return users.stream().sorted(byJoiningDateComparator).collect(Collectors.toList());
	}
	
	public void lockUser(Long id) {
		userRepository.lockUser(id);
	}
	
	public void unlockUser(Long id) {
		userRepository.unlockUser(id);
	}
	
	public List<User> findByName(String name){
		return userRepository.findByName(name);
	}
	
	public List<User> findBySurname(String surname){
		return userRepository.findBySurname(surname);
	}
	
	public List<User> findByPincode(String pincode){
		return userRepository.findByPincode(pincode);
	}

}
