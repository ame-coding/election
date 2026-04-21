package com.election.springapp.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.election.springapp.model.User;

@Component
public class SecurityUtils{
	
	public Long getCurrentUserId() {
		
		return getCurrentUser().getId();
	}

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }	
    
}
