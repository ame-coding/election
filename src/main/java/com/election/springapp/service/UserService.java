package com.election.springapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.election.springapp.repository.UserRepository;

@Service
public class UserService{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository=userRepository;
		this.passwordEncoder=passwordEncoder;
	}
	
	public void register(String username, String password) {
	
		userRepository.save(username, passwordEncoder.encode(password));
		
	}	
	
}