package com.election.springapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.election.springapp.model.CandidatesAcsMap;
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
	
	public List<CandidatesAcsMap> findAll(){
		
		return repo.findAll();
		
	}
	
	public boolean candidateHasAcReference(Long code) {
		
		return repo.candidateHasAcReference(code);
		
	}
	
	
	public void deleteByCandidatecodeAndAcno(Long code, Long acno) {
		
		repo.deleteByCandidatecodeAndAcno(code, acno);
		
	}
	
	public void deleteByCandidatecode(Long code) {
		
		repo.deleteByCandidatecode(code);
		
	}
	
	
}