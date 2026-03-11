package com.election.springapp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;

@Component
public class dbini {
	private JdbcTemplate jdbc;

    public dbini(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


	  public void create() throws Exception {
		  jdbc.update(
				    "CREATE TABLE IF NOT EXISTS test (" +
				    "id SERIAL PRIMARY KEY, " +
				    "word VARCHAR(50) UNIQUE)"
				);

		  jdbc.update(
			    "INSERT INTO test (word) VALUES ('huhh'),('hmmm'),('HIII') "
		        +"ON CONFLICT (word) DO NOTHING"
		);

	    }
}
