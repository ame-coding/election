package com.election.springapp.model;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VoteSubmissionForm {
	
	@NotNull(message = "Assembly Constituency must be selected")
	private Long acno;
	
	@NotNull(message = "Round Number must be selected")
	private Long roundno;
	
	@Pattern(regexp="Postal Ballot|EVM Counting", message="Description must be EVM Counting or Postal Ballot")
	private String description;
	
	@NotNull(message = "No candidates found")
	@NotEmpty(message = "No candidates found")
	private List<CandidateVotes> candidatevotes;

	public Long getAcno() {
		return acno;
	}

	public void setAcno(Long acno) {
		this.acno = acno;
	}

	public Long getRoundno() {
		return roundno;
	}

	public void setRoundno(Long roundno) {
		this.roundno = roundno;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CandidateVotes> getCandidatevotes() {
		return candidatevotes;
	}

	public void setCandidatevotes(List<CandidateVotes> candidatevotes) {
		this.candidatevotes = candidatevotes;
	}
	
	
	
}