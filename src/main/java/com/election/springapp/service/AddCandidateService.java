package com.election.springapp.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.election.springapp.model.AddCandidateDto;
import com.election.springapp.model.User;
import com.election.springapp.repository.AddCandidateRepository;

@Service
public class AddCandidateService {
	
	private final AddCandidateRepository repo;
	
	public AddCandidateService (AddCandidateRepository repo) {
		
		this.repo=repo;
		
	}
	
	public void save(AddCandidateDto dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = (User) auth.getPrincipal();
		Long createdById = loggedInUser.getId();
		
		if(repo.existsByName(dto.getName().trim())) {
			throw new IllegalArgumentException("Candidate Name already in use");
		}
		
		repo.save(dto, createdById);
		
	}
	
	
}