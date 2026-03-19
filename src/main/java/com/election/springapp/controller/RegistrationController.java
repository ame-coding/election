package com.election.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.election.springapp.service.UserService;

@Controller
public class RegistrationController{
	
	private final UserService userService;
	
	public RegistrationController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam String username, @RequestParam String password) {
		
		userService.register(username, password);
		return "redirect:/login";
		
	}
	
	
}
