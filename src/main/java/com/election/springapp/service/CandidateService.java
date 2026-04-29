package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.election.springapp.model.Candidate;
import com.election.springapp.model.CandidateDetails;
import com.election.springapp.repository.CandidateRepository;

@Service
public class CandidateService {
	
	private final CandidateRepository repo;
	private final MapCandidateAcService mapCandidateAcService;
	
	public CandidateService (CandidateRepository repo, MapCandidateAcService mapCandidateAcService) {
		this.repo=repo;
		this.mapCandidateAcService=mapCandidateAcService;
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
	
	public boolean partyHasCandidateRefernce(Long code) {
		
		return repo.partyHasCandidateReference(code);
		
	}
	
	public boolean candidateHasAcReference(Long code){
		
		return mapCandidateAcService.candidateHasAcReference(code);
		
	}
	
	public List<Long> findCandidatecodesByPartyCode(Long partycode){
		
		return repo.findCandidatecodesByPartycode(partycode);
		
	}
	
	@Transactional
	public void deleteByCode(Long code) {
		
		mapCandidateAcService.deleteByCandidatecode(code);
		repo.deleteByCode(code);
		
	}
	

	
		
}