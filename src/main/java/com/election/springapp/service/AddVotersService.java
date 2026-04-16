package com.election.springapp.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.election.springapp.model.AddVotersDto;
import com.election.springapp.model.User;
import com.election.springapp.model.VotesPolled;
import com.election.springapp.repository.AddVotersRepository;

@Service
public class AddVotersService {
	
	private final AddVotersRepository addVotersRepo;
	
	public AddVotersService(AddVotersRepository addVotersRepo) {
		this.addVotersRepo=addVotersRepo;
	}
	
	public void save(AddVotersDto dto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = (User) auth.getPrincipal();
		Long createdById = loggedInUser.getId();
		
		if(dto.getTotalvotespolled()>dto.getTotalvoters()) {
			throw new IllegalArgumentException("Votes Polled cannot be greater than the no. of voters");
		}
		
		if(addVotersRepo.existsByAcno(dto.getAcno())) {
			throw new IllegalArgumentException("Assembly Constituency already has total no. of voters & votes polled");
		}
		
		addVotersRepo.save(dto, createdById);
	}
	
	
	public List<VotesPolled> findAll(){
		
		return addVotersRepo.findAll();
	
	}
	
	
}