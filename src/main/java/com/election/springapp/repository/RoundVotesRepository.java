package com.election.springapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoundVotesRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public RoundVotesRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public void insertVote(Long acno, Long roundno, Long candidatecode, Long votes, Long createdById) {
		
		String sql="INSERT INTO masterstrends.roundvotes (acno, roundno, candidatecode, votes, userid) VALUES (?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql,acno,roundno,candidatecode,votes, createdById);
		
	}
	
}