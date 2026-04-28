package com.election.springapp.model;


public class VotesPolled {
	
	private Integer acno;
	
	private String ac;
	
	private Integer totalvoters;
	
	private Integer votespolled;
	
	public Integer getAcno() {
		return acno;
	}

	public void setAcno(Integer acno) {
		this.acno = acno;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public Integer getTotalvoters() {
		return totalvoters;
	}

	public void setTotalvoters(Integer totalvoters) {
		this.totalvoters = totalvoters;
	}

	public Integer getVotespolled() {
		return votespolled;
	}

	public void setVotespolled(Integer votespolled) {
		this.votespolled = votespolled;
	}	
	
	
}