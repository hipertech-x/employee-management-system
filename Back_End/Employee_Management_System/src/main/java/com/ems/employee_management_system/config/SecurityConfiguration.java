package com.ems.employee_management_system.config;

import org.springframework.context.annotation.Bean;
//@Bean tells the spring--to create and manage objects for methods

import org.springframework.context.annotation.Configuration;
//@Configuration tells spring--This class contains Configuration rules

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//HttpSecurity is Security rule builder

import org.springframework.security.web.SecurityFilterChain;
//SecurityFilterChain is the list of security filters

@Configuration
public class SecurityConfiguration{
	
	@Bean//--this is @Bean tells spring to create and manage objects from methods
	//we are returning SecurityFilterChain
	SecurityFilterChain  filterChain(HttpSecurity http)throws Exception {
		http.csrf(csrf->csrf.disable()).
		authorizeHttpRequests(auth->auth.anyRequest().permitAll());
		
		return http.build();
	}
	
	
}
