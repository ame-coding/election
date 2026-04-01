package com.election.springapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AddVotersDto;

@Repository
public class AddVotersRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public AddVotersRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public void save(AddVotersDto dto, Long createdById) {
		
		String sql = "INSERT INTO electionstrends.voters (acno, totalvoters, totalvotespolled, userid) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql,dto.getAcno(),dto.getTotalvoters(),dto.getTotalvotespolled(),createdById);
		
	}
	
	
	public boolean existsByAcno(int acno) {
		
		String sql = "SELECT COUNT(*) FROM electionstrends.voters WHERE acno = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, acno);
		return count != null && count > 0;
		
	}
	
	
}