package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.election.springapp.model.CandidateDetails;

@Service
public class RoundVotesService {
	
	private final CountingRoundsService countingRoundsService;
	private final MapCandidateAcService mapCandidateAcService;
	
	public RoundVotesService (CountingRoundsService countingRoundsService, MapCandidateAcService mapCandidateAcService){
		
		this.countingRoundsService=countingRoundsService;
		this.mapCandidateAcService=mapCandidateAcService;
		
	}
	
	public List<Integer> getRoundsByAcno(Long acno){
		
		return countingRoundsService.getRoundsByAcno(acno);
		
	}
	
	public List<CandidateDetails> getCandidatesByAcno(Long acno){
		
		return mapCandidateAcService.getCandidatesByAcno(acno);
		
	}
	
	
	
}