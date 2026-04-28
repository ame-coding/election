package com.election.springapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.CountingRounds;
import com.election.springapp.model.ViewCountingRounds;

@Repository
public class CountingRoundsRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public CountingRoundsRepository (JdbcTemplate jdbcTemplate){
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public Long getNextRoundNo(Long acno) {
		
		String sql = "SELECT COALESCE (MAX(rounds),0) + 1 FROM masterstrends.countingrounds WHERE acno = ?";
		
		return jdbcTemplate.queryForObject(sql, Long.class, acno);
		
	}
	
	public void save(CountingRounds cr, Long createdById) {
		
		String sql="INSERT INTO masterstrends.countingrounds (acno, rounds, userid) VALUES (?, ?, ?)";
		
		jdbcTemplate.update(sql, cr.getAcno(), cr.getRoundno(), createdById);
		
	}
	
	public void createCountingRounds(List<CountingRounds> rows, Long createdById) {
		
		String sql="INSERT INTO masterstrends.countingrounds (acno, rounds, description, userid) VALUES (?, ?, ?, ?)";
		
		 List<Object[]> batchArgs = new ArrayList<>();
		 
		 for (CountingRounds row : rows) {
			 batchArgs.add(new Object[]{
					 row.getAcno(),
					 row.getRoundno(),
					 row.getDesc(),
					 createdById
			 });
		 }

		 jdbcTemplate.batchUpdate(sql, batchArgs);
		
	}
	
	public List<ViewCountingRounds> findAll(){
		
		String sql="SELECT ac.acno, ac.acname, r.rounds, r.description AS desc FROM masters.assemblyconstituencies ac RIGHT JOIN masterstrends.countingrounds r  ON ac.acno=r.acno WHERE ac.acno<99 ORDER BY ac.acno";
		
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ViewCountingRounds.class));
		
	}
	
	public void deleteById(Long acno, Long rounds) {
		
		String sql="DELETE FROM masterstrends.countingrounds WHERE acno=? AND rounds=?";
	
		jdbcTemplate.update(sql,acno,rounds);
		
	}
	
	
	
}
