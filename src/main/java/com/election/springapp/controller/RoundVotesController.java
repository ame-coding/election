package com.election.springapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.election.springapp.model.CandidateDetails;
import com.election.springapp.service.RoundVotesService;


@Controller
public class RoundVotesController {
	
	private static final Logger log=LoggerFactory.getLogger(RoundVotesController.class);
	private final RoundVotesService service;
	
	public RoundVotesController(RoundVotesService service) {
		
		this.service=service;
		
	}
	
	@GetMapping("/roundvotes")
	public String roundvotesPage() {
		
		return "addroundvotes";
		
	}
	
	
	
	@GetMapping("/roundvotes/rounds")
	@ResponseBody
	public List<Integer> getRounds(@RequestParam Long acno){
		
		return service.getRoundsByAcno(acno);
		
	}
	
	@GetMapping("/roundvotes/candidates")
	@ResponseBody
	public List<CandidateDetails> getCandidates(@RequestParam Long acno){
		
		
		return service.getCandidatesByAcno(acno);
		
	}
	
	
	
}