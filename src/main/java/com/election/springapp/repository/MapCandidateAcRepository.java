package com.election.springapp.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.CandidatesAcsMap;
import com.election.springapp.model.MapCandidateAcDto;

@Repository
public class MapCandidateAcRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public MapCandidateAcRepository (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public void save (MapCandidateAcDto dto, Long createdById) {
		
		String sql = "INSERT INTO masterstrends.candidatesacsmap (candidatecode, acno, userid) VALUES (?, ?, ?)";
	
		jdbcTemplate.update(sql, dto.getCandidateCode(), dto.getAcno(), createdById);
		
	}
	
	public Boolean existsByMapping(MapCandidateAcDto dto) {
		
		String sql = "SELECT COUNT(*) FROM masterstrends.candidatesacsmap WHERE candidatecode = ? AND acno = ?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, dto.getCandidateCode(),dto.getAcno());
		return count != null && count > 0;

		
	}
	
	public Boolean candidateHasAcReference(Long code) {
		
		String sql="SELECT COUNT(*) FROM masterstrends.candidatesacsmap WHERE candidatecode = ?";
		Integer count=jdbcTemplate.queryForObject(sql , Integer.class, code);
		return count !=null && count>0;
		
	}
	
	public List<CandidatesAcsMap> findAll(){
		
		String sql="SELECT map.candidatecode, map.acno, can.candidatename, ac.acname FROM masterstrends.candidatesacsmap map JOIN masterstrends.candidates can ON map.candidatecode=can.candidatecode JOIN masters.assemblyconstituencies ac ON map.acno=ac.acno ORDER BY map.candidatecode";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CandidatesAcsMap.class));
		
		
	}
	
	public void deleteByCandidatecodeAndAcno(Long code, Long acno) {
		
		String sql="DELETE FROM masterstrends.candidatesacsmap WHERE candidatecode = ? AND acno = ?";
		jdbcTemplate.update(sql, code, acno);
		
		
	}
	
	
	public void deleteByCandidatecode(Long code) {
		
		String sql="DELETE FROM masterstrends.candidatesacsmap WHERE candidatecode = ?";
		jdbcTemplate.update(sql, code);
		
	}
	
	
}