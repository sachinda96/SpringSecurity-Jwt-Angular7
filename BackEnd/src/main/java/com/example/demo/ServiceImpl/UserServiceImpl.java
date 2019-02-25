package com.example.demo.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.configure.JwtTokenProvider;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.SignInDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public ResponseEntity<Object> login(String username, String password) throws Exception {
		UserEntity userEntity = userDao.findByUserName(username);

		if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {

			String token = jwtTokenProvider.createToken(username, userEntity.getUserRoles());
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("null", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> signIn(SignInDto signInDto) throws Exception {
		// TODO Auto-generated method stub

		if (signInDto.getPassword().equals(signInDto.getCpassword())) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUseid(UUID.randomUUID().toString());
			userEntity.setUserName(signInDto.getUsername());
			userEntity.setPassword(bCryptPasswordEncoder.encode(signInDto.getPassword()));
			userEntity.setUserRoles(signInDto.getRoles());
			userDao.save(userEntity);

		}
		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> logout(String token) throws Exception {
		// TODO Auto-generated method stub
		boolean removed=jwtTokenProvider.logOut(token);
		if(removed) {
			return new ResponseEntity<Object>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(false, HttpStatus.OK);
	}

	@Override
	public String getUserName(String token) throws Exception {
		return jwtTokenProvider.getUsername(token);
	}

}
