package com.neosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.entity.Role;
import com.neosoft.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createNewRole(Role role) {
		return roleRepository.save(role);
	}

}
