package com.election.springapp.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AddVotersDto {
	
	@NotNull(message = "Assembly Constituency is required")
	private Integer acno;
	
	@NotNull(message = "Total No. of Voters cannot be Empty")
	@Min(value = 9999, message = "Total No. of Voters cannot be less than 9999")
	private Integer totalvoters;
	
	@NotNull(message = "No. of Votes Polled cannot be Empty")
	@Min(value = 9999, message = "No. of Votes Polled cannot be less than 9999")
	private Integer totalvotespolled;

	public Integer getAcno() {
		return acno;
	}

	public void setAcno(Integer acno) {
		this.acno = acno;
	}

	public Integer getTotalvoters() {
		return totalvoters;
	}

	public void setTotalvoters(Integer totalvoters) {
		this.totalvoters = totalvoters;
	}

	public Integer getTotalvotespolled() {
		return totalvotespolled;
	}

	public void setTotalvotespolled(Integer totalvotespolled) {
		this.totalvotespolled = totalvotespolled;
	}
	
	
}

