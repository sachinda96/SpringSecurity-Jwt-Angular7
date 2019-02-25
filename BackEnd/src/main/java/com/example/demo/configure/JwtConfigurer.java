package com.example.demo.configure;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{

	
	private JwtTokenProvider jwtTokenProvid;

	public JwtConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvid = jwtTokenProvider;
    }
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		// TODO Auto-generated method stub
		JwtTokenFilter jwtFilter=new JwtTokenFilter(jwtTokenProvid);
		builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
   
	}
	

    
}
