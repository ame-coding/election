package com.election.springapp.model;

import java.util.List;

public class CandidateDetails {
	
	private Long code;
	
	private String name;
	
	private Party party;
	
	private List<AssemblyConstituency> ac;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public List<AssemblyConstituency> getAc() {
		return ac;
	}

	public void setAc(List<AssemblyConstituency> ac) {
		this.ac = ac;
	}
	
	
}