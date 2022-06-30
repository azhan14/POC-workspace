package com.neosoft.service;

import java.util.HashSet;
import java.util.Set;

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

	public User saveUser(User user) {
		Role role = roleRepository.findById("user").get();
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		user.setPassword(getEncodedPassword(user.getPassword()));
		return userRepository.save(user);
	}

}
