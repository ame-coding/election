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
		
		Long expected=repo.getNextRoundNo(cr.getAcno());
		
		if(expected>0) {
			
			throw new IllegalArgumentException("Rounds are already added for this assembly constituency, delete existing ones to add new rounds!");
			
		}
	
		
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
	
	public List<Integer> getRoundsByAcno(Long acno){
		
		return repo.getRoundsByAcno(acno);
		
	}
	
	
	
	public List<ViewCountingRounds> findAll(){
		
		return repo.findAll();
		
	}
	
	public void deleteById(Long acno, Long roundno) {
		
		repo.deleteById(acno,roundno);
		
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