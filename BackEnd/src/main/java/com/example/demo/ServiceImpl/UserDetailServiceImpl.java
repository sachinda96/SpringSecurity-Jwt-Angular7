package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;

import antlr.collections.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			System.out.println("user name "+username);
			ArrayList<GrantedAuthority> roles=new ArrayList<GrantedAuthority>();
			UserEntity userEntity=userDao.findByUserName(username);
			System.out.println("user Entity "+userEntity.getUserRoles());
			
				System.out.println("rolesss "+userEntity.getUserRoles());
				roles.add(new SimpleGrantedAuthority(userEntity.getUserRoles()));
			
			return new User(userEntity.getUserName(),userEntity.getPassword(), roles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UsernameNotFoundException("username not fount: " + username );
		}

	}

	

}
