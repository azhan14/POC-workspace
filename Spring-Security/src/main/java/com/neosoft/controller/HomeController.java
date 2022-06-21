package com.neosoft.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/api/home")
	public String home() {
		return "This is home";
	}
	
	@GetMapping("/api/admin")
	public String admin() {
		return "This is admin";
	}

}
