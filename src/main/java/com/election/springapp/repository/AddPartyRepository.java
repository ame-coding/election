package com.election.springapp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AddPartyDto;

@Repository
public class AddPartyRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public AddPartyRepository(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate=jdbcTemplate;
		
	}
	

	public void save(AddPartyDto dto, byte[] symbolBytes, Long createdById) {
		
		String sql = "INSERT INTO masterstrends.parties (partyname, partyabbr, partysymbol, userid) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql,dto.getName(),dto.getAbbreviation(),symbolBytes,createdById);
		
	}
	
	
	public boolean existsByName(String name) {
		
		String sql = "SELECT COUNT(*) FROM masterstrends.parties WHERE LOWER(partyname) = LOWER(?)";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
		return count != null && count > 0;
		
	}
	
	public boolean existsByAbbr(String abbr) {
		
		String sql = "SELECT COUNT(*) FROM masterstrends.parties WHERE LOWER(partyabbr) = LOWER(?)";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, abbr);
		return count != null && count > 0;
		
	}
	
	
	
	
	//WIP
	
	/*public byte[] getSymbol(AddPartyDto dto) {
		
		return jdbcTemplate.queryForObject(
				"SELECT partysymbol FROM masterstrends.parties WHERE partyabbr = ?",
				(rs, rowNum) -> rs.getBytes(partysymbol),
				dto.getAbbreviation()
				);
		
	}*/
	
	
	
}