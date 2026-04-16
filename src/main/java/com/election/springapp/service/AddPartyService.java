package com.election.springapp.service;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.election.springapp.model.AddPartyDto;
import com.election.springapp.model.User;
import com.election.springapp.repository.AddPartyRepository;

@Service
public class AddPartyService {
	
	private final AddPartyRepository repo;
	
	public AddPartyService (AddPartyRepository repo) {
		this.repo=repo;
	}
	
	
	public void save(AddPartyDto dto) throws IOException{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User loggedInUser = (User) auth.getPrincipal();
		Long createdById = loggedInUser.getId();
		
		if(dto.getSymbol()==null || dto.getSymbol().isEmpty()) {
			throw new IllegalArgumentException("Symbol File is empty");			
		}
		
		
		String contentType=dto.getSymbol().getContentType();
		
		if(contentType==null || !contentType.startsWith("image/")) {
			throw new IllegalArgumentException("File must be an image");
		}
		
		if(repo.existsByName(dto.getName().trim())) {
			throw new IllegalArgumentException("Party Name already in use");
		}
		
		if(repo.existsByAbbr(dto.getAbbreviation().trim())) {
			throw new IllegalArgumentException("Party Abbreviation already in use");
		}
		
		byte[] symbolBytes = dto.getSymbol().getBytes();
		repo.save(dto, symbolBytes, createdById);
		
		
	}
	
	
	
}