package com.neosoft.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {
	
	@Id
	private Long id;
	private String username;
	private String password;
	private String role;
	

}
