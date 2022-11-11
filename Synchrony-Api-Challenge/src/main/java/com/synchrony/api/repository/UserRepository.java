package com.synchrony.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synchrony.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUserName(String userName);
	

}
