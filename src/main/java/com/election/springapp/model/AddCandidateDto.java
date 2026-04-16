package com.election.springapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddCandidateDto {
	
	@Size(max=50, message="Candidate Name cannot be longer than 50 words")
	@NotBlank(message = "Candidate Name cannot be blank")
	private String name;
	
	@NotNull(message = "Candidate must be assigned to a party")
	private Integer partyno;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPartyno() {
		return partyno;
	}

	public void setPartyno(Integer partyno) {
		this.partyno = partyno;
	}
	
}