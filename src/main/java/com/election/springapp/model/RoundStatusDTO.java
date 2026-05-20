package com.election.springapp.model;


public class RoundStatusDTO {
	
	private Long roundno;
	
	private boolean hasVotes;

	public Long getRoundno() {
		return roundno;
	}

	public void setRoundno(Long roundno) {
		this.roundno = roundno;
	}

	public boolean isHasVotes() {
		return hasVotes;
	}

	public void setHasVotes(boolean hasVotes) {
		this.hasVotes = hasVotes;
	}
	
	
}