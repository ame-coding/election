package com.election.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.election.springapp.model.Party;
import com.election.springapp.repository.PartyRepository;

@Service
public class PartyService {
	
	private final PartyRepository repo;
	
	public PartyService (PartyRepository repo) {
		
		this.repo=repo;
		
	}
	
	public List<Party> getParties(){
		return repo.getParties();
	}
	
	public List<Party> getPartyDetails(){
		return repo.getPartyDetails();
	}
	
	public Optional<Party> findImageByCode(Long code){
		return repo.findImageByCode(code);
	}
	
	
}

