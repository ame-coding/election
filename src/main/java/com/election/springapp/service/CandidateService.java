package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.election.springapp.model.Candidate;
import com.election.springapp.model.CandidateDetails;
import com.election.springapp.repository.CandidateRepository;
import com.election.springapp.repository.MapCandidateAcRepository;

@Service
public class CandidateService {
	
	private final CandidateRepository repo;
	private final MapCandidateAcRepository mapCandidateAcRepository;
	
	public CandidateService (CandidateRepository repo, MapCandidateAcRepository mapCandidateAcRepository) {
		this.repo=repo;
		this.mapCandidateAcRepository=mapCandidateAcRepository;
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
	
	public boolean candidateHasAcReference(Long code){
		
		return mapCandidateAcRepository.candidateHasAcReference(code);
		
	}
	
	@Transactional
	public void deleteByCode(Long code) {
		
		mapCandidateAcRepository.deleteByCandidatecode(code);
		repo.deleteByCode(code);
		
	}
	
		
}