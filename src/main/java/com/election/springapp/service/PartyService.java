package com.election.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.election.springapp.model.Party;
import com.election.springapp.repository.PartyRepository;

@Service
public class PartyService {
	
	private final PartyRepository repo;
	private final CandidateService candidateService;
	
	public PartyService (PartyRepository repo, CandidateService candidateService) {
		
		this.repo=repo;
		this.candidateService=candidateService;
		
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
	
	public boolean partyHasCandidateReference(Long code) {
		
		return candidateService.candidateHasAcReference(code);
		
	}
	
	@Transactional
	public void deleteById(Long code) {
		
		List<Long> candidatecodes=candidateService.findCandidatecodesByPartyCode(code);
		
		for(Long candidatecode : candidatecodes) {
			
			candidateService.deleteByCode(candidatecode);
			
		}
		
		repo.deleteById(code);
		
	}
	
	
}

