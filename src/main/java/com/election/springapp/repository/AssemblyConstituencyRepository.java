package com.election.springapp.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AssemblyConstituency;

@Repository
public class AssemblyConstituencyRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public AssemblyConstituencyRepository (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	
	public List<AssemblyConstituency> getAssemblyConstituencies(){
		
		String sql="SELECT acno, acname AS name FROM masters.assemblyconstituencies";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(AssemblyConstituency.class));
		
	}
	
	
	
}