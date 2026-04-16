package com.election.springapp.model;

public class Party {

	private Long code;

	private String name;
	
	private String abbr;

	private byte[] symbol;

	
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

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public byte[] getSymbol() {
		return symbol;
	}

	public void setSymbol(byte[] symbol) {
		this.symbol = symbol;
	}

	

	
}