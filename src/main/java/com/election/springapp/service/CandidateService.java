package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.election.springapp.model.Candidate;
import com.election.springapp.model.CandidateDetails;
import com.election.springapp.repository.CandidateRepository;

@Service
public class CandidateService {
	
	private final CandidateRepository repo;
	
	public CandidateService (CandidateRepository repo) {
		this.repo=repo;
	}
	

	public List<Candidate> findCandidates(){
		
		return repo.findCandidates();
		
	}
	
	public List<CandidateDetails> showCandidateDetails(){
		
		List<CandidateDetails> records = repo.showCandidateDetails();
		
		for(CandidateDetails cd : records) {
			cd.setAc(repo.findACByCandidateCode(cd.getCode()));
		}
		
		return records;
		
	}
	
	
		
}