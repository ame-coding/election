package com.election.springapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.election.springapp.model.AssemblyConstituency;
import com.election.springapp.service.AssemblyConstituencyService;

@Controller
public class AddVotersController {
	
	private final AssemblyConstituencyService acService;
	
	public AddVotersController(AssemblyConstituencyService acService) {
		this.acService=acService;
	}
	
	@GetMapping("/ac")
	@ResponseBody
	public List<AssemblyConstituency> getAssemblyConstituencies() {
		
		return acService.getAssemblyConstituencies();		
	}
	
	
}