package com.election.springapp.service;

import org.springframework.stereotype.Service;

import com.election.springapp.model.CountingRounds;
import com.election.springapp.repository.CountingRoundsRepository;
import com.election.springapp.util.SecurityUtils;

@Service
public class CountingRoundsService {
	
	private final CountingRoundsRepository repo;
	private final SecurityUtils securityUtils;
	
	public CountingRoundsService (CountingRoundsRepository repo, SecurityUtils securityUtils) {
		
		this.repo=repo;
		this.securityUtils=securityUtils;
		
	}
	
	public Long getNextRoundNo (Long acno) {
		
		return repo.getNextRoundNo(acno);
		
	}
	
	
	public void save(CountingRounds cr) {
		
		Long expected=repo.getNextRoundNo(cr.getAcno());
		Long createdById=securityUtils.getCurrentUserId();
		
		if(!cr.getRoundno().equals(expected)) {
			
			throw new IllegalArgumentException("Round No. must be " + expected + " for this Assembly Constituency. Got: " + cr.getRoundno());
		}
	
		repo.save(cr, createdById);
		
	}
	
	
	
	
}