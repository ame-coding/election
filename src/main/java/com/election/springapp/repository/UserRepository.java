package com.election.springapp.repository;

import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.User;

@Repository
public class UserRepository{
	
	private final JdbcTemplate jdbcTemplate;
	
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public Optional<User> findByUsername(String username) {
		
		String sql="SELECT userid AS id, username, password FROM electionstrendsusers.users WHERE username = ?";
		try {
			User user=jdbcTemplate.queryForObject(sql, (rs,rowNum) -> {
				User u=new User();
				u.setId(rs.getLong("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				return u;
			}, username);
			return Optional.ofNullable(user);
		}catch(EmptyResultDataAccessException e) {
		
			return Optional.empty();
			
		}
	
	}
	
	public void save(String username, String encodedPassword) {
		
		String sql="INSERT INTO electionstrendsusers.users(username,password) VALUES (?, ?)";		
		jdbcTemplate.update(sql, username, encodedPassword);
		
	}
	
	
}