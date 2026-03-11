package com.election.springapp;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class start {

	private JdbcTemplate jdbc;

    public  start(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @GetMapping("/")
    public String home() {
        return "mainpage";
    }
    
    @ResponseBody
    @GetMapping("/word")
    public Map<String,String> words() {

        try {

            String sql = "SELECT word FROM test";

            Map<String,String> result = jdbc.query(sql,
                    rs -> {

                        if (!rs.next()) {
                            return Map.of(
                                "status", "error",
                                "message", "something wrong with db"
                            );
                        }

                        String words = rs.getString(1);

                        return Map.of(
                                "status", "ok",
                                "message", words
                        );
                    }
                );

                return result;

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of(
                    "status", "error",
                    "message", "dbini error"
            );
        }
    }
}
