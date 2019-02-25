package com.example.demo.dao;



import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,String> {

	UserEntity findByUserName(String username)throws Exception;
}
