package com.election.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.election.springapp.model.CountingRounds;
import com.election.springapp.model.ViewCountingRounds;
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
	
	
	public void createCountingRounds(CountingRounds cr) {
		
		List<CountingRounds> rows=new ArrayList<>();
		Long createdById=securityUtils.getCurrentUserId();
		
		for(long i=1; i<=cr.getRoundno(); i++) {
			
			CountingRounds row=new CountingRounds();
			row.setAcno(cr.getAcno());
			row.setRoundno(i);
			row.setDesc("-");
			rows.add(row);
		}
		
		repo.createCountingRounds(rows, createdById);
		
	}
	
	public List<ViewCountingRounds> findAll(){
		
		return repo.findAll();
		
	}
	
	public void deleteById(Long acno, Long rounds) {
		
		repo.deleteById(acno,rounds);
		
	}
	
	
	/*public void save(CountingRounds cr) {
		
		//Long expected=repo.getNextRoundNo(cr.getAcno());
		Long createdById=securityUtils.getCurrentUserId();
		
		if(!cr.getRoundno().equals(expected)) {
			
			throw new IllegalArgumentException("Round No. must be " + expected + " for this Assembly Constituency. Got: " + cr.getRoundno());
		}
	
		repo.save(cr, createdById);
		
	}*/
	
	
	
	
}