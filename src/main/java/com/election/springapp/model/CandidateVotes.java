package com.election.springapp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CandidateVotes {
	
	@NotNull(message = "Candidate Code is missing")
	private Long candidatecode;
	
	@NotNull(message = "Vote Count is missing")
	@Min(value=0, message="Votes cannot be negative")
	private Long votes;

	public Long getCandidatecode() {
		return candidatecode;
	}

	public void setCandidatecode(Long candidatecode) {
		this.candidatecode = candidatecode;
	}

	public Long getVotes() {
		return votes;
	}

	public void setVotes(Long votes) {
		this.votes = votes;
	}

}