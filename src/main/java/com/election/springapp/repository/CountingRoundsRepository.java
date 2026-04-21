package com.election.springapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.CountingRounds;

@Repository
public class CountingRoundsRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public CountingRoundsRepository (JdbcTemplate jdbcTemplate){
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public Long getNextRoundNo(Long acno) {
		
		String sql = "SELECT COALESCE (MAX(roundno),0) + 1 FROM masterstrends.countingrounds WHERE acno = ?";
		
		return jdbcTemplate.queryForObject(sql, Long.class, acno);
		
	}
	
	public void save(CountingRounds cr, Long createdById) {
		
		String sql="INSERT INTO masterstrends.countingrounds (acno, roundno, description, userid) VALUES (?, ?, ?, ?)";
		
		jdbcTemplate.update(sql, cr.getAcno(), cr.getRoundno(), cr.getDesc(), createdById);
		
	}
	
	
	
	
}
