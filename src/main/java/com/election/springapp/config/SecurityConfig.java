package com.election.springapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.election.springapp.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomUserDetailsService userDetailsService;
	
	public SecurityConfig (CustomUserDetailsService userDetailsService) {
		this.userDetailsService=userDetailsService;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.userDetailsService(userDetailsService)
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/login", "/register", "/css/**").permitAll()
					.anyRequest().authenticated()
					)
					.formLogin(form -> form
							.loginPage("/login")
							.defaultSuccessUrl("/home")
							.permitAll()
					)
					.logout(logout -> logout
							.logoutSuccessUrl("/login?logout")
					);
			
		return http.build();
		
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
		
	}
	
}
