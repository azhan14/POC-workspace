package com.neosoft.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String category;
	
	@With 
	private Double price;
	
	@ManyToMany(mappedBy = "products")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Order> orders;

}
