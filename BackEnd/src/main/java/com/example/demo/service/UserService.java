package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.dto.SignInDto;

public interface UserService {

	ResponseEntity<Object> login(String username, String password)throws Exception;
	
	ResponseEntity<Object> signIn(SignInDto signInDto)throws Exception;
	
	ResponseEntity<Object> logout(String token)throws Exception;
	
	String getUserName(String token)throws Exception;
}
