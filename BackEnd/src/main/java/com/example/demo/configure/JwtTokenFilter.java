package com.example.demo.configure;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class JwtTokenFilter extends GenericFilterBean{

	private JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		 String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		 System.out.println("token filter");
	        if (token != null && jwtTokenProvider.validateToken(token)) {
	        	Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }
	        chain.doFilter(request, response);
		
	}

}
