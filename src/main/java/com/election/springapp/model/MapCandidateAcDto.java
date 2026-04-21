package com.election.springapp.model;

import jakarta.validation.constraints.NotNull;

public class MapCandidateAcDto {
	
	@NotNull(message="Candidate must be selected")
	private Integer candidateCode;
	
	@NotNull(message="Select an assembly constituency for the candidate to contest in")
	private Integer acno;

	public Integer getCandidateCode() {
		return candidateCode;
	}

	public void setCandidateCode(Integer candidateCode) {
		this.candidateCode = candidateCode;
	}

	public Integer getAcno() {
		return acno;
	}

	public void setAcno(Integer acno) {
		this.acno = acno;
	}
	
	
}