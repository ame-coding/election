package com.election.springapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AddCandidateDto;

@Repository
public class AddCandidateRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AddCandidateRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public void save (AddCandidateDto dto, Long createdById) {
		
		String sql = "INSERT INTO masterstrends.candidates (candidatename, partycode, userid) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, dto.getName(), dto.getPartyno(), createdById);
		
	}
	
	public boolean existsByName(String name) {
		
		String sql = "SELECT COUNT(*) FROM masterstrends.candidates WHERE LOWER(candidatename) = LOWER(?)";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
		return count != null && count > 0;
		
	}
	
	
	
	
}