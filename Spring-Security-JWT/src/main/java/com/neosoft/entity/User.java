package com.neosoft.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String surname;
	private String pincode;
	
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	private String username;
	
	private String password;
	
	private int deleted = 0;
	private int locked = 0;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REMOVE,
			CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinTable(name = "USER_ROLE",
		joinColumns = {
				@JoinColumn(name = "USER_ID")
		},
		inverseJoinColumns = {
				@JoinColumn(name = "ROLE_ID")
		}
	)
	private Set<Role> roles;

}
