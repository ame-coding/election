package com.election.springapp.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.election.springapp.model.AssemblyConstituency;
import com.election.springapp.model.Candidate;
import com.election.springapp.model.CandidateDetails;
import com.election.springapp.model.Party;

@Repository
public class CandidateRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
	public CandidateRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public List<Candidate> findCandidates() {
		
		String sql = "SELECT candidatecode AS code, candidatename AS name FROM masterstrends.candidates ";
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Candidate.class));
		
	}
	
	public List<CandidateDetails> showCandidateDetails(){
		
		String sql = "SELECT cd.candidatecode, cd.candidatename, pt.partycode, pt.partyname, pt.partyabbr, pt.partysymbol FROM masterstrends.candidates cd LEFT JOIN masterstrends.parties pt ON cd.partycode=pt.partycode";
	
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			CandidateDetails cd=new CandidateDetails();
			cd.setCode(rs.getLong("candidatecode"));
			cd.setName(rs.getString("candidatename"));
			
			Party p=new Party();
			p.setCode(rs.getLong("partycode"));
			p.setName(rs.getString("partyname"));
			p.setAbbr(rs.getString("partyabbr"));
			p.setSymbol(rs.getBytes("partysymbol"));
			
			cd.setParty(p);
			
			return cd;
		});
		
	}
	
	public List<AssemblyConstituency> findACByCandidateCode(Long code){
		
		String sql = "SELECT ac.acno, ac.acname FROM masterstrends.candidatesacsmap map JOIN masters.assemblyconstituencies ac ON map.acno=ac.acno WHERE map.candidatecode = ? ORDER BY ac.acno";
		
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			AssemblyConstituency ac=new AssemblyConstituency();
			ac.setAcno(rs.getLong("acno"));
			ac.setName(rs.getString("acname"));
			return ac;
		}, code);
		
	}
	
	
	public boolean partyHasCandidateReference(Long code) {
		
		String sql = "SELECT COUNT(*) FROM masterstrends.candidates WHERE partycode=?";
		Integer count=jdbcTemplate.queryForObject(sql, Integer.class, code);
		return count!=null && count>0;
		
	}
	
	public List<Long> findCandidatecodesByPartycode(Long partycode){
		
		String sql="SELECT candidatecode FROM masterstrends.candidates WHERE partycode=?";
		return jdbcTemplate.queryForList(sql, Long.class, partycode);
		
	}
	
	public void deleteByCode(Long code) {
		
		String sql = "DELETE from masterstrends.candidates WHERE candidatecode=?";
		jdbcTemplate.update(sql, code);
		
	}
	
	/*public void deleteByPartyCode(Long code) {
		
		String sql = "DELETE from masterstrends.candidates WHERE partycode=?";
		jdbcTemplate.update(sql,code);
		
	}*/
	
	
	
}