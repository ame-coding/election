package com.election.springapp.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddPartyDto {
	
	@Size(max=50, message="Party Name cannot be longer than 50 characters")
	@NotBlank(message = "Party Name is required")
	private String name;
	
	@Size(max=50, message="Party Abbreviation cannot be longer than 50 characters")
	@NotBlank(message = "Party Abbreviation is required")
	private String abbreviation;
	
	private MultipartFile symbol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public MultipartFile getSymbol() {
		return symbol;
	}

	public void setSymbol(MultipartFile symbol) {
		this.symbol = symbol;
	}
	
	
}

