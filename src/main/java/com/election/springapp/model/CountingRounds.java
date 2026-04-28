package com.election.springapp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CountingRounds {
	
	@NotNull(message="Assembly Constituency not selected")
	private Long acno;
	
	@NotNull(message="Choose a Round Number!")
	@Min(value=1, message="Cannot be less than 1 round")
	private Long roundno;
	
	private String desc;

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	
	
}