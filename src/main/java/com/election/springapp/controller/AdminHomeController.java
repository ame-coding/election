package com.election.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {
	
	@GetMapping("/addvoters")
	public String addvotersPage() {
		return "addvoters";
	}
	
}