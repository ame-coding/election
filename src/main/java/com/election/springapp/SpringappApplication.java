package com.election.springapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.election.springapp.dbini;

@SpringBootApplication
public class SpringappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringappApplication.class, args);
    }

    @Bean
    CommandLineRunner init(dbini db) {
        return args -> db.create();
    }
}