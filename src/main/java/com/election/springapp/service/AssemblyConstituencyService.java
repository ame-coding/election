package com.election.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.election.springapp.model.AssemblyConstituency;
import com.election.springapp.repository.AssemblyConstituencyRepository;

@Service
public class AssemblyConstituencyService {
	
	private final AssemblyConstituencyRepository assemblyconstituencyRepository;

	public AssemblyConstituencyService(AssemblyConstituencyRepository assemblyConstituencyRepository){
		this.assemblyconstituencyRepository=assemblyConstituencyRepository;
	}
	
	
	public List<AssemblyConstituency> getAssemblyConstituencies(){
		
		return assemblyconstituencyRepository.getAssemblyConstituencies();
		
	}
	
}

