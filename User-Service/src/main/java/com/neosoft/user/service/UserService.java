package com.neosoft.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neosoft.user.entity.Users;
import com.neosoft.user.repository.UserRepository;
import com.neosoft.user.valueObject.Department;
import com.neosoft.user.valueObject.ResponseTemplateVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public Users saveUser(Users user) {
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Inside getUserWithDepartment in UserService");
		ResponseTemplateVO rtvo = new ResponseTemplateVO();
		Users user = userRepository.findById(userId).get();
		
		Department department = 
				restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
		
		rtvo.setUser(user);
		rtvo.setDepartment(department);
		
		return rtvo;
	}

}
