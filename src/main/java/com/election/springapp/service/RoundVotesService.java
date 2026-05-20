package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.election.springapp.model.AcStatusDTO;
import com.election.springapp.model.CandidateDetails;
import com.election.springapp.model.CandidateVotes;
import com.election.springapp.model.RoundStatusDTO;
import com.election.springapp.model.VoteSubmissionForm;
import com.election.springapp.repository.RoundVotesRepository;
import com.election.springapp.util.SecurityUtils;

@Service
public class RoundVotesService {
	
	private final CountingRoundsService countingRoundsService;
	private final MapCandidateAcService mapCandidateAcService;
	private final RoundVotesRepository repo;
	private final SecurityUtils securityUtils;
	
	public RoundVotesService (CountingRoundsService countingRoundsService, MapCandidateAcService mapCandidateAcService, RoundVotesRepository repo, SecurityUtils securityUtils){
		
		this.countingRoundsService=countingRoundsService;
		this.mapCandidateAcService=mapCandidateAcService;
		this.repo=repo;
		this.securityUtils=securityUtils;
		
	}
	
	public List<Integer> getRoundsByAcno(Long acno){
		
		return countingRoundsService.getRoundsByAcno(acno);
		
	}
	
	public List<CandidateDetails> getCandidatesByAcno(Long acno){
		
		return mapCandidateAcService.getCandidatesByAcno(acno);
		
	}
	
	@Transactional
	public void saveVotes(VoteSubmissionForm form) {
		
		Long createdById=securityUtils.getCurrentUserId();
		
		for(CandidateVotes cv : form.getCandidatevotes()) {
			
			repo.insertVote(form.getAcno(), form.getRoundno(), form.getDescription(), cv.getCandidatecode(), cv.getVotes(), createdById);
			
		}
		
	}
	
	
	public List<AcStatusDTO> getAllAssemblyConstituenciesWithStatus(){
		
		return repo.getAllAssemblyConstituenciesWithStatus();
		
	}
	
	public List<RoundStatusDTO> findRoundStatusByAc(Long acno){
		
		return repo.findRoundStatusByAc(acno);
		
	}
	
	
}