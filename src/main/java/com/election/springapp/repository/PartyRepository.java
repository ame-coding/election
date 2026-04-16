package com.election.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.Party;

@Repository
public class PartyRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public PartyRepository (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public List<Party> getParties(){
		
		String sql="SELECT partycode AS code, partyname AS name FROM masterstrends.parties";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Party.class));
		
	}
	
	public List<Party> getPartyDetails(){
		
		String sql="SELECT partycode, partyname, partyabbr, partysymbol FROM masterstrends.parties";
		
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Party p=new Party();
			p.setCode(rs.getLong("partycode"));
			p.setName(rs.getString("partyname"));
			p.setAbbr(rs.getString("partyabbr"));
			p.setSymbol(rs.getBytes("partysymbol"));
			return p;
		});
		
	}
	
	public Optional<Party> findImageByCode(Long code) {
		
		String sql="SELECT partycode, partysymbol FROM masterstrends.parties WHERE partycode = ?";
		
		List<Party> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
			Party p=new Party();
			p.setCode(rs.getLong("partycode"));
			p.setSymbol(rs.getBytes("partysymbol"));
			return p;
		}, code);
		
		return result.stream().findFirst();
		
		
	}
	
	
}