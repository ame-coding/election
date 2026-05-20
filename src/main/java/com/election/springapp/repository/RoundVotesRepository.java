package com.election.springapp.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AcStatusDTO;
import com.election.springapp.model.RoundStatusDTO;

@Repository
public class RoundVotesRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public RoundVotesRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public void insertVote(Long acno, Long roundno, String description, Long candidatecode, Long votes, Long createdById) {
		
		String sql="INSERT INTO electionstrends.votes (acno, roundno, description, candidatecode, votessecured, userid, locked) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(sql,acno,roundno, description, candidatecode,votes, createdById, "N");
		
	}
	
	
	public List<AcStatusDTO> getAllAssemblyConstituenciesWithStatus(){
		
		String sql="SELECT ac.acno, ac.acname, COUNT(DISTINCT cr.roundno) AS totalrounds, COUNT(DISTINCT rv.roundno) AS votedrounds "
				+ "FROM masters.assemblyconstituencies ac "
				+ "LEFT JOIN masterstrends.countingrounds cr ON cr.acno=ac.acno "
				+ "LEFT JOIN electionstrends.votes rv ON rv.acno=ac.acno "
				+ "WHERE ac.acno<99 "
				+ "GROUP BY ac.acno, ac.acname "
				+ "ORDER BY ac.acno ";
		
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			
			int total = rs.getInt("totalrounds");
			int voted = rs.getInt("votedrounds");
			
			String status;
			if(voted == 0) status="Not Entered";
			else if(voted < total) status="In Progress";
			else status="Completed";
			
			AcStatusDTO dto=new AcStatusDTO();
			
			dto.setAcno(rs.getLong("acno"));
			dto.setAcname(rs.getString("acname"));
			dto.setStatus(status);
			return dto;
			
		});
		
		
	}
	
	
	public List<RoundStatusDTO> findRoundStatusByAc(Long acno){
		
		String sql="SELECT cr.roundno,  "
				+ "EXISTS (SELECT 1 FROM electionstrends.votes rv WHERE rv.acno=cr.acno AND rv.roundno=cr.roudno) AS has_votes "
				+ "FROM masterstrends.countingrounds cr "
				+ "WHERE cr.acno=? "
				+ "ORDER BY cr.roundno";
		
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			
			RoundStatusDTO dto=new RoundStatusDTO();
			
			dto.setRoundno(rs.getLong("roundno"));
			dto.setHasVotes(rs.getBoolean("has_votes"));
			return dto;
			
		}, acno);
		
		
	}
	
	
	
}