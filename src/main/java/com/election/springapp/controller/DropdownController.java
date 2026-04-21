package com.election.springapp.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.election.springapp.model.AssemblyConstituency;
import com.election.springapp.model.Candidate;
import com.election.springapp.model.Party;
import com.election.springapp.service.AssemblyConstituencyService;
import com.election.springapp.service.CandidateService;
import com.election.springapp.service.PartyService;

@RestController
@RequestMapping("/dropdown")
public class DropdownController {
	
	private final CandidateService candidateService;
	private final AssemblyConstituencyService acService;
	private final PartyService partyService;
	
	public DropdownController (CandidateService candidateService, AssemblyConstituencyService acService, PartyService partyService) {
		
		this.candidateService=candidateService;
		this.acService=acService;
		this.partyService=partyService;
		
	}
	
	
	@GetMapping("/candidates")
	public List<Candidate> getCandidates(){
		
		return candidateService.findCandidates();
		
	}
	
	@GetMapping("/ac")
	public List<AssemblyConstituency> getAssemblyConstituencies() {
		
		return acService.getAssemblyConstituencies();		
	}
	
	
	@GetMapping("/parties")
	@ResponseBody
	public List<Party> getParties(){
		
		return partyService.getParties();
		
	}
	
	@GetMapping("/parties/{code}/image")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable long code) {
	
		byte[] symbol = partyService.findImageByCode(code).map(Party::getSymbol).orElse(null);
		
		if (symbol == null) return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(symbol);
		
	}
	
	
}