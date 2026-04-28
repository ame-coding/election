package com.election.springapp.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AddVotersDto;
import com.election.springapp.model.VotesPolled;

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
	
	public List<VotesPolled> findAll(){
		
		String sql="SELECT ac.acno, ac.acname AS ac, COALESCE(vp.totalvoters, '0') AS totalvoters, COALESCE(vp.totalvotespolled, '0') AS votespolled FROM masters.assemblyconstituencies ac LEFT JOIN electionstrends.voters vp ON ac.acno=vp.acno WHERE ac.acno<99 ORDER BY ac.acno";
	
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(VotesPolled.class));
		
	}
	
	
	public void deleteById(Long id){
		
		String sql="DELETE FROM electionstrends.voters WHERE acno=?";
		jdbcTemplate.update(sql, id);
		
	}
	
	
	
}