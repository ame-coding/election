package com.election.springapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.election.springapp.model.MapCandidateAcDto;
import com.election.springapp.model.User;
import com.election.springapp.repository.MapCandidateAcRepository;

@Service
public class MapCandidateAcService {
	
	private final MapCandidateAcRepository repo;
	
	public MapCandidateAcService (MapCandidateAcRepository repo) {
		
		this.repo=repo;
		
	}
	
	
	public void save(MapCandidateAcDto dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = (User) auth.getPrincipal();
		Long createdById = loggedInUser.getId();
		
		if(repo.existsByMapping(dto)) {
		
			throw new IllegalArgumentException("Candidate is already mapped to that Assembly Constituency");
			
		}
		
		repo.save(dto, createdById);
		
	}
	
	
	
}