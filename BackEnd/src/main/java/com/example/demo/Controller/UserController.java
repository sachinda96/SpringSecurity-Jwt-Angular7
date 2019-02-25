package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configure.JwtTokenProvider;
import com.example.demo.dto.SignInDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping(value="/login")
	public ResponseEntity<Object> login(@RequestParam String username,@RequestParam String password,@RequestParam String role) throws Exception{
		return userService.login(username, password);
	}
	
	@GetMapping("/getusername/")
	public String getUsername(@RequestHeader("Authorization") String Authorization) throws Exception {
		String token=Authorization.substring(7,Authorization.length());
		return userService.getUserName(token);
	}
	
	@PostMapping("/signup")
	private ResponseEntity<Object> signIn(@RequestBody SignInDto signInDto) throws Exception{
		return userService.signIn(signInDto);
	}
	
	@GetMapping("/logout")
	private ResponseEntity<Object> logout(@RequestHeader("Authorization") String Authorization) throws Exception{
		String token=Authorization.substring(7,Authorization.length());
		return userService.logout(token);
	}
	
}
